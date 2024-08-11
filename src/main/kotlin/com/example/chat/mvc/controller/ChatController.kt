package com.example.chat.mvc.controller

import com.example.chat.mvc.model.Room
import com.example.chat.mvc.service.RoomManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class ChatController(
    private val roomManager: RoomManager
) {

    @GetMapping("/chat-rooms")
    fun findAllRooms(): List<Room> {
        return roomManager.findAll()
    }

    @PostMapping("/chat-rooms")
    fun createRoom(@RequestParam name: String): Room {
        return roomManager.save(name)
    }
}