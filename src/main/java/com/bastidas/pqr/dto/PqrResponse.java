package com.bastidas.pqr.dto;

import com.bastidas.pqr.model.Pqr;
import com.bastidas.pqr.model.PqrStatus;
import com.bastidas.pqr.model.PqrType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Public representation of a PQR request")
public class PqrResponse {

    @Schema(description = "Internal database ID", example = "1")
    private Long id;

    @Schema(description = "Unique filing number (radicado)", example = "PQR-2026-0001")
    private String radicado;

    @Schema(description = "Citizen identifier", example = "usr-8f3a-4b92-81e2-9f3b5c6d7e8f")
    private String citizenId;

    @Schema(description = "PQR request type", example = "PETITION")
    private PqrType type;

    @Schema(description = "PQR title or subject", example = "Child support legal advisory request")
    private String title;

    @Schema(description = "Detailed description of the PQR")
    private String description;

    @Schema(description = "Legal category", example = "FAMILY")
    private String category;

    @Schema(description = "Current lifecycle status", example = "SUBMITTED")
    private PqrStatus status;

    @Schema(description = "Timestamp when the PQR was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the PQR was last updated")
    private LocalDateTime updatedAt;

    public static PqrResponse fromEntity(Pqr pqr) {
        return PqrResponse.builder()
                .id(pqr.getId())
                .radicado(pqr.getRadicado())
                .citizenId(pqr.getCitizenId())
                .type(pqr.getType())
                .title(pqr.getTitle())
                .description(pqr.getDescription())
                .category(pqr.getCategory())
                .status(pqr.getStatus())
                .createdAt(pqr.getCreatedAt())
                .updatedAt(pqr.getUpdatedAt())
                .build();
    }
}
