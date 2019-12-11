package com.poongd.demo.common;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.validation.Errors;

import com.poongd.demo.index.IndexController;

public class ErrorsResource extends Resource<Errors>{
	public ErrorsResource(Errors content, Link... links) {
		super(content, links);
		add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
	}
}
