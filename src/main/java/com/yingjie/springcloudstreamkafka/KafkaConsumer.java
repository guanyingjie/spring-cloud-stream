package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class KafkaConsumer {

  @Bean
  public Consumer<Message<String>> consumer() {
    return message -> {
      String body = message.getPayload();
      MessageHeaders messageHeaders = message.getHeaders();
      log.info("Received message, TOPIC: {}; OFFSET:{},Message:{}",
          messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
          messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
          body);
    };
  }
}
