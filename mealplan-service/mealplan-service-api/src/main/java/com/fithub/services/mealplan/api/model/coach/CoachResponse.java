package com.fithub.services.mealplan.api.model.coach;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Properties of an coach response object")
public class CoachResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long coachId;
    
    private String coachName;
    
    private String coachSurname;
    
}