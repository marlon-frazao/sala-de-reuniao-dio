package com.marlonfrazao.saladereuniao.services;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marlonfrazao.saladereuniao.dto.RoomForm;
import com.marlonfrazao.saladereuniao.dto.RoomResponse;
import com.marlonfrazao.saladereuniao.model.Room;
import com.marlonfrazao.saladereuniao.repositories.RoomRepository;
import com.marlonfrazao.saladereuniao.services.exception.DateFormatException;
import com.marlonfrazao.saladereuniao.services.exception.ResourceNotFoundException;

@Service
public class RoomService {

	@Autowired
	private RoomRepository repository;
	
	@Transactional
	public RoomResponse insert(RoomForm form) {
		try {
			return repository.save(form.toEntity()).toResponse();
		} catch (ParseException e) {
			throw new DateFormatException("O formato da data deve seguir o exemplo: 05/12/2020 12:00");
		}
	}
	
	@Transactional
	public RoomResponse update(Long id, RoomForm form) {
		try {
			Room entity = repository.getById(id);
			entity = form.toEntity();
			entity.setId(id);
			return repository.save(entity).toResponse();
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		} catch(ParseException e) {
			throw new DateFormatException("O formato da data deve seguir o exemplo: 05/12/2020 12:00");
		}
	}
	
	@Transactional(readOnly = true)
	public List<RoomResponse> findAll() {
		return repository.findAll().stream().map(x -> x.toResponse()).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public RoomResponse findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada")).toResponse();
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Sala não encontrada");
		}
	}
}
