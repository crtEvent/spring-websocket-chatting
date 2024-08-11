package com.example.chat.mvc.service

import com.example.chat.mvc.model.Room
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomManager {

    private var rooms: MutableMap<String, Room> = LinkedHashMap()

    fun findAll(): List<Room> {
        return ArrayList(rooms.values)
    }

    fun findById(roomId: String): Room? {
        return rooms[roomId]
    }

    fun save(name: String): Room {
        val chatRoom = Room.create(name)
        rooms[chatRoom.roomId] = chatRoom
        return chatRoom
    }
}