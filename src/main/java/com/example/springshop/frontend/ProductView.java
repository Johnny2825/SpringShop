package com.example.springshop.frontend;

import com.example.springshop.entity.Product;
import com.example.springshop.service.CartService;
import com.example.springshop.service.ProductService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("products")
public class ProductView extends VerticalLayout {
    private final Grid<Product> productGrid = new Grid<>(Product.class);

    private final ProductService productService;
    private final CartService cartService;

    public ProductView(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;

        initPage();
    }

    private void initPage() {
        var cartLayout = initCartButton();
        initProductGrid();
        add(productGrid, cartLayout);
    }

    private HorizontalLayout initCartButton() {
        var addToCartButton = new Button("Добавить в корзину", buttonClickEvent -> {
            cartService.addProductToCart(productGrid.asMultiSelect().getSelectedItems());
            Notification.show("Товар успешно добавлен в корзину");
        });

        var toCartButton = new Button("Корзина", buttonClickEvent -> {
                UI.getCurrent().navigate("cart");
        });
        return new HorizontalLayout(addToCartButton, toCartButton);
    }

    private void initProductGrid() {
        var products = productService.getAll();
        productGrid.setItems(products);
        productGrid.setColumns("name", "price", "count", "vendorCode");
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
