package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/environmentinfo", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EnvironmentInfoController {

    @Autowired
    private EnvironmentInfoMapper environmentInfoMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EnvironmentInfoValidator environmentInfoValidator;

    @PostMapping
    public ResponseEntity createEnvironmentInfo(@RequestBody @Valid EnvironmentInfoDto environmentInfoDto, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        environmentInfoValidator.validate(environmentInfoDto, errors);

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        EnvironmentInfo environmentInfo = modelMapper.map(environmentInfoDto, EnvironmentInfo.class);

        Long rowsCnt = environmentInfoMapper.createEnvironmentInfo(environmentInfo);
        Long id = environmentInfo.getId();

        ControllerLinkBuilder selfLinkBuilder = linkTo(EnvironmentInfoController.class).slash(id);
        URI createdUri = selfLinkBuilder.toUri();

        EnvironmentInfoResource environmentInfoResource = new EnvironmentInfoResource(environmentInfo);
        environmentInfoResource.add(linkTo(EnvironmentInfoController.class).withRel("query-events"));

        return ResponseEntity.created(createdUri).body(environmentInfoResource);
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity getEnvironmentInfo(String farmName, String area, String beginTime, String endTime){
        List<EnvironmentInfo> environmentInfoList = environmentInfoMapper.selectallbyTime(farmName, area, beginTime, endTime);
        return ResponseEntity.ok(environmentInfoList);
    }

    @GetMapping("/recent")
    @CrossOrigin
    public ResponseEntity getRecentEnvInfo(String farmName, String area){
        EnvironmentInfo environmentInfo = environmentInfoMapper.selectRecent(farmName, area);
        return ResponseEntity.ok(environmentInfo);
    }


}
