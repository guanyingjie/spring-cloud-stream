package com.yingjie.springcloudstreamkafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class Controller {
  private final KafkaProducer kafkaProducer;
  
  @GetMapping
  public void producer(){
    kafkaProducer.producer("message");
  }
}
