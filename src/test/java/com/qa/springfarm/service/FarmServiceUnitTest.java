package com.qa.springfarm.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.springfarm.domain.Farm;
import com.qa.springfarm.repo.FarmRepo;

@SpringBootTest
public class FarmServiceUnitTest {

	@Autowired
	private FarmService farmService;
	
	@MockBean
	private FarmRepo farmRepo;
	
	
	@Test
	public void createTest() {
		Farm testInput = new Farm("Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L);
		Farm mockOutput = new Farm(1, "Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L);

		Mockito.when(this.farmRepo.save(testInput)).thenReturn(mockOutput);
		
		assertEquals(mockOutput, this.farmService.create(testInput));
		
		Mockito.verify(this.farmRepo, Mockito.times(1)).save(testInput);
	}
	
	@Test
	public void getAllTest() {
		List<Farm> mockOutput = new ArrayList<Farm>();
		
		mockOutput.add(new Farm(1, "Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L));
		mockOutput.add(new Farm(2, "Manor farm", "Manor road", "oldmajor@animal.farm", 01154L));
		
		Mockito.when(this.farmRepo.findAll()).thenReturn(mockOutput);
		
		assertEquals(mockOutput, this.farmService.getAll());
		
		Mockito.verify(this.farmRepo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void getById() {
		long testInputValid = 2L;
		long testInputInvalid = 5L;
		Optional<Farm> mockOutputValid = 
				Optional.ofNullable(new Farm(2L, "Manor farm", "Manor road", "oldmajor@animal.farm", 01154L));
		Optional<Farm> mockOutputInvalid = Optional.ofNullable(null);
		
		Mockito.when(this.farmRepo.findById(testInputValid)).thenReturn(mockOutputValid);
		Mockito.when(this.farmRepo.findById(testInputInvalid)).thenReturn(mockOutputInvalid);
	
		assertEquals(mockOutputValid.get(),this.farmService.getById(testInputValid));
		assertEquals(null, this.farmService.getById(testInputInvalid));
		
		Mockito.verify(this.farmRepo, Mockito.times(1)).findById(testInputValid);
		Mockito.verify(this.farmRepo, Mockito.times(1)).findById(testInputInvalid);	
	}
	
	@Test
	public void testUpdate() {
		long testInputValidId = 4L;
		long testInputInvalidId = 5L;
		Farm testInputFarm = new Farm("Lleifior", "Powy", "von@cym.we", 32331950L);
		
		Optional<Farm> mockOutputValidId = Optional.ofNullable(new Farm(4L, "Lleifior", "Powy", "von@cym.we", 32331950L));
		Optional <Farm> mockOutputInvalidId = Optional.ofNullable(null);
		
		Farm methodResult = new Farm(4L, "Lleifior", "Powy", "von@cym.we", 32331950L);
		
		Mockito.when(this.farmRepo.findById(testInputValidId)).thenReturn(mockOutputValidId);
		Mockito.when(this.farmRepo.saveAndFlush(methodResult)).thenReturn(methodResult);
		Mockito.when(this.farmRepo.findById(testInputInvalidId)).thenReturn(mockOutputInvalidId);

		assertEquals(methodResult, this.farmService.update(testInputValidId, testInputFarm));
		assertEquals(null, this.farmService.update(testInputInvalidId, testInputFarm));

		Mockito.verify(this.farmRepo, Mockito.times(1)).findById(testInputValidId);	
		Mockito.verify(this.farmRepo, Mockito.times(1)).saveAndFlush(methodResult);
		Mockito.verify(this.farmRepo, Mockito.times(1)).findById(testInputInvalidId);		
	}
	
	@Test
	public void testDelete() {
		long testInput= 3L;
		
		Mockito.when(this.farmRepo.existsById(testInput)).thenReturn(false);
		
		assertEquals(true, this.farmService.delete(testInput));
		
		Mockito.verify(this.farmRepo, Mockito.times(1)).findById(testInput);
		Mockito.verify(this.farmRepo, Mockito.times(1)).deleteById(testInput);
	}
}