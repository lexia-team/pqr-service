package com.bastidas.pqr.service;

import com.bastidas.pqr.dto.CreatePqrRequest;
import com.bastidas.pqr.dto.PqrResponse;

import java.util.List;

public interface PqrService {
    PqrResponse createPqr(CreatePqrRequest request);
    List<PqrResponse> getAllPqrs();
    PqrResponse getPqrByRadicado(String radicado);
    List<PqrResponse> getPqrsByCitizenId(String citizenId);
}
