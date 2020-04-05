package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.user.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricRepository extends JpaRepository<Transfert, Long> {


}

