package auca.ac.transit_dues.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.Treasurer;
import auca.ac.transit_dues.domain.DuesType;
import auca.ac.transit_dues.domain.Stage;
import auca.ac.transit_dues.repository.TreasurerRepository;
import auca.ac.transit_dues.repository.DuesTypeRepository;
import auca.ac.transit_dues.repository.StageRepository;


@Service
public  class TreasurerService {


    @Autowired
    private TreasurerRepository treasurerRepository;

    @Autowired
    private DuesTypeRepository duesTypeRepository;

    @Autowired
    private StageRepository stageRepository;

    public String saveTreasurer (Treasurer treasurer)  {
        treasurerRepository.save(treasurer);
        return "Treasurer saved successfully";
        
    }


    public List<Treasurer> getAllTreasurers() {
        return treasurerRepository.findAll();
    }

    public Optional<Treasurer> getTreasurerById(UUID id) {
        return treasurerRepository.findById(id);
    }


    public String updateTreasurer(UUID id, Treasurer treasurer) {
        Optional<Treasurer> found = treasurerRepository.findById(id);
        if (found.isPresent()) {
            Treasurer old = found.get();
            old.setFirstName(treasurer.getFirstName());
            old.setLastName(treasurer.getLastName());
            old.setPhone(treasurer.getPhone());
            treasurerRepository.save(old);
            return "Treasurer updated successfully";


        }

        return "The treasurer with that id does not exist";

    }

    public  String deleteTreasurer(UUID id) {

        Optional<Treasurer> found =treasurerRepository.findById(id);
        if (found.isPresent()) {
            treasurerRepository.deleteById(id);
            return "Treasurer deleted successfully";
        }
        return "The treasurer with that id does not exist";




    }

    public List<Treasurer> getTreasurersByDuesType(String name) {
    return treasurerRepository.findByDuesTypeName(name);
}

    public String addDuesType(UUID treasurerId, UUID duesTypeId) {
        Optional<Treasurer> foundTreasurer = treasurerRepository.findById(treasurerId);
        if (foundTreasurer.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Optional<DuesType> foundDuesType = duesTypeRepository.findById(duesTypeId);
        if (foundDuesType.isEmpty()) {
            return "That dues type does not exist";
        }
        Treasurer treasurer = foundTreasurer.get();
        DuesType duesType = foundDuesType.get();

        for (DuesType d : treasurer.getDuesTypes()) {
            if (d.getId().equals(duesTypeId)) {
                return "The treasurer already has that dues type";
            }
        }

        treasurer.getDuesTypes().add(duesType);
        treasurerRepository.save(treasurer);
        return "Dues type added to the treasurer";
    }

    public String changeStage(UUID treasurerId, int stageNumber) {
        Optional<Treasurer> foundTreasurer = treasurerRepository.findById(treasurerId);
        if (foundTreasurer.isEmpty()) {
            return "The treasurer with that id does not exist";
        }
        Optional<Stage> foundStage = stageRepository.findByStageNumber(stageNumber);
        if (foundStage.isEmpty()) {
            return "The stage with that stageNumber does not exist";
        }
        Treasurer treasurer = foundTreasurer.get();
        Stage newStage = foundStage.get();

        if (treasurer.getStage() != null && treasurer.getStage().getId().equals(newStage.getId())) {
            return "Stage changed successfully";
        }
        if (treasurerRepository.existsByStage(newStage)) {
            return "That stage is taken by another treasurer";
        }

        treasurer.setStage(newStage);
        treasurerRepository.save(treasurer);
        return "Stage changed successfully";
    }









}
