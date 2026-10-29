package auca.ac.transit_dues.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.transit_dues.domain.Stage;
import auca.ac.transit_dues.service.StageService;




@RestController 
@RequestMapping ("/api/stages")
public  class StageController {

    @Autowired 
    private StageService stageService;


    @PostMapping("/save")
    public ResponseEntity<?> saveStage(@RequestBody Stage stage) {
        String msg = stageService.saveStage(stage);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }


    @GetMapping("/all") 
    public ResponseEntity<?> getAllstages() {
        return new ResponseEntity<>(stageService.getAllStages(), HttpStatus.OK);
    
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSrageById(@PathVariable UUID id) {
        Optional<Stage> stage = stageService.getStageById(id);
        if (stage.isPresent()) {
            return new ResponseEntity<>(stage.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>("The Stage with that id does not exit", HttpStatus.NOT_FOUND);



    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStage(@PathVariable UUID id, @RequestBody Stage stage) {
        String msg = stageService.updateStage(id, stage);
        if (msg.contains("successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);

        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    } 

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStage(@PathVariable UUID id) {
        String msg = stageService.deleteStage(id);
        if (msg.contains("successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return  new  ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    } 

    @GetMapping("/free")
public ResponseEntity<?> getFreeStages() {

    return new ResponseEntity<>(stageService.getFreeStages(), HttpStatus.OK);
    
}



    



    




    





}