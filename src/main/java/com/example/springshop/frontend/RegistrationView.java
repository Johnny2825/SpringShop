package com.example.springshop.frontend;

import com.example.springshop.entity.Person;
import com.example.springshop.service.PersonService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("registration")
@PageTitle("Registration | Vaadin Shop")
public class RegistrationView extends VerticalLayout {

    private final PersonService personService;

    public RegistrationView(PersonService personService) {
        this.personService = personService;

        initRegistrationView();
    }

    private void initRegistrationView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        var phoneTextField = initTextFieldWithPlaceholder("Номер телефона");
        var loginTextField = initTextFieldWithPlaceholder("Логин");
        var passwordField = new PasswordField();
        passwordField.setPlaceholder("Пароль");
        var lastNameTextField = initTextFieldWithPlaceholder("Фамилия");
        var firstNameTextField = initTextFieldWithPlaceholder("Имя");
        var patronymicTextField = initTextFieldWithPlaceholder("Отчество");
        var emailTextField = initTextFieldWithPlaceholder("Почта");
        var addressTextField = initTextFieldWithPlaceholder("Адрес");

        var registrationButton = new Button("Зарегистрироваться", event -> {
            boolean hasError = false;
            if (!phoneTextField.getValue().matches("\\d{11,14}+")) {
                Notification.show("Телефон должен состоять только из цифр");
                hasError = true;
            }

            if (!loginTextField.getValue().matches("[a-zA-Z0-9]{1,128}+")) {
                Notification.show("Логин не соотвествует ожидаемому");
                hasError = true;
            }

            if (!passwordField.getValue().matches("[a-zA-Z0-9]{1,128}+")) {
                Notification.show("Пароль не соотвествует ожидаемому");
                hasError = true;
            }

            if (!lastNameTextField.getValue().matches("[а-яА-Я0-9]{1,128}+")) {
                Notification.show("Фамилия не соотвествует ожидаемому");
                hasError = true;
            }

            if (!firstNameTextField.getValue().matches("[а-яА-Я0-9]{1,128}+")) {
                Notification.show("Имя не соотвествует ожидаемому");
                hasError = true;
            }

            if (!patronymicTextField.getValue().matches("[а-яА-Я0-9]{1,128}+")) {
                Notification.show("Отчество не соотвествует ожидаемому");
                hasError = true;
            }

            if (!emailTextField.getValue().matches("[a-zA-Z0-9@.]{5,256}+")) {
                Notification.show("Почта не соотвествует ожидаемому");
                hasError = true;
            }

            if (!addressTextField.getValue().matches("[а-яА-Я0-9]{1,1024}+")) {
                Notification.show("Отчество не соотвествует ожидаемому");
                hasError = true;
            }

            if (hasError) {
                return;
            } else {
                var person = new Person()
                        .setRole("CUSTOMER")
                        .setLogin(loginTextField.getValue())
                        .setPhone(phoneTextField.getValue())
                        .setPassword(passwordField.getValue())
                        .setLastName(lastNameTextField.getValue())
                        .setFirstName(firstNameTextField.getValue())
                        .setPatronymic(patronymicTextField.getValue())
                        .setAddress(addressTextField.getValue())
                        .setEmail(emailTextField.getValue());

                personService.save(person);

                Notification.show("Регистрация прошла успешно");
                UI.getCurrent().navigate("login");
            }
        });

        var cancelButton = new Button("Отмена", buttonClickEvent ->
                UI.getCurrent().navigate(LoginView.class));

        add(loginTextField,
                phoneTextField,
                loginTextField,
                passwordField,
                lastNameTextField,
                firstNameTextField,
                patronymicTextField,
                emailTextField,
                addressTextField,
                registrationButton,
                cancelButton
        );
    }

    private TextField initTextFieldWithPlaceholder(String placeholder) {
        var textField = new TextField();
        textField.setPlaceholder(placeholder);
        return textField;
    }
}
