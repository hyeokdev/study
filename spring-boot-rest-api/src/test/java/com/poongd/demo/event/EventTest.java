package com.poongd.demo.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class EventTest {

	@Test
	public void builder() {
		Event event = Event.builder()
				.name("Inflearn Spring REST API")
				.description("REST API development with Spring")
				.build();
		assertThat(event).isNotNull();
	}
	
	@Test
	public void javaBean() {
		String name = "Event";
		String description = "Spring";
		
		Event event = new Event();
		event.setName(name);
		event.setDescription(description);
		
		assertThat(event.getName()).isEqualTo(name);
		assertThat(event.getDescription()).isEqualTo(description);
	}
	
	private Object[] parametersForTestFree() {
		return new Object[] {
				new Object[] {0,0,true},
				new Object[] {100,0,false},
				new Object[] {0,100,false},
				new Object[] {100,200,false}
		};
	}
	
	@Test
	@Parameters
	public void testFree(int basePrice, int maxPrice, boolean isFree) {
		Event event = Event.builder()
				.basePrice(basePrice)
				.maxPrice(maxPrice)
				.build();
		
		event.update();
		
		assertThat(event.isFree()).isEqualTo(isFree);
	}
	
	private Object[] parametersForTestOffLine() {
		return new Object[] {
				new Object[] {"강남",true},
				new Object[] {null,false},
				new Object[] {"  ",false}
		};
	}
	
	@Test
	@Parameters
	public void testOffLine(String location, boolean isOffLine) {
		Event event = Event.builder()
				.location(location)
				.build();
		
		event.update();
		
		assertThat(event.isOffLine()).isEqualTo(isOffLine);
	}
}
