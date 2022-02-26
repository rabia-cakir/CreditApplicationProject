package com.project.backend.controller;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.service.IApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    IApplicationService applicationService;

    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAll()
    {
        return new ResponseEntity<>(applicationService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/get-status/{identiyNumber}")
    public ResponseEntity<ApplicationDto> getStatus(@PathVariable("identityNumber") String identityNumber)
    {
        log.info("ApplicationController : Credit status information has been fetched");
        return new ResponseEntity<>(applicationService.getStatus(identityNumber), HttpStatus.OK);
    }
}
