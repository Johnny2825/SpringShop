package com.example.springshop.service;

import com.example.springshop.entity.Order;
import com.example.springshop.entity.Person;
import com.example.springshop.entity.repository.OrderRepository;
import com.example.springshop.util.PersonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        var savedOrder = orderRepository.save(order);
//        try {
//            var subject = String.format("Заказ с номером %s успешно создан", savedOrder.getId());
//            var messageText = String.format("Заказ с номером %s успешно создан. Заказ будет доставлен в течении суток " +
//                    "на адрес %s", savedOrder.getId(), savedOrder.getDeliveryAddress());
//
//            if(PersonUtil.getCurrentPerson() == null || StringUtils.isBlank(PersonUtil.getCurrentPerson().getEmail())) {
//                System.out.println("Почта не заполнена");
//                return savedOrder;
//            }
//
//            MailService.sendMessage(PersonUtil.getCurrentPerson().getEmail(), subject, messageText);
//        } catch (GeneralSecurityException | IOException | MessagingException e) {
//            System.out.println("Не удалось отправить письмо");
//            e.printStackTrace();
//        }

        return savedOrder;
    }

    public List<Order> findAll(Person person) {
        return orderRepository.findAllByPerson(person);
    }
}
