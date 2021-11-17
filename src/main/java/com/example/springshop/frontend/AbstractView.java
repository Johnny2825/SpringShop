package com.example.springshop.frontend;

import com.example.springshop.config.security.CustomUserDetails;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractView extends VerticalLayout {
    private final Button logoutButton;
    private final Button mainPageButton;
    private final Button addProductPageButton;
    private final Button toCartButton;
    private final Button toOrderButton;

    private static final SimpleGrantedAuthority ADMIN_ROLE = new SimpleGrantedAuthority("ADMIN");
    private static final SimpleGrantedAuthority SELLER_ROLE = new SimpleGrantedAuthority("SELLER");
    private static final SimpleGrantedAuthority MANAGER_ROLE = new SimpleGrantedAuthority("MANAGER");


    public AbstractView() {

        logoutButton = new Button("Выйти", e -> {
            SecurityContextHolder.clearContext();
            UI.getCurrent().navigate(LoginView.class);
        });

        mainPageButton = new Button("Список товаров", e -> {
            UI.getCurrent().navigate(ProductListView.class);
        });

        addProductPageButton = new Button("Добавить продукт", e -> {
            UI.getCurrent().navigate(AddProductView.class);
        });

        toCartButton = new Button("Корзина", event -> {
            UI.getCurrent().navigate("cart");
        });

        toOrderButton = new Button("Заказы", event -> {
            UI.getCurrent().navigate("order");
        });

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.END);

        var hl = new HorizontalLayout(mainPageButton, toOrderButton, toCartButton, logoutButton);

        var adminButtons = new HorizontalLayout(initSpecialButtons());
        add(adminButtons, hl);
    }


    private HorizontalLayout initSpecialButtons() {
        var details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var buttons = new ArrayList<Button>();
        if (details instanceof CustomUserDetails && ((CustomUserDetails) details).getAuthorities().contains(ADMIN_ROLE)) {
            buttons.add(new Button("Список пользователей", e -> {
                        UI.getCurrent().navigate(PersonListView.class);
                    })
            );
        }

        if (details instanceof CustomUserDetails) {
            var detail = ((CustomUserDetails) details).getAuthorities();
            if (detail.contains(ADMIN_ROLE) || detail.contains(SELLER_ROLE) || detail.contains(MANAGER_ROLE)) {
                buttons.add(new Button("Создать товар", e -> {
                            UI.getCurrent().navigate(AddProductView.class);
                        })
                );
            }
        }
        var hl = new HorizontalLayout();
        buttons.forEach(hl::add);
        return hl;
    }

    protected TextField initTextFieldWithPlaceholder(String placeholder) {
        var textField = new TextField();
        textField.setPlaceholder(placeholder);
        return textField;
    }
}
