package com.hotel.project.dto;

import java.util.List;

import com.hotel.project.dto.RoomResponseDto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RoomResponseListDto {
	private List<RoomResponseDto> rooms;

	private Long total;
}
