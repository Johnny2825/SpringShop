package com.example.springshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShopApplication.class, args);
    }

}

/**
 * 1. Дописать скрипты в liquibase
 * 2. Создать сущности под таблицы
 */


/**
 * + Товар - наименования, цена, количество, автор, артикул, категория, дата создания
 * + Пользователь - Имя, Фамилия, Отчество, Телефон, Адрес, Баланс, Роль, Почта, дата создания, логин, пароль
 * + Заказ - Корзина, Стоимость заказа, Пользователь, Номер заказа, Адрес доставки,
 * дата создания(Дополнительно расчет стоимости заказа в зависимости от веса) стоиимости
 * + Категория - Наименование, дата создания
 * + Корзина - дата создания, список товаров и их количество, создатель(Пользователь)
 */
