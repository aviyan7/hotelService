package com.hotel.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.project.dto.RoomCreateDto;
import com.hotel.project.dto.RoomResponseDto;
import com.hotel.project.dto.RoomResponseListDto;
import com.hotel.project.model.room.*;
import com.hotel.project.repository.RoomRepository;
@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepository;

	private RoomResponseDto getRoomResponseDto(Room savedRoom) {
		RoomResponseDto response = new RoomResponseDto();
		if (savedRoom != null) {
			response.setId(savedRoom.getId());
			response.setRoomNumber(savedRoom.getRoomNumber());
			response.setRoomType(savedRoom.getRoomType());
			response.setPrice(savedRoom.getPrice());
			response.setDetail(savedRoom.getDetail());
			response.setStatus(savedRoom.getStatus());	
		}
		return response;
	}

	public RoomResponseDto addRoom(RoomCreateDto request) {
		Room room = new Room();
		room.setRoomNumber(request.getRoomNumber());
		room.setRoomType(request.getRoomType());
		room.setPrice(request.getPrice());
		room.setDetail(request.getDetail());
		room.setStatus(request.getStatus());	
		
		Room savedRoom = roomRepository.save(room);
		return getRoomResponseDto(savedRoom);
	}

	public RoomResponseListDto getRooms(Status status) {
		List<RoomResponseDto> roomResponseList = new ArrayList<>();
		List<Room> rooms;
		if (status == null) {
			rooms = roomRepository.findAll();
		} else {
			rooms = roomRepository.findByStatus(status);
		}
		
		for (Room room : rooms) {
			roomResponseList.add(getRoomResponseDto(room));
		}
		
		RoomResponseListDto response = new RoomResponseListDto();
		response.setRooms(roomResponseList);
		response.setTotal((long) roomResponseList.size());
		System.out.println(response);
		return response;
	}

	public RoomResponseDto getRoomById(Long id) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		if (optionalRoom.isPresent()) {
			return getRoomResponseDto(optionalRoom.get());
		}
		return null;
	}

//	public TestimonialResponseDto updateTestimonial(Long id, TestimonialUpdateDto testimonialUpdateDto,
//			Long CustomerId) {
//		Optional<Testimonial> optionalTestimonial = testimonialRepository.findByIdAndCustomerId(id,
//				requestFilter.getCustomerId());
//		if (optionalTestimonial.isPresent()) {
//			Testimonial testimonial = optionalTestimonial.get();
//			testimonial.setName(testimonialUpdateDto.getName());
//			testimonial.setDescription(testimonialUpdateDto.getDescription());
//			testimonial.setDesignation(testimonialUpdateDto.getDesignation());
//			testimonial.setStatus(testimonialUpdateDto.getStatus());
//			String tempKey = testimonial.getImageKey();
//			if (testimonialUpdateDto.getTestimonialImage() != null
//					&& !testimonialUpdateDto.getTestimonialImage().isEmpty()) {
//				testimonial.setImageKey(fileService.uploadFile(testimonialUpdateDto.getTestimonialImage(),
//						FileType.IMAGE, "testimonialimage.png"));
//				if (tempKey != null)
//					fileService.deleteFile(tempKey);
//			}
//			Testimonial savedTestimonial = testimonialRepository.save(testimonial);
//			return getTestimonialResponseDto(savedTestimonial);
//		}
//		return null;
//	}
//
//	@Transactional
//	public void deleteTestimonialById(Long id, Long CustomerId) throws Exception {
//		Optional<Testimonial> testimonial = testimonialRepository.findByIdAndCustomerId(id,
//				requestFilter.getCustomerId());
//		if (testimonial.isPresent()) {
//			Testimonial testimonials = testimonial.get();
//			String tempKey = testimonials.getImageKey();
//			if (tempKey != null)
//				fileService.deleteFile(tempKey);
//			testimonialRepository.deleteByIdAndCustomerId(id, requestFilter.getCustomerId());
//		} else {
//			throw new Exception("Testimonial with id " + id + " not found");
//		}
//	}
}
