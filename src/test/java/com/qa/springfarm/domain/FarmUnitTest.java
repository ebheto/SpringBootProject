package com.qa.springfarm.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class FarmUnitTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Farm.class).usingGetClass().
		withPrefabValues(Farm.class, new Farm("Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L), new Farm()).verify();
	}

	@Test
	public void testConstructorWithId() {
		Farm farm = new Farm(1, "Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L);
		assertNotNull(farm.getId());
		assertNotNull(farm.getFarm_name());
		assertNotNull(farm.getAddress());
		assertNotNull(farm.getEmail());
		assertNotNull(farm.getTelephone());
		
		assertEquals(1, farm.getId());
		assertEquals("Old Macdonald", farm.getFarm_name());
		assertEquals("EIEIO", farm.getAddress());
		assertEquals("oldmac@moo.com", farm.getEmail());
		assertEquals((Long)43658709L, farm.getTelephone());
	}

	@Test
	public void testConstructorWithoutId() {
		Farm farm = new Farm("Twelve Oaks", "Clayton County", "wilkes@atl.ca", 51936L);
		assertNotNull(farm.getId());
		assertNotNull(farm.getFarm_name());
		assertNotNull(farm.getAddress());
		assertNotNull(farm.getEmail());
		assertNotNull(farm.getTelephone());
		
		assertEquals(0, farm.getId());
		assertEquals("Twelve Oaks", farm.getFarm_name());
		assertEquals("Clayton County", farm.getAddress());
		assertEquals("wilkes@atl.ca", farm.getEmail());
		assertEquals((Long)51936L, farm.getTelephone());
	}
	
	@Test
	public void testSetters() {
       
		Farm farm = new Farm();
		
		farm.setId(1);
		farm.setFarm_name("Old Macdonald");
		farm.setAddress("EIEIO");
	    farm.setEmail("oldmac@moo.com");
		farm.setTelephone(43658709L);
		
		assertNotNull(farm.getId());
		assertNotNull(farm.getFarm_name());
		assertNotNull(farm.getAddress());
		assertNotNull(farm.getEmail());
		assertNotNull(farm.getTelephone());
		
		assertEquals(1, farm.getId());
		assertEquals("Old Macdonald", farm.getFarm_name());
		assertEquals("EIEIO", farm.getAddress());
		assertEquals("oldmac@moo.com", farm.getEmail());
		assertEquals((Long)43658709L, farm.getTelephone());
	}

	@Test
	public void testToString() {
		Farm farm = new Farm(1, "Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L);
		
		assertNotNull("Farm [Id = 1" + 1 + ", farm_name = Old Macdonald " + "Old Macdonald" + ", address = EIEIO " + "EIEIO" + ", email = oldmac@moo.com " + "olmac@moo.com" + ", telephone = 43658709" + 43658709 + "]");
		
		assertEquals(
			"Farm [Id=1, farm_name=Old Macdonald, address=EIEIO, email=oldmac@moo.com, telephone=43658709]",
			farm.toString());
	}

}