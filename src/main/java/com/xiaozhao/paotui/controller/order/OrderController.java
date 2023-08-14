package com.xiaozhao.paotui.controller.order;

import com.xiaozhao.paotui.controller.BaseController;
import com.xiaozhao.paotui.intf.demand.DO.ResponseDO;
import com.xiaozhao.paotui.intf.demand.DemandDetailService;
import com.xiaozhao.paotui.intf.entity.Order;
import com.xiaozhao.paotui.intf.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    /**
     * 利用乐观锁思想抢单
     * @param order
     * @return
     */
    @PostMapping
    public ResponseDO grabOrder(@RequestBody Order order){
        log.info("订单信息 {}", order);
        int i = orderService.grabOrder(order);
        if (i == 1){
            return new ResponseDO(true,SUCCESS,null);
        }
        return new ResponseDO(false,FAIL,null);


    }
}
