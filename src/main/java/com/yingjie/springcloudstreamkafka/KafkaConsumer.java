package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
public class KafkaConsumer {

  @StreamListener(MyBinder.INPUT)
  public void consumer(@Payload String message) throws InterruptedException {
    Thread.sleep(200);
    log.info("message consumed: {}", message);
    throw new NullPointerException();
  }
}
