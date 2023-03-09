package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
@EnableBinding(value = {MyBinder.class})
public class SpringCloudStreamKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudStreamKafkaApplication.class, args);
  }

}
