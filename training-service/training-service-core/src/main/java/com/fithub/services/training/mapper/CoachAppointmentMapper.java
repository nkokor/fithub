package com.fithub.services.training.mapper;

import java.util.List;


import org.mapstruct.Mapper;

import com.fithub.services.training.api.model.appointment.CoachAppointmentResponse;
import com.fithub.services.training.dao.model.AppointmentEntity;

@Mapper(componentModel = "spring")
public interface CoachAppointmentMapper {

    public List<CoachAppointmentResponse> entitiesToDtos(List<AppointmentEntity> appointmentEntities);

}