package tn.ucar.enicar.info.projetspring.dto;

import lombok.Data;
import tn.ucar.enicar.info.projetspring.enums.PosteStatus;
import tn.ucar.enicar.info.projetspring.enums.PosteType;

@Data
public class PosteCreationRequest {
    private String title;
    private PosteType type;
    private PosteStatus status;
    private String description;
    private Long eventId;
    private Long teamId; // nullable si coordinator
}

