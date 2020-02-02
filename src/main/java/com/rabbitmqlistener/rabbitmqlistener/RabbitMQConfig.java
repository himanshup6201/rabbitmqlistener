package com.rabbitmqlistener.rabbitmqlistener;

import com.rabbitmq.client.AMQP;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.awt.im.SimpleInputMethodWindow;


@Configuration
public class RabbitMQConfig {
    private static  final String MY_QUEUE="MyQueue";
    @Bean
    Queue myQueue(){
        return new Queue(MY_QUEUE,true);
    }
    @Bean
    Exchange myexchange12(){
        return ExchangeBuilder.topicExchange("MyTopicExchange1").durable(true).build();
    }
    @Bean
    ConnectionFactory connectionFactory(){
      CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory("localhost");
      cachingConnectionFactory.setUsername("guest");
      cachingConnectionFactory.setPassword("guest");
      return cachingConnectionFactory;
    }
    @Bean
    Binding binding(){
      //  return new Binding(MY_QUEUE,Binding.DestinationType.QUEUE,"MyTopicExchange1","topic",null);
        return  BindingBuilder.bind(myQueue()).to(myexchange12()).with("topic").noargs();
    }
    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer=new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener());
        return simpleMessageListenerContainer;
    }
}
