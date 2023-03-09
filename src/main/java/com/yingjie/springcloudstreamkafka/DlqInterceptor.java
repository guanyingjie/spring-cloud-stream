package com.yingjie.springcloudstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
@GlobalChannelInterceptor(patterns = "dlq-*")
public class DlqInterceptor implements ChannelInterceptor {
//  @Override
//  public Message<?> preSend(@NotNull Message<?> message, @NotNull MessageChannel channel) {
//    log.info("[DLQ]-> preSend '{}' to '{}'", message.getHeaders().get("id"), channel);
//    return ChannelInterceptor.super.preSend(message, channel);
//  }

}
