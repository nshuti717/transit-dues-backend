package auca.ac.transit_dues.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import auca.ac.transit_dues.domain.Stage;

@Repository 
public interface StageRepository extends  JpaRepository<Stage, UUID> {
    @Query("SELECT s FROM Stage s WHERE NOT EXISTS (SELECT t FROM Treasurer t WHERE t.stage = s) ORDER BY s.stageNumber")
    
List<Stage> findFreeStages();

}


 