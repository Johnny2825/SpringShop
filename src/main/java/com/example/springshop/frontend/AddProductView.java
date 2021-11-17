package com.example.springshop.frontend;

import com.example.springshop.entity.Category;
import com.example.springshop.entity.Product;
import com.example.springshop.service.CategoryService;
import com.example.springshop.service.ProductService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

import java.math.BigDecimal;

@Route("add-product")
public class AddProductView extends AbstractView {
    private final ProductService productService;
    private final CategoryService categoryService;

    public AddProductView(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;

        initProductView();
    }

    private void initProductView() {
        var nameTextField = initTextFieldWithPlaceholder("Имя");
        var priceTextField = initTextFieldWithPlaceholder("Цена");
        var countTextField = initTextFieldWithPlaceholder("Количество");
        var vendorCodeTextField = initTextFieldWithPlaceholder("Артикул");
        var categoryComboBox = new ComboBox<String>();
        categoryComboBox.setPlaceholder("Категория");
        categoryComboBox.setItems(categoryService.findAll().stream().map(Category::getName));


        var createButton = new Button("Создать", e -> {
            boolean hasError = false;
            if (!nameTextField.getValue().matches("[а-яА-Я0-9]+")) {
                Notification.show("Имя не соотвествует ожидаемому");
                hasError = true;
            }

//            if (categoryComboBox.getEmptyValue() == null) {
//                Notification.show("Категория не выбрана");
//                hasError = true;
//            }

            if (!priceTextField.getValue().matches("[0-9]+")) {
                Notification.show("Цена должна состоять из цифр");
                hasError = true;
            }

            if (!countTextField.getValue().matches("[0-9]+")) {
                Notification.show("Количество должно состоять из цифр");
                hasError = true;
            }

            if(hasError) {
                return;
            }

            var product = new Product();
            product.setName(nameTextField.getValue());
            product.setCategory(categoryService.findByName(categoryComboBox.getValue()));
            product.setPrice(new BigDecimal(priceTextField.getValue()));
            product.setCount(Integer.parseInt(countTextField.getValue()));
            product.setVendorCode(vendorCodeTextField.getValue());

            productService.save(product);
            UI.getCurrent().navigate(ProductListView.class);
        });

        var cancelButton = new Button("Отмена", e ->
                UI.getCurrent().navigate(ProductListView.class));

        add(nameTextField, categoryComboBox, priceTextField, countTextField, vendorCodeTextField, createButton, cancelButton);
    }

}
