package com.example.chat.mvc.service

import com.example.chat.mvc.model.Chat
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

    fun send(session: WebSocketSession, room: Room, chat: Chat) {
        when (chat.type) {
            MessageType.WELCOME -> {
                room.addSession(session)
                val welcomeChat = chat.toWelcome()
                room.sendMessageToSessions(makeMessage(welcomeChat))
            }
            MessageType.TALK -> {
                room.sendMessageToSessions(makeMessage(chat))
            }
            MessageType.LEAVE -> {
                room.removeSession(session)
                val leaveChat = chat.toLeave()
                room.sendMessageToSessions(makeMessage(leaveChat))
            }
        }
    }

    private fun makeMessage(chat: Chat): TextMessage {
        return TextMessage(mapper.writeValueAsString(chat))
    }

}