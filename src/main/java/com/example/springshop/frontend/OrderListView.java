package com.example.springshop.frontend;

import com.example.springshop.entity.Order;
import com.example.springshop.service.OrderService;
import com.example.springshop.util.PersonUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

@Route("order")
public class OrderListView extends AbstractView{
    private final Grid<Order> orderGrid = new Grid<Order>(Order.class);

    private final OrderService orderService;

    public OrderListView(OrderService orderService) {
        this.orderService = orderService;

        initPage();
    }

    private void initPage() {
        initOrderGrid();
        add(orderGrid);
    }

    private void initOrderGrid() {
        var orders = orderService.findAll(PersonUtil.getCurrentPerson());
        orderGrid.setItems(orders);
        orderGrid.setColumns("id", "cost", "deliveryAddress", "createdAt");
        orderGrid.setSizeUndefined();
    }


}
