package com.capstone2.MovieService.rabbitmq;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailProducer {
    //Injecting dependencies:rabbitTemplate,exchange
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    public void sendEmailDtoToQueue(EmailDTO emailDTO){
        //binding name: mail binding
        rabbitTemplate.convertAndSend(directExchange.getName(),"mail_binding",emailDTO);
    }
}
