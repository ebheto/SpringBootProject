package com.qa.springfarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springfarm.domain.Farm;

@Repository
public interface FarmRepo extends JpaRepository <Farm, Long> {

}
