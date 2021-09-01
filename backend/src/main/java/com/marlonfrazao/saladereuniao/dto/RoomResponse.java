package com.marlonfrazao.saladereuniao.dto;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.marlonfrazao.saladereuniao.model.Room;

public class RoomResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter formatter =
		    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
		                     .withLocale( Locale.UK )
		                     .withZone( ZoneId.systemDefault() );
	
	private Long id;
	private String name;
	private String start;
	private String end;
	
	public RoomResponse() {}

	public RoomResponse(Long id, String name, String start, String end) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	public RoomResponse(Room entity) {
		id = entity.getId();
		name = entity.getName();
		start = formatter.format(entity.getStart());
		end = formatter.format(entity.getEnd());
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
