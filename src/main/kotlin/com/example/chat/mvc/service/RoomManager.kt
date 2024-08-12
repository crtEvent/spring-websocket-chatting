package com.example.chat.mvc.service

import com.example.chat.mvc.model.Room
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomManager {

    private var rooms: MutableMap<String, Room> = LinkedHashMap()

    init {
        val room1 = Room.create(name = "비밀의 방")
        val room2 = Room.create(name = "밤하늘 속 대화")
        val room3 = Room.create(name = "코드 스페이스")
        val room4 = Room.create(name = "스프링 개발자들")
        val room5 = Room.create(name = "코틀린 클럽")

        rooms[room1.id] = room1
        rooms[room2.id] = room2
        rooms[room3.id] = room3
        rooms[room4.id] = room4
        rooms[room5.id] = room5
    }


    fun findAll(): List<Room> {
        return ArrayList(rooms.values)
    }

    fun findById(roomId: String): Room? {
        return rooms[roomId]
    }

    fun save(name: String): Room {
        val room = Room.create(name)
        rooms[room.id] = room
        return room
    }
}