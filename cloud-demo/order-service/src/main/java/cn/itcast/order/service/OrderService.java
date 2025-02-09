package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.lianghaozhang.client.UserClient;
import com.lianghaozhang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.使用feignClient通过userId查询user
        User user = userClient.getUserById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }

    /*
    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.通过RestTemplate发送http请求，根据用户id查询用户信息
        User user = restTemplate.getForObject("http://userService/user/" + order.getUserId(), User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
