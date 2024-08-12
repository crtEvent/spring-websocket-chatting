package com.example.chat.mvc.model

data class Chat(
    val type: MessageType, // 메시지 타입
    val roomId: String,    // 방 번호
    val sender: String,    // 채팅을 보낸 사람
    val content: String,   // 메시지
    val time: String       // 채팅 발송 시간
) {

    fun toWelcome(): Chat {
        return Chat(
            type = MessageType.WELCOME,
            roomId = roomId,
            sender = sender,
            content = "$sender 님이 입장하셨습니다.",
            time = time)
    }

    fun toLeave(): Chat {
        return Chat(
            type = MessageType.LEAVE,
            roomId = roomId,
            sender = sender,
            content = "$sender 님이 나가셨습니다.",
            time = time)
    }

}