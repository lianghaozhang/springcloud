package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId,
                                    @RequestParam(value = "isEnableDefaultFilter", required = false) String isEnableDefaultFilter) {
        System.out.println("isEnableDefaultFilter: " + isEnableDefaultFilter);
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }
}
