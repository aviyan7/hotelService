package com.hotel.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.project.model.room.Status;
import com.hotel.project.dto.RoomCreateDto;
import com.hotel.project.dto.RoomResponseDto;
import com.hotel.project.dto.RoomResponseListDto;
import com.hotel.project.service.RoomService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/rooms")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RoomResponseDto addRoom(@RequestBody RoomCreateDto room) {
		return roomService.addRoom(room);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public RoomResponseListDto getRooms(@RequestParam(required = false) Status status) {
		return roomService.getRooms(status);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RoomResponseDto getRoomById(@PathVariable("id") Long id) {
		return roomService.getRoomById(id);
	}

//	@PutMapping("/{id}")
//	@ResponseStatus(code = HttpStatus.OK)
//	public RoomResponseDto updateRoom(@PathVariable("id") Long id,
//			@RequestBody RoomUpdateDto roomUpdateDto) {
//		return roomService.updateRoom(id, roomUpdateDto);
//	}

//	@DeleteMapping("/{id}")
//	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	public void deleteCarouselById(@PathVariable Long id) {
//		roomService.deleteRoomById(id);
//	

}
