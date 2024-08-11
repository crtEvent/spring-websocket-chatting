package com.example.chat.system

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class SpringConfig(
    private val webSocketHandler: WebSocketHandler
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        // Socket endpoint 설정 : /ws/chat
        // Socket endpoint로 요청이 들어오면 websocket 통신을 진행한다.
        registry
            .addHandler(webSocketHandler, "/ws/chat")
            .setAllowedOrigins("*")
    }
}