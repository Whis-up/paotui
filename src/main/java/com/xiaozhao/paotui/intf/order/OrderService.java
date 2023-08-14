package com.xiaozhao.paotui.intf.order;


import com.xiaozhao.paotui.intf.entity.Order;


public interface OrderService {

    int loadById(int id);

    int grabOrder(Order order);

    int update(Order order);



}
