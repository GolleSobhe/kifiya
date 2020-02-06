package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.user.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

}
