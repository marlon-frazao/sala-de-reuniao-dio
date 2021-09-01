package com.marlonfrazao.saladereuniao.dto;

import java.io.Serializable;
import java.text.ParseException;

import com.marlonfrazao.saladereuniao.model.Room;

public class RoomForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String start;
	private String end;
	
	public RoomForm() {}

	public RoomForm(String name, String start, String end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	public Room toEntity() throws ParseException {
		return new Room(this);
	}
}
