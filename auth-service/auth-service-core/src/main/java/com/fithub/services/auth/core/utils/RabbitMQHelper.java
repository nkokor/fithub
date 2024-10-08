package com.fithub.services.auth.core.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fithub.services.auth.api.rabbitmq.ClientRegistrationMessage;
import com.fithub.services.auth.api.rabbitmq.CoachCapacityUpdateMessage;
import com.fithub.services.auth.dao.model.UserEntity;
import com.fithub.services.auth.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMQHelper {

    @Value("${rabbitmq.queue.client-registration-training}")
    private String clientRegistrationTrainingQueueTitle;

    @Value("${rabbitmq.queue.client-registration-chat}")
    private String clientRegistrationChatQueueTitle;

    @Value("${rabbitmq.queue.client-registration-membership}")
    private String clientRegistrationMembershipQueueTitle;

    @Value("${rabbitmq.queue.client-registration-mealplan}")
    private String clientRegistrationMealplanQueueTitle;

    @Value("${rabbitmq.queue.coach-capacity-update-training}")
    private String coachCapacityUpdateTrainingQueueTitle;

    @Value("${rabbitmq.exchange.direct}")
    private String directExchangeTitle;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;

    private <T> String writeObjectAsJsonString(final T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public void sendClientRegistrationEventToQueue(final UserEntity newClient) throws JsonProcessingException {
        final ClientRegistrationMessage clientRegistrationMessage = userMapper.entityToClientRegistrationMessage(newClient);
        final String newClientJsonString = writeObjectAsJsonString(clientRegistrationMessage);

        rabbitTemplate.convertAndSend(directExchangeTitle, clientRegistrationTrainingQueueTitle, newClientJsonString);
        rabbitTemplate.convertAndSend(directExchangeTitle, clientRegistrationChatQueueTitle, newClientJsonString);
        rabbitTemplate.convertAndSend(directExchangeTitle, clientRegistrationMembershipQueueTitle, newClientJsonString);
        rabbitTemplate.convertAndSend(directExchangeTitle, clientRegistrationMealplanQueueTitle, newClientJsonString);
    }

    public void sendCoachCapacityUpdateEventToQueue(final CoachCapacityUpdateMessage message) throws JsonProcessingException {
        final String messageJsonString = writeObjectAsJsonString(message);

        rabbitTemplate.convertAndSend(directExchangeTitle, coachCapacityUpdateTrainingQueueTitle, messageJsonString);
    }

}