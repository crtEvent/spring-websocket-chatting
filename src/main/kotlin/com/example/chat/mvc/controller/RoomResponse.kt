package com.example.chat.mvc.controller

import com.example.chat.mvc.model.Room

data class RoomResponse(
    val id: String,
    val name: String,
) {
    companion object {
        @JvmStatic
        fun from(room: Room): RoomResponse {
            return RoomResponse(id = room.id, name = room.name)
        }
    }
}