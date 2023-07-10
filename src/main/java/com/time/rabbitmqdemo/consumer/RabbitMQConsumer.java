package com.time.rabbitmqdemo.consumer;

import com.time.rabbitmqdemo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${rabbit.mq.queue.name}"})
    public void consume(String msg){
        log.info("Recieve msg : {}",msg);
    }
    @RabbitListener(queues = {"${rabbit.mq.json-queue.name}"})
    public void consumeJson(UserDto user){
        log.info("Receive JSON : {}",user);
    }

}
