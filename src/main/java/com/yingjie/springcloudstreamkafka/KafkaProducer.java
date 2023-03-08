package com.yingjie.springcloudstreamkafka;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
  private final StreamBridge streamBridge;

  public void producer(String message) {
    streamBridge.send("producer-out-0",
        MessageBuilder.withPayload(message).build());
  }
}
