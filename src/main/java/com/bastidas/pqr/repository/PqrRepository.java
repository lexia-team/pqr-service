package com.bastidas.pqr.repository;

import com.bastidas.pqr.model.Pqr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PqrRepository extends JpaRepository<Pqr, Long> {
    Optional<Pqr> findByRadicado(String radicado);
    List<Pqr> findByCitizenId(String citizenId);
    boolean existsByRadicado(String radicado);
}
