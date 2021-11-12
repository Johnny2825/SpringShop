package com.example.springshop.frontend;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Product;
import com.example.springshop.entity.filter.ProductFilter;
import com.example.springshop.service.CartService;
import com.example.springshop.service.ProductService;
import com.example.springshop.util.PersonUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.stream.Collectors;

@Route("products")
public class ProductListView extends AbstractView {
    private final Grid<Product> productGrid = new Grid<>(Product.class);

    private final ProductService productService;
    private final CartService cartService;

    private final TextField minPriceTextField = new TextField();
    private final TextField maxPriceTextField = new TextField();
    private final TextField nameTextField = new TextField();

    public ProductListView(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;

        initPage();
    }

    private void initPage() {
        var cartLayout = initCartButton();
        initProductGrid();
        add(productGrid, initFilterLayout(), cartLayout);
    }

    private Component initFilterLayout() {
        var vl = new VerticalLayout();
        vl.setAlignItems(Alignment.CENTER);
        vl.setJustifyContentMode(JustifyContentMode.CENTER);

        minPriceTextField.setPlaceholder("Минимальная цена");
        maxPriceTextField.setPlaceholder("Максимальная цена");
        nameTextField.setPlaceholder("Наименование");

        vl.add(minPriceTextField, maxPriceTextField, nameTextField);

        return vl;
    }

    private HorizontalLayout initCartButton() {
        var addToCardButton = new Button("Добавить в корзину", event -> {
            var innerProducts = productGrid.getSelectedItems()
                    .stream()
                    .map(p -> new Cart.InnerProduct()
                            .setId(p.getId())
                            .setPrice(p.getPrice())
                            .setName(p.getName())
                            .setCount(1)
                            .setVendorCode(p.getVendorCode()))
                    .collect(Collectors.toList());

            var cartOptional = cartService.findLastCart(PersonUtil.getCurrentPerson());

            Cart cart; //ИЗМЕНИТЬ
            if (cartOptional.isPresent()) {
                cart = cartOptional.get();
                cart.addProducts(innerProducts);
            } else {
                cart = new Cart();
                cart.setOwner(PersonUtil.getCurrentPerson());
                cart.setProducts(innerProducts);
            }

            cartService.save(cart);
            Notification.show("Товар успешно добавлен в корзину");
        });

        var filterButton = new Button("Отфильтровать", event -> {
            var map = new HashMap<String, String>();
            map.put("minPrice", minPriceTextField.getValue());
            map.put("maxPrice", maxPriceTextField.getValue());
            map.put("name", nameTextField.getValue());

            var productFilter = new ProductFilter(map);
            var filteredProducts = productService.findAllByFilter(productFilter);

            ListDataProvider<Product> dataProvider = DataProvider.ofCollection(filteredProducts);
            productGrid.setDataProvider(dataProvider);
        });

        return new HorizontalLayout(addToCardButton, filterButton);
    }

    private void initProductGrid() {
        var products = productService.findAll();
        productGrid.setItems(products);
        productGrid.setColumns("category", "name", "price", "count", "vendorCode");
        productGrid.setSizeUndefined();
        productGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        ListDataProvider<Product> dataProvider = DataProvider.ofCollection(products);
        productGrid.setDataProvider(dataProvider);

        productGrid.addColumn(new ComponentRenderer<>(item -> {
            var plusButton = new Button("+", i -> {
                item.incrementCount();
                productService.save(item);
                productGrid.getDataProvider().refreshItem(item);
            });

            var minusButton = new Button("-", i -> {
                item.decreaseCount();
                productService.save(item);
                productGrid.getDataProvider().refreshItem(item);
            });


            return new HorizontalLayout(plusButton, minusButton);
        }));
    }


}
