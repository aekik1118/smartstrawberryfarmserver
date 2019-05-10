package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class EnvironmentInfoResource extends Resource<EnvironmentInfo> {

    public EnvironmentInfoResource(EnvironmentInfo content, Link... links) {
        super(content, links);
        add(linkTo(EnvironmentInfoController.class).slash(content.getId()).withSelfRel());
    }
}
