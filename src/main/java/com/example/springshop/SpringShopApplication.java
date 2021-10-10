package com.example.springshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShopApplication.class, args);
    }

}

/** 1 урок
 * 1. Дописать скрипты в liquibase
 * 2. Создать сущности под таблицы
 */

/** 2 урок
 * 1. Добавить сохранение товара в корзину
 * 2. Добавть отображение корзины
 */

/** 3 урок
 * 1. С помощью Spring AOP посчитайте по каждому сервису суммарное время,
 * затрачиваемоена выполнение методов этих сервисов и вывести в консоль.
 */


/**
 * + Товар - наименования, цена, количество, автор, артикул, категория, дата создания
 * + Пользователь - Имя, Фамилия, Отчество, Телефон, Адрес, Баланс, Роль, Почта, дата создания, логин, пароль
 * + Заказ - Корзина, Стоимость заказа, Пользователь, Номер заказа, Адрес доставки,
 * дата создания(Дополнительно расчет стоимости заказа в зависимости от веса) стоиимости
 * + Категория - Наименование, дата создания
 * + Корзина - дата создания, список товаров и их количество, создатель(Пользователь)
 */
