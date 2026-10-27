package auca.ac.transit_dues.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.domain.Stage;
import auca.ac.transit_dues.repository.StageRepository;  



@Service 
public class StageService {

    @Autowired 
    private StageRepository stageRepository;

    public String saveStage (Stage stage)  {
        stageRepository.save(stage);
        return  "Stage saved successfully";


    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    } 

    public Optional<Stage> getStageById(UUID id) {
        return stageRepository.findById(id);
    }


    public String updateStage (UUID id, Stage newData) {
        Optional<Stage> found = stageRepository.findById(id);
        if (found.isEmpty()) {
            return "The stage with that id does not exist";
        }
        Stage stage = found.get();
        stage.setStageNumber(newData.getStageNumber());
        stageRepository.save(stage);
        stageRepository.save(stage);
        return "Stage updated successfully";

    } 

    public String deleteStage(UUID id) {
        Optional<Stage>  found = stageRepository.findById(id);
        if(found.isEmpty()) {
            return "The stage with that di does not exist";
        }
        stageRepository.deleteById(id);
        return "Stage deleted successfully";
    }  

     







}







