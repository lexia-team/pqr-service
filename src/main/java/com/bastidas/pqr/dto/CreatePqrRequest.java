package com.bastidas.pqr.dto;

import com.bastidas.pqr.model.PqrType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Payload required to file a new PQR request in LexIA")
public class CreatePqrRequest {

    @NotBlank(message = "Citizen identifier is required")
    @Size(max = 36, message = "Citizen ID must not exceed 36 characters")
    @Schema(description = "Decoupled unique identifier of the citizen", example = "usr-8f3a-4b92-81e2-9f3b5c6d7e8f")
    private String citizenId;

    @NotNull(message = "PQR type is required")
    @Schema(description = "Type of request (PETITION, COMPLAINT, CLAIM, SUGGESTION, TUTELA)", example = "PETITION")
    private PqrType type;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 150, message = "Title must be between 5 and 150 characters")
    @Schema(description = "Subject or title of the request", example = "Child support legal advisory request")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    @Schema(description = "Detailed description of the facts or legal petition", example = "I hereby formally request guidance regarding child support custody proceedings...")
    private String description;

    @Size(max = 50, message = "Category must not exceed 50 characters")
    @Schema(description = "Legal area or category", example = "FAMILY")
    private String category;
}
