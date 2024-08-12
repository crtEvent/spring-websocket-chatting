package com.example.chat.mvc.controller

import com.example.chat.mvc.model.Chat
import com.example.chat.mvc.model.MessageType
import java.time.Instant

data class ChatRequest(
    val type: MessageType,
    val roomId: String,
    val sender: String,
    val content: String,
) {

    fun to(now: Instant): Chat {
        return Chat(
            type = type,
            roomId = roomId,
            sender = sender,
            content = content,
            time = now.toString()
        )
    }

}