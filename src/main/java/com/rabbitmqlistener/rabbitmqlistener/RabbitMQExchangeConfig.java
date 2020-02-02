package com.rabbitmqlistener.rabbitmqlistener;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig {
    @Bean
    Exchange exampleExchange(){
        return  new TopicExchange("ExampleExchange");
    }
    @Bean
    Exchange example2Exchange(){
        return ExchangeBuilder.directExchange("Example2Exchange")
                .autoDelete().internal().build();
    }
    @Bean
    Exchange newExchange(){
        return  ExchangeBuilder.topicExchange("Exchangetopic3").autoDelete().durable(true).internal().build();
    }
    @Bean
    Exchange fanoutExc(){
        return  ExchangeBuilder.fanoutExchange("Exchangefan").autoDelete().durable(false).internal().build();
    }
    @Bean
    Exchange headerexc(){
        return  ExchangeBuilder.headersExchange("headerExchange").autoDelete().durable(true).internal().ignoreDeclarationExceptions().build();
    }
}
