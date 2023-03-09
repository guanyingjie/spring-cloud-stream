package com.yingjie.springcloudstreamkafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
  private final MyBinder myBinder;

  public void producer(String content) {
    myBinder.output().send(MessageBuilder.withPayload(content).build());
  }
}
