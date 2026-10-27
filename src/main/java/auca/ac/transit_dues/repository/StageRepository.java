package auca.ac.transit_dues.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import auca.ac.transit_dues.domain.Stage;

@Repository 
public interface StageRepository extends  JpaRepository<Stage, UUID> {

}


 