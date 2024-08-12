package com.example.chat.mvc.controller

import com.example.chat.mvc.model.Room
import com.example.chat.mvc.service.RoomManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class ChatController(
    private val roomManager: RoomManager
) {

    @GetMapping("/rooms")
    fun findAllRooms(): List<RoomResponse> {
        return roomManager.findAll().map { room ->
            RoomResponse.from(room)
        }
    }

    @PostMapping("/rooms")
    fun createRoom(@RequestParam name: String): RoomResponse {
        return RoomResponse.from(roomManager.save(name))
    }
}