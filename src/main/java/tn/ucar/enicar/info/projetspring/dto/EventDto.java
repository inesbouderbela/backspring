package tn.ucar.enicar.info.projetspring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventDto {
    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;


}

