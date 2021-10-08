package com.example.springshop.frontend;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        var titleLayout = new HorizontalLayout();
        var buttonTextLayout = new HorizontalLayout();

        var titleText = new Text("Заголовок интернет-магазина");
        var buttonText = new Text("Здесь есть кнопка");
        var button = new Button("Нажми меня", event -> {
            Notification.show("Вы нажали кнопку");
        });

        titleLayout.add(titleText);
        buttonTextLayout.add(buttonText, button);

        add(titleLayout, buttonTextLayout);
    }
}
