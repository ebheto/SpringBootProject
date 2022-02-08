package com.qa.springfarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.springfarm.domain.Farm;
import com.qa.springfarm.repo.FarmRepo;

@Service
public class FarmService implements CRUDInterface<Farm> {
		 
	private FarmRepo farmRepo;
	
	public FarmService(FarmRepo farmRepo) {
		this.farmRepo = farmRepo;
	}

	@Override
	public Farm create(Farm farm) {
		return this.farmRepo.save(farm);
	}

	@Override
	public List<Farm> getAll() {
		return this.farmRepo.findAll();
	}

	@Override
	//Optional of allows for return to be null without throwing exception
	public Farm getById(long id) {
		Optional<Farm> optionalFarm = this.farmRepo.findById(id);
		if (optionalFarm.isPresent()){
			return optionalFarm.get();
		}
		return null;
	}

	//Optional of allows for return to be null without throwing exception
	@Override
	public Farm update(long id, Farm updatedFarm) {
		Optional<Farm> optionalFarm = this.farmRepo.findById(id);
		if (optionalFarm.isPresent()){
			Farm existingFarm = optionalFarm.get();
			existingFarm.setFarm_name(updatedFarm.getFarm_name());
			existingFarm.setAddress(updatedFarm.getAddress());
			existingFarm.setEmail(updatedFarm.getEmail());
			existingFarm.setTelephone(updatedFarm.getTelephone());
			
			return this.farmRepo.saveAndFlush(existingFarm);
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		boolean deleted = false;
		Optional<Farm> optionalFarm = this.farmRepo.findById(id);
		if (optionalFarm.isPresent()){
		this.farmRepo.deleteById(id);
		
		deleted = true;
		return deleted;
	}
	return deleted;
	
	}
}
