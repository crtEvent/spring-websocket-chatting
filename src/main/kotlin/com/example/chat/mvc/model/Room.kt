package com.example.chat.mvc.model

import org.slf4j.LoggerFactory
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.*
import kotlin.collections.HashSet

class Room private constructor(
    val roomId: String,  // 채팅방 아이디
    val name: String,    // 채팅방 이름
    private val socketSessions: MutableSet<WebSocketSession> = HashSet(), // 참여자들의 session들
) {

    companion object {
        fun create(name: String): Room {
            val roomId = UUID.randomUUID().toString()
            return Room(roomId = roomId, name = name)
        }
    }

    private val log = LoggerFactory.getLogger(Room::class.java)

    fun addSession(session: WebSocketSession) {
        socketSessions.add(session)
    }

    fun removeSession(session: WebSocketSession) {
        socketSessions.remove(session)
    }

    fun <T> sendMessageToSessions(message: WebSocketMessage<T>) {
        socketSessions.parallelStream().forEach { session ->
            try {
                session.sendMessage(message)
            } catch (e: IOException) {
                log.error("Failed to send message", e)
            }
        }
    }

}
