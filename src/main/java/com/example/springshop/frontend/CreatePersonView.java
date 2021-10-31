package com.example.springshop.frontend;

import com.example.springshop.entity.Person;
import com.example.springshop.service.PersonService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("people/new")
public class CreatePersonView extends VerticalLayout implements KeyNotifier {
    private final PersonService personService;
    private Binder<Person> binder = new Binder<>(Person.class);
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField( "Last name");
    private TextField patronymic = new TextField( "Patronymic");
    private TextField phone = new TextField( "Phone");
    private TextField address = new TextField( "Address");
    private EmailField email = new EmailField( "Email");
    private PasswordField password = new PasswordField( "Password");
    private ComboBox<String> role = new ComboBox<>("Role");

    private Person person;

    public CreatePersonView(PersonService personService) {
        this.personService = personService;
        binder.bindInstanceFields(this);
        person = new Person();
        binder.setBean(person);
        init();
        addKeyPressListener(Key.ENTER, e -> {
            personService.save(person);
            UI.getCurrent().navigate("people");
        });
    }

    private void init() {
        var creatingFields = initFields();
        var creatingButton = initButtons();
        add(creatingFields, creatingButton);
    }

    private VerticalLayout initFields() {
        email.setErrorMessage("Please enter a valid email address");
        role.setItems("Customer", "Manager", "Admin");
        return new VerticalLayout(firstName, lastName, patronymic, phone,
                address, email, password, role);
    }

    private HorizontalLayout initButtons() {
        var save = new Button("Сохранить", buttonClickEvent -> {
            personService.save(person);
            UI.getCurrent().navigate("people");
        });
        save.getElement().getThemeList().add("primary");


        var cancel = new Button("Отмена", buttonClickEvent ->
            UI.getCurrent().navigate("people"));

        return new HorizontalLayout(save, cancel);
    }


}
