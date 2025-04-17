package tn.ucar.enicar.info.projetspring.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;
@Data
public class EventCreateDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String location;

    @Future
    private LocalDateTime startDate;

    @Future
    private LocalDateTime endDate;

    private String organization;
    private String technology;
    private String benefits; // "Bénéfice 1|Bénéfice 2"
    private String vision;
    private MultipartFile imageFile;


}
