package com.yingjie.springcloudstreamkafka;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.cloud.stream.binder.kafka.ListenerContainerWithDlqAndRetryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.BackOff;

import java.util.function.BiFunction;

@Configuration
@Slf4j
public class KafkaConfiguration {

  @Bean
  ListenerContainerWithDlqAndRetryCustomizer cust(KafkaTemplate<?, ?> template) {
    return new ListenerContainerWithDlqAndRetryCustomizer() {

      @Override
      public void configure(AbstractMessageListenerContainer<?, ?> container, String destinationName,
                            String group,
                            @Nullable BiFunction<ConsumerRecord<?, ?>, Exception, TopicPartition> dlqDestinationResolver,
                            @Nullable BackOff backOff) {

          DeadLetterPublishingRecoverer dlpr = new DeadLetterPublishingRecoverer(template,
              dlqDestinationResolver);
          dlpr.setExceptionHeadersCreator((kafkaHeaders, exception, isKey, headerNames) -> {
            var exceptionType = getRootCauseExceptionType(exception);
            kafkaHeaders.add("exception-type", exceptionType.getBytes());
          });
          DefaultErrorHandler handler = new DefaultErrorHandler(dlpr, backOff);
//          handler.addNotRetryableExceptions(IllegalStateException.class);
          container.setCommonErrorHandler(handler);

      }

      @Override
      public boolean retryAndDlqInBinding(String destinationName, String group) {
        return false;
      }

    };
  }


  private static String getRootCauseExceptionType(Throwable exception) {
    while (exception.getClass().getName().contains("org.springframework")) {
      exception = exception.getCause();
    }

    return exception.getClass().getSimpleName();
  }
}
