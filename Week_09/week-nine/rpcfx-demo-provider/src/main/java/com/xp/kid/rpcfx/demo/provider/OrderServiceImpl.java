package com.xp.kid.rpcfx.demo.provider;

import com.xp.kid.rpcfx.demo.api.Order;
import com.xp.kid.rpcfx.demo.api.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "ordertest" + System.currentTimeMillis(), 9.9f);
    }
}
