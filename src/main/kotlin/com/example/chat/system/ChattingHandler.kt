package com.example.chat.system

import com.example.chat.mvc.model.ChatMessage
import com.example.chat.mvc.model.Room
import com.example.chat.mvc.service.MessageSender
import com.example.chat.mvc.service.RoomManager
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class ChattingHandler(
    private val mapper: ObjectMapper,
    private val roomManager: RoomManager,
    private val messageSender: MessageSender,
): TextWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload

        val chatMessage: ChatMessage = mapper.readValue(payload, ChatMessage::class.java)

        val chatRoom: Room = roomManager.findById(chatMessage.roomId)
            ?: throw RoomNotFoundException("Room with ID ${chatMessage.roomId} not found")

        messageSender.handleAction(session, chatRoom, chatMessage)
    }

}