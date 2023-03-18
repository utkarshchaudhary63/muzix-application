package com.capstone2.MovieService.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    //exchange,queue,converter,rabbitTemplate,binding

    private String exchange_name="mail_exchange";
    private String queue_name="mail_queue";

    //queue bean
    @Bean
    public Queue getQueue(){
        return new Queue(queue_name);
    }
    //exchangeBean
    @Bean
    public DirectExchange getDirectExchange(){
    return new DirectExchange(exchange_name);
    }
    //convertor bean
    @Bean
    public Jackson2JsonMessageConverter getMessageConvertor(){
        return new Jackson2JsonMessageConverter();
    }
    //rabbittemplate bean

    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConvertor());
        return rabbitTemplate;
    }
    //binding bean: exchange+queue (routing)
    @Bean
    public Binding getBinding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("mail_binding");
    }
}
