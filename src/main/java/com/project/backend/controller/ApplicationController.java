package com.project.backend.controller;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.service.IApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final IApplicationService applicationService;
    @Autowired
    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @ApiOperation(value = "List all applications.")
    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAll(){
        log.info("ApplicationController: Request to the ApplicationService to list all applications");
        return new ResponseEntity<>(applicationService.getAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Get application by identity number.")
    @GetMapping("/get-status/{identiyNumber}")
    public ResponseEntity<ApplicationDto> getStatus(@PathVariable("identiyNumber") String identiyNumber){
        log.info("ApplicationController: Request to the ApplicationService to fetch application with identity number");
        return new ResponseEntity<>(applicationService.getStatus(identiyNumber), HttpStatus.OK);
    }


}

