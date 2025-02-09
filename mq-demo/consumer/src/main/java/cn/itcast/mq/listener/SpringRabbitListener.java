package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    /*@RabbitListener(queues = "simpleQueue")
    public void listenerSimpleQueue(String message){
        System.out.println("收到的消息: " + message);
    }*/

    /*@RabbitListener(queues = "simpleQueue")
    public void listenerWorkQueue1(String message) throws InterruptedException {
        System.out.println("1收到的消息: " + message);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simpleQueue")
    public void listenerWorkQueue2(String message) throws InterruptedException {
        System.err.println("2收到的消息: " + message);
        Thread.sleep(200);
    }*/

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String message) {
        System.out.println("1收到的消息: 【 " + message + " 】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue2(String message) {
        System.out.println("2收到的消息: 【 " + message + " 】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenerDirectQueue1(String message) {
        System.out.println("1收到的消息: 【 " + message + " 】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenerDirectQueue2(String message) {
        System.out.println("2收到的消息: 【 " + message + " 】");
    }

    @RabbitListener(queues = "objectQueue")
    public void listenerObjectQueue(String message) {
        System.out.println("收到的消息: 【 " + message + " 】");
    }
}
