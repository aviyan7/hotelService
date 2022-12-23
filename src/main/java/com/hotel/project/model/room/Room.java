package com.hotel.project.model.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.hotel.project.model.room.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "room")
@NoArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int roomNumber;

	private String roomType;
	
	@Column(nullable = false)
	@Lob
	private String detail;

	private Long price;

	@Enumerated(EnumType.STRING)
	private Status status;
}
