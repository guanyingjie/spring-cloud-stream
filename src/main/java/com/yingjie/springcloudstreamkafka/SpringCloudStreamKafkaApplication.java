package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringCloudStreamKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudStreamKafkaApplication.class, args);
  }

  @Bean
  public Consumer<String> consumer() {
    return message -> log.info("consumer message");
  }

  @Bean
  public Supplier<String> producer() {
    return () -> "message producer";
  }
}
