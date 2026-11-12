package com.bastidas.pqr.controller;

import com.bastidas.pqr.dto.CreatePqrRequest;
import com.bastidas.pqr.dto.PqrResponse;
import com.bastidas.pqr.service.PqrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pqrs")
@RequiredArgsConstructor
@Tag(name = "PQR Management", description = "Endpoints for creating, managing, and retrieving PQR requests in LexIA")
public class PqrController {

    private final PqrService pqrService;

    @PostMapping
    @Operation(summary = "File a new PQR", description = "Creates a new PQR ticket with an initial status of SUBMITTED and generates a unique filing number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PQR filed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public ResponseEntity<PqrResponse> createPqr(@Valid @RequestBody CreatePqrRequest request) {
        PqrResponse created = pqrService.createPqr(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "List all PQRs", description = "Retrieves all PQR tickets currently registered in the database.")
    @ApiResponse(responseCode = "200", description = "List of PQRs retrieved successfully")
    public ResponseEntity<List<PqrResponse>> getAllPqrs() {
        return ResponseEntity.ok(pqrService.getAllPqrs());
    }

    @GetMapping("/radicado/{radicado}")
    @Operation(summary = "Get PQR by filing number", description = "Retrieves the details of a specific PQR ticket using its unique filing number (radicado).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PQR found"),
            @ApiResponse(responseCode = "404", description = "PQR not found for the given filing number")
    })
    public ResponseEntity<PqrResponse> getPqrByRadicado(@PathVariable String radicado) {
        return ResponseEntity.ok(pqrService.getPqrByRadicado(radicado));
    }

    @GetMapping("/citizen/{citizenId}")
    @Operation(summary = "List PQRs by citizen ID", description = "Retrieves the history of all PQR tickets submitted by a specific citizen.")
    @ApiResponse(responseCode = "200", description = "Citizen PQR history retrieved successfully")
    public ResponseEntity<List<PqrResponse>> getPqrsByCitizen(@PathVariable String citizenId) {
        return ResponseEntity.ok(pqrService.getPqrsByCitizenId(citizenId));
    }
}
