package com.project.backend.mapper;

import com.project.backend.dto.ApplicationDto;
import com.project.backend.entity.Application;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public interface IApplicationMapper {

    @Named("ApplicationDtoToApplicationEntity")
    Application ApplicationDtoToApplicationEntity(ApplicationDto applicationDto);

    @Named("ApplicationEntityToApplicationDto")
    ApplicationDto ApplicationEntityToApplicationDto(Application application);

    @IterableMapping(qualifiedByName = "ApplicationEntitiesToApplicationDto")
    List<ApplicationDto> ApplicationEntitiesToApplicationDto(List<Application> applications);
}
