package com.bastidas.pqr.service.impl;

import com.bastidas.pqr.dto.CreatePqrRequest;
import com.bastidas.pqr.dto.PqrResponse;
import com.bastidas.pqr.model.Pqr;
import com.bastidas.pqr.model.PqrStatus;
import com.bastidas.pqr.repository.PqrRepository;
import com.bastidas.pqr.service.PqrService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PqrServiceImpl implements PqrService {

    private final PqrRepository pqrRepository;

    @Override
    @Transactional
    public PqrResponse createPqr(CreatePqrRequest request) {
        String generatedRadicado = generateUniqueRadicado();

        Pqr pqr = Pqr.builder()
                .radicado(generatedRadicado)
                .citizenId(request.getCitizenId())
                .type(request.getType())
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .status(PqrStatus.SUBMITTED)
                .build();

        Pqr savedPqr = pqrRepository.save(pqr);
        return PqrResponse.fromEntity(savedPqr);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PqrResponse> getAllPqrs() {
        return pqrRepository.findAll().stream()
                .map(PqrResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PqrResponse getPqrByRadicado(String radicado) {
        Pqr pqr = pqrRepository.findByRadicado(radicado)
                .orElseThrow(() -> new NoSuchElementException("No PQR found with filing number: " + radicado));
        return PqrResponse.fromEntity(pqr);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PqrResponse> getPqrsByCitizenId(String citizenId) {
        return pqrRepository.findByCitizenId(citizenId).stream()
                .map(PqrResponse::fromEntity)
                .toList();
    }

    private String generateUniqueRadicado() {
        int currentYear = Year.now().getValue();
        String radicado;
        do {
            int randomSeq = ThreadLocalRandom.current().nextInt(1000, 9999);
            radicado = String.format("PQR-%d-%04d", currentYear, randomSeq);
        } while (pqrRepository.existsByRadicado(radicado));
        return radicado;
    }
}
