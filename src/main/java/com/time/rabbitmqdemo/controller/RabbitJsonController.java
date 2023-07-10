package com.time.rabbitmqdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.time.rabbitmqdemo.dto.UserDto;
import com.time.rabbitmqdemo.producer.RabbitMQJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2")
public class RabbitJsonController {
    @Autowired
    private RabbitMQJsonProducer rabbitMQJsonProducer;
    @PostMapping("/produce-user")
    public ResponseEntity<String> sendJson(@RequestBody UserDto user) {
        rabbitMQJsonProducer.sendMessage(user);
        return ResponseEntity.ok("Json send");
    }
}
