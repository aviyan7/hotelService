package com.hotel.project.dto;

import com.hotel.project.model.room.Status;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RoomResponseDto {
	private long id;

	private int roomNumber;

	private String roomType;
	
	private String detail;

	private long price;

	private Status status;
}
