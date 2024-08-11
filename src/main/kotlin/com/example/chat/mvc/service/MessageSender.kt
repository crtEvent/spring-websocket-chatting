package com.example.chat.mvc.service

import com.example.chat.mvc.model.ChatMessage
import com.example.chat.mvc.model.MessageType
import com.example.chat.mvc.model.Room
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class MessageSender(
    private val mapper: ObjectMapper
) {

    fun handleAction(session: WebSocketSession, room: Room, chatMessage: ChatMessage) {
        when (chatMessage.type) {
            MessageType.WELCOME -> {
                room.addSession(session)
                chatMessage.content = "${chatMessage.sender} 님이 입장하셨습니다"
                room.sendMessageToSessions(makeMessage(chatMessage))
            }
            MessageType.TALK -> {
                room.sendMessageToSessions(makeMessage(chatMessage))
            }
            MessageType.LEAVE -> {
                room.removeSession(session)
                chatMessage.content = "${chatMessage.sender} 님이 나가셨습니다"
                room.sendMessageToSessions(makeMessage(chatMessage))
            }
        }
    }

    private fun makeMessage(chatMessage: ChatMessage): TextMessage {
        return TextMessage(mapper.writeValueAsString(chatMessage))
    }

}