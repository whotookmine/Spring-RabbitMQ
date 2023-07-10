package com.time.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.mq.queue.name}")
    private String queueName;
    @Value("${rabbit.mq.json-queue.name}")
    private String queueName2;

    @Value("${rabbit.mq.exchange.name}")
    private String exchange;

    @Value("${rabbit.mq.routing-key}")
    private String routingKey1;
    @Value("${rabbit.mq-routing-json-key}")
    private String routingKey2;
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }
    @Bean
    public Queue jsonQueue(){
        return new Queue(queueName2);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    //Binding 1st Queue
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey1);
    }
    //Binding 2nd Queue
    @Bean
    public Binding binding2(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingKey2);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


}
