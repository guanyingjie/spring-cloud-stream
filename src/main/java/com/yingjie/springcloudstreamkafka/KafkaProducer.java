package com.yingjie.springcloudstreamkafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
  private final StreamBridge streamBridge;

  public void producer(String body) {
    log.info("producer");
    streamBridge.send("numberProducer-out-0", body);
  }
}
