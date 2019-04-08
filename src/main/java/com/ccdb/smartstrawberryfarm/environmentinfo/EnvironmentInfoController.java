package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/environmentinfo", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EnvironmentInfoController {

    @Autowired
    private EnvironmentInfoMapper environmentInfoMapper;

    @PostMapping
    public ResponseEntity createEnvironmentInfo(@RequestBody @Valid EnvironmentInfo environmentInfo){
        Long rowsCnt = environmentInfoMapper.createEnvironmentInfo(environmentInfo);
        Long id = environmentInfo.getId();

        URI createdUri = linkTo(EnvironmentInfoController.class).slash(id).toUri();
        return ResponseEntity.created(createdUri).body(environmentInfo);
    }


}
