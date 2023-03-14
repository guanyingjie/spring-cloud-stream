package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class KafkaConsumer {

  @Bean
  public Consumer<String> numberConsumer() {
    return message -> {
      log.info("receive message : {}", message);
      if (message.equals("message")){
        throw new IllegalStateException();
      }
      throw new NullPointerException();
    };
  }

}
