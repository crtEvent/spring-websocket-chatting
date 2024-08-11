package com.example.chat.mvc.model

data class Chat(
    val type: MessageType, // 메시지 타입
    val roomId: String,    // 방 번호
    val sender: String,    // 채팅을 보낸 사람
    var content: String,   // 메시지
    val time: String       // 채팅 발송 시간
) {

}