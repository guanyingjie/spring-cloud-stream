package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
public class KafkaConsumer {

//  @Bean
//  public Consumer<Message<String>> consumer() {
//    return message -> {
//      String body = message.getPayload();
//      MessageHeaders messageHeaders = message.getHeaders();
//      log.info("Received message, TOPIC: {}; OFFSET:{},Message:{}",
//          messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
//          messageHeaders.get(KafkaHeaders.OFFSET, Long.class),
//          body);
//    };
//  }

  @StreamListener(MyBinder.INPUT)
  public void consumer(@Payload String message) throws InterruptedException {
    Thread.sleep(200);
    log.info("message consumed: {}", message);
    throw new NullPointerException();
  }
}
