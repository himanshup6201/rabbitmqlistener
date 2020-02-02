package com.rabbitmqlistener.rabbitmqlistener;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
    @Bean
    Queue exampleQueue(){
        return new Queue("exampleQueue",false);
    }
    @Bean
    Queue example2ndQueue(){
        return QueueBuilder.durable("example2ndQueue")
                .autoDelete().exclusive()
                .build();
    }
}
