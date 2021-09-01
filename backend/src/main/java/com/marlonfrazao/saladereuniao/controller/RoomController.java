package com.marlonfrazao.saladereuniao.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marlonfrazao.saladereuniao.dto.RoomForm;
import com.marlonfrazao.saladereuniao.dto.RoomResponse;
import com.marlonfrazao.saladereuniao.model.Room;
import com.marlonfrazao.saladereuniao.services.RoomService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/rooms")
public class RoomController {

	@Autowired
	private RoomService service;
	
	@GetMapping
	public ResponseEntity<List<RoomResponse>> getAllRooms() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomForm form) {
		RoomResponse response = service.insert(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomForm form) {
		return ResponseEntity.ok().body(service.update(id, form));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Room> deleteRoom(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
