package com.example.springshop.frontend;

import com.example.springshop.service.PersonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import java.util.UUID;

//@Route("person/")
public class PersonView extends VerticalLayout {
    private final PersonService personService;
    private UUID id;

    public PersonView(PersonService personService) {
        this.personService = personService;
        System.out.println(id.toString());
        personService.getById(id);
        init();
    }

    private void init() {
        var addPersonButton = initPerssonButton();
        add(addPersonButton);
    }

    private HorizontalLayout initPerssonButton() {
        var deletePeopleButton = new Button("Удалить", buttonClickEvent -> {
//            personService.delete(personGrid.getSelectedItems());
        });
        return new HorizontalLayout(deletePeopleButton);
    }


}
