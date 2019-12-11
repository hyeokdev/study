package com.poongd.demo.event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;

public class EventResource extends Resource<Event> {

	public EventResource(Event event) {
		super(event);
		add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
	}
	
}
