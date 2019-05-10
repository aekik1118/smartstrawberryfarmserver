package com.ccdb.smartstrawberryfarm.pollution;

import com.ccdb.smartstrawberryfarm.environmentinfo.EnvironmentInfoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/pollution", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class PollutionController {

    @Autowired
    private PollutionMapper pollutionMapper;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    ResponseEntity createPollution(@RequestBody @Valid PollutionDto pollutionDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        Pollution pollution =  modelMapper.map(pollutionDto, Pollution.class);

        int flag = pollutionMapper.createPollution(pollution);
        String farmName = pollution.getFarmname();
        String area = pollution.getArea();

        ControllerLinkBuilder selfLinkBuilder = linkTo(PollutionController.class).slash(farmName).slash(area);
        URI createdUri = selfLinkBuilder.toUri();


        return ResponseEntity.created(createdUri).body(pollution);
    }

    @GetMapping
    ResponseEntity findPollution(String farmName, String area){
        Optional<Boolean> isPollution = pollutionMapper.isPollutionFarmArea(farmName,area);
        if(isPollution.isPresent()){
            return ResponseEntity.ok(isPollution.get());
        }

        return ResponseEntity.ok(false);
    }



}
