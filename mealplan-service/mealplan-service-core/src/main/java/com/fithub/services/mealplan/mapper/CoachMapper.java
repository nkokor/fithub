package com.fithub.services.mealplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fithub.services.mealplan.api.model.coach.CoachResponse;
import com.fithub.services.mealplan.dao.model.CoachEntity;
import com.fithub.services.mealplan.dao.model.UserEntity;

@Mapper(componentModel = "spring")
public interface CoachMapper {

    public List<CoachResponse> entitiesToDtos(List<CoachEntity> coachEntities);

}