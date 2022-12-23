package com.hotel.project.dto;

import com.hotel.project.model.room.Status;

import lombok.Data;
@Data
public class RoomCreateDto {

	private static final long serialVersionUID = 1L;

	private int roomNumber;

	private String roomType;
	
	private String detail;

	private long price;

	private Status status;
}

