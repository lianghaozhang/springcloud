package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*@Test
    public void testSendMessage2SimpleQueue(){
        String queueName = "simpleQueue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }*/

    /*@Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simpleQueue";
        String message = "message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }*/

    @Test
    public void testSendMessage2FanoutExchange() {
        String exchange = "itcast.fanout";
        String message = "hello, every one!";
        rabbitTemplate.convertAndSend(exchange, "", message);
    }

    @Test
    public void testSendMessage2DirectExchange() {
        String exchange = "itcast.direct";
        String key = "red";
        rabbitTemplate.convertAndSend(exchange, key, "hello, " + key + "!");
    }

    @Test
    public void testObjectQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "阿牛");
        map.put("age", "21");
        rabbitTemplate.convertAndSend("objectQueue", map);
    }
}
