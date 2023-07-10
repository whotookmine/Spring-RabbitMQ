package com.time.rabbitmqdemo.controller;

import com.time.rabbitmqdemo.dto.UserDto;
import com.time.rabbitmqdemo.producer.RabbitMQProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class RabbitController {
    @Autowired
    private RabbitMQProducer rabbitMQProducer;



    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("msg") String msg){
        rabbitMQProducer.sendMessage(msg);
        return ResponseEntity.ok("msg send");
    }


}
