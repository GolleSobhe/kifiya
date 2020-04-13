package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Transfert, Long> {


}

