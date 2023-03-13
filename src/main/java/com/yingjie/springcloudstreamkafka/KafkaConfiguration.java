package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

@Configuration
@Slf4j
public class KafkaConfiguration {

  @Bean
  public ListenerContainerCustomizer<AbstractMessageListenerContainer<String, String>> customizer(DefaultErrorHandler errorHandler) {
    return (container, dest, group) -> {
      container.setCommonErrorHandler(errorHandler);
    };
  }

  @Bean
  public DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {
    return new DefaultErrorHandler(deadLetterPublishingRecoverer);
  }

  @Bean
  public DeadLetterPublishingRecoverer publisher(KafkaOperations template) {
    DeadLetterPublishingRecoverer recover = new DeadLetterPublishingRecoverer(template);

    recover.setExceptionHeadersCreator((kafkaHeaders, exception, isKey, headerNames) -> {
      var exceptionType = getRootCauseExceptionType(exception);
      kafkaHeaders.add("exception-type", exceptionType.getBytes());
    });
    return recover;
  }


  private static String getRootCauseExceptionType(Throwable exception) {
    while (exception.getClass().getName().contains("org.springframework")) {
      exception = exception.getCause();
    }

    return exception.getClass().getSimpleName();
  }
}
