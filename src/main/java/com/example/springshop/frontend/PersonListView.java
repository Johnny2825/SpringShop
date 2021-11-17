package com.example.springshop.frontend;

import com.example.springshop.entity.Person;
import com.example.springshop.service.PersonService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.UUID;

@Route("person-list-view")
public class PersonListView extends AbstractView {
    private final PersonService personService;

    private Grid<Person> personGrid = new Grid<>(Person.class);

    public PersonListView(PersonService personService) {
        this.personService = personService;

        initPersonGrid();

        add(personGrid);
    }

    private void initPersonGrid() {
        var person = personService.findAll();

        personGrid.setItems(person);
        personGrid.setColumns("login", "lastName");
        personGrid.setSizeUndefined();
        personGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        ListDataProvider<Person> dataProvider = DataProvider.ofCollection(person);
        personGrid.setDataProvider(dataProvider);

        personGrid.addColumn(new ComponentRenderer<>(item -> {
            var disable = new Checkbox(item.isDisabled());
            disable.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Checkbox, Boolean>>)
                    checkboxBooleanComponentValueChangeEvent -> {
                        item.setDisabled(disable.getValue());
                        personService.update(item);
                    });
            return new HorizontalLayout(disable);
        }));

    }
}
