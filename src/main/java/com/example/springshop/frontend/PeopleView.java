package com.example.springshop.frontend;

import com.example.springshop.entity.Person;
import com.example.springshop.entity.Product;
import com.example.springshop.service.PersonService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route("people")
public class PeopleView extends VerticalLayout {
    private final Grid<Person> personGrid = new Grid<>(Person.class);
    private final PersonService personService;

    public PeopleView(PersonService personService) {
        this.personService = personService;
        initPage();
    }

    private void initPage() {
        var peopleLayout = initPeopleButton();
        initPersonGrid();
        add(personGrid, peopleLayout);
    }

    private HorizontalLayout initPeopleButton() {
        var createNewPersonButton = new Button("Создать нового пользователя", buttonClickEvent -> {
            UI.getCurrent().navigate("people/new");
        });

        var toProductButton = new Button("Продукты", buttonClickEvent -> {
            UI.getCurrent().navigate("products");
        });
        return new HorizontalLayout(createNewPersonButton, toProductButton);
    }

    private void initPersonGrid() {
        var people = personService.getAll();
        personGrid.setColumns("firstName", "lastName", "patronymic", "phone", "role", "email");
        personGrid.setSizeUndefined();
        personGrid.asSingleSelect();
//                .addValueChangeListener(e -> UI.getCurrent().navigate("person/" + e.getValue().getId()));
//                employeeEditor.editEmployee(e.getValue()));

        ListDataProvider<Person> dataProvider = DataProvider.ofCollection(people);
        personGrid.setDataProvider(dataProvider);
    }



}
