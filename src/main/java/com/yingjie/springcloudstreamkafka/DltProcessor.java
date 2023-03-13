package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class DltProcessor {
  public void processDltMessage(ConsumerRecord<String, String> consumerRecord) {
    var exceptionTypeIterator= consumerRecord.headers().headers("exception-type").iterator();
    String exceptionType = new String(exceptionTypeIterator.next().value(), StandardCharsets.UTF_8);
    log.info("Dead letter message received with exception: " + exceptionType);
  }
}
