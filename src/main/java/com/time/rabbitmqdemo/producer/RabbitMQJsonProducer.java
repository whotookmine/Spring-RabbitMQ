package com.time.rabbitmqdemo.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.rabbitmqdemo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonProducer {
    @Value("${rabbit.mq.exchange.name}")
    private String exchange;
    @Value("${rabbit.mq-routing-json-key}")
    private String routekey;

    //Use Rabbit Template
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(UserDto msg){
        log.info("Send msg : {}",msg);
        //Pass Exchange , Routing Key and MESSAGE
        rabbitTemplate.convertAndSend(exchange,routekey,msg);
    }
}
