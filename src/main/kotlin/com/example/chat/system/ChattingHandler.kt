package com.example.chat.system

import com.example.chat.mvc.model.Chat
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

        val chat: Chat = mapper.readValue(payload, Chat::class.java)

        val room: Room = roomManager.findById(chat.roomId)
            ?: throw RoomNotFoundException("Room with ID ${chat.roomId} not found")

        messageSender.send(session, room, chat)
    }

}