package auca.ac.transit_dues.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.ac.transit_dues.domain.DuesType;

@Repository 
public interface DuesTypeRepository extends JpaRepository<DuesType, UUID>{

    
    
}