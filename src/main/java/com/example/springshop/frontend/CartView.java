package com.example.springshop.frontend;

import com.example.springshop.service.PersonService;
import com.example.springshop.entity.Cart;
import com.example.springshop.service.CartService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route("cart")
public class CartView extends VerticalLayout {
    private final Grid<Cart.InnerProduct> cartGrid = new Grid<>(Cart.InnerProduct.class);

    private final CartService cartService;

    public CartView(CartService cartService) {
        this.cartService = cartService;
        initPage();
    }

    private void initPage() {
        var productLayout = initProductButton();
        initCartGrid();
        add(cartGrid, productLayout);
    }

    private HorizontalLayout initProductButton() {
        var removeFromCartButton = new Button("Удалить из корзины", buttonClickEvent -> {
            cartService.deleteProductFromCart(cartGrid.asMultiSelect().getSelectedItems());
            Notification.show("Товар удален из корзины");
        });
        var toProductButton = new Button("Товары", buttonClickEvent -> {
            UI.getCurrent().navigate("products");
        });
        return new HorizontalLayout(removeFromCartButton, toProductButton);
    }

    private void initCartGrid() {

        var products = cartService.getAllProducts();
        cartGrid.setItems(products);
        cartGrid.setColumns("name", "price", "count", "vendorCode");
        cartGrid.setSizeUndefined();
        cartGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        ListDataProvider<Cart.InnerProduct> dataProvider = DataProvider.ofCollection(products);
        cartGrid.setDataProvider(dataProvider);
    }
}
