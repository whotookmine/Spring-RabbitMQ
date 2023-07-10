package com.time.rabbitmqdemo.producer;

import com.time.rabbitmqdemo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {

    @Value("${rabbit.mq.exchange.name}")
    private String exchange;
    @Value("${rabbit.mq.routing-key}")
    private String routingKey1;

    //Use Rabbit Template
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String msg){
        log.info("Send msg : {}",msg);
        //Pass Exchange , Routing Key and MESSAGE
        rabbitTemplate.convertAndSend(exchange,routingKey1,msg);
    }

}
