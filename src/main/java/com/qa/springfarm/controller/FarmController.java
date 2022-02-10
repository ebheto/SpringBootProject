package com.qa.springfarm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springfarm.domain.Farm;
import com.qa.springfarm.service.FarmService;

@RestController
@RequestMapping("/Farm")
public class FarmController {
	
	private FarmService farmService;
	
	public FarmController(FarmService farmService) {
		this.farmService = farmService;
	}
	
	//Create
	@PostMapping("/createFarm")
	public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
		return new ResponseEntity<Farm>(this.farmService.create(farm),
				                                                      HttpStatus.CREATED);
	}
	
	//Read All
	@GetMapping("/getall")
	public ResponseEntity<List<Farm>> getAllFarms() { 
		return new ResponseEntity<List<Farm>>(this.farmService.getAll(),
				                                                        HttpStatus.OK);
	}	
	
	//Read By Id
	@GetMapping("/getById/{id}")
	public ResponseEntity<Farm> getFarmById(@PathVariable long id) {
		return new ResponseEntity<Farm>(this.farmService.getById(id),
				                                                     HttpStatus.OK);
	}
	
	//Update
	@PutMapping("/updateFarm/{id}")
	public ResponseEntity<Farm> updateFarm(@PathVariable long id, @RequestBody Farm farm) {
		return new ResponseEntity<Farm>(this.farmService.update(id, farm),
				                                                    HttpStatus.ACCEPTED);
	}
	
	//Delete
	@Transactional
	@DeleteMapping("/deleteFarm/{id}")
	public ResponseEntity<Boolean> deleteFarm(@PathVariable long id) {
		boolean deleted = this.farmService.delete(id);
		
		return (deleted) 
				        ? new ResponseEntity<Boolean>(deleted, HttpStatus.OK)
		    			: new ResponseEntity<Boolean>(deleted, HttpStatus.NOT_FOUND);
	}
	
}