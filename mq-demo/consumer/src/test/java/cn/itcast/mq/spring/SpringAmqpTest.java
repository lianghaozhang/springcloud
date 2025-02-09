package cn.itcast.mq.spring;

import cn.itcast.mq.listener.SpringRabbitListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private SpringRabbitListener rabbitListener;

    /*@Test
    public void listenerTest() {
        rabbitListener.listenerSimpleQueue();
    }*/
}
