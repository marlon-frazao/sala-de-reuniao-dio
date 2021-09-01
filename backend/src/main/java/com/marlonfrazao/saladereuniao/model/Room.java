package com.marlonfrazao.saladereuniao.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.marlonfrazao.saladereuniao.dto.RoomForm;
import com.marlonfrazao.saladereuniao.dto.RoomResponse;

@Entity
@Table(name = "meeting_room")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant start;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant end;
	
	public Room() {}

	public Room(Long id, String name, Instant start, Instant end) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	public Room(RoomForm form) throws ParseException {
		name = form.getName();
		start = sdf.parse(form.getStart()).toInstant();
		end = sdf.parse(form.getEnd()).toInstant();
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

	public Instant getStart() {
		return start;
	}

	public void setStart(Instant start) {
		this.start = start;
	}

	public Instant getEnd() {
		return end;
	}

	public void setEnd(Instant end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + "]";
	}
	
	public RoomResponse toResponse() {
		return new RoomResponse(this);
	}
}
