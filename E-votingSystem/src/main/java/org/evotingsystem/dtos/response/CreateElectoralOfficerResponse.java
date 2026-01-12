package org.evotingsystem.dtos.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateElectoralOfficerResponse {
    @NotBlank
    private String message;
}
