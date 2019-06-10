package cn.tycoding.scst.component.chat.config;

import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;

/**
 * @author tycoding
 * @date 2019-06-10
 */
@Component
@ServerEndpoint("/chat/{name}")
public class WebsocketEndpointConfig {


}
