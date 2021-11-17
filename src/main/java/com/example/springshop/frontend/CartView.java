package com.example.springshop.frontend;

import com.example.springshop.entity.Cart;
import com.example.springshop.entity.Order;
import com.example.springshop.service.CartService;
import com.example.springshop.service.OrderService;
import com.example.springshop.util.PersonUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Route("cart")
public class CartView extends AbstractView {
    private final Grid<Cart.InnerProduct> innerProductGrid = new Grid<>(Cart.InnerProduct.class);
    private final Cart cart;

    private final CartService cartService;
    private final OrderService orderService;

    public CartView(CartService cartService,
                    OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        // ИЗМЕНИТЬ
        Optional<Cart> optionalCart = cartService.findLastCart(PersonUtil.getCurrentPerson());
        this.cart = optionalCart.orElse(null);

        initCartView();
    }

    private void initCartView() {
        initInnerProductGrid();
        initButtons();
    }

    private void initButtons() {
        var saveButton = new Button("Сохранить", e -> {
            var innerProducts = innerProductGrid.getDataProvider()
                    .fetch(new Query<>()).collect(Collectors.toList());

            cart.setProducts(innerProducts);
            cartService.save(cart);
        });

        var initOrderButton = new Button("Создать заказ", e -> {
            var order = new Order();
            order.setCart(cart);
            order.setDeliveryAddress(PersonUtil.getCurrentPerson().getAddress());
            order.setCost(calculateCost());
            order.setPerson(PersonUtil.getCurrentPerson());

            order = orderService.save(order);

            cart.setOrder(order);

            cartService.save(cart);
            UI.getCurrent().getPage().reload();
            Notification.show("Заказ создан");
        });

        add(saveButton, initOrderButton);
    }

    private void initInnerProductGrid() {
        innerProductGrid.setSizeUndefined();
        innerProductGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        if (cart != null) {
            innerProductGrid.setColumns("name", "price", "vendorCode", "count");
            innerProductGrid.setItems(cart.getProducts());

            ListDataProvider<Cart.InnerProduct> dataProvider = DataProvider.ofCollection(cart.getProducts());
            innerProductGrid.setDataProvider(dataProvider);

            innerProductGrid.addColumn(new ComponentRenderer<>(item -> {
                var plusButton = new Button("+", i -> {
                    item.setCount(item.getCount() + 1);
                    innerProductGrid.getDataProvider().refreshItem(item);
                });

                var minusButton = new Button("-", i -> {
                    if (item.getCount() > 0) {
                        item.setCount(item.getCount() - 1);
                        innerProductGrid.getDataProvider().refreshItem(item);
                    }
                });

                var deleteButton = new Button("Удалить", i -> {
                    var products = cart.getProducts()
                            .stream()
                            .filter(p -> !p.getId().equals(item.getId()))
                            .collect(Collectors.toList());

                    cart.setProducts(products);

                    cartService.save(cart);

                    UI.getCurrent().getPage().reload();
                });

                return new HorizontalLayout(plusButton, minusButton, deleteButton);
            }));
        }

        add(innerProductGrid);
    }

    private BigDecimal calculateCost() {
        var cost = BigDecimal.ZERO;
        for(Cart.InnerProduct innerProduct: cart.getProducts()) {
            cost = cost.add(innerProduct.getPrice().multiply(BigDecimal.valueOf(innerProduct.getCount())));
        }

        return cost;
    }
}
