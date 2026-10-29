package auca.ac.transit_dues.controller;

import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;


import auca.ac.transit_dues.domain.DuesType;
import auca.ac.transit_dues.service.DuesTypeService;


@RestController
@RequestMapping("/api/dues-types")

public class DuesTypeController {

    @Autowired 
    private DuesTypeService duesTypeService; 

    @PostMapping("/save")
    public ResponseEntity<?> saveDuesType(@RequestBody DuesType duesType) {
        String msg = duesTypeService.saveDuesType(duesType);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDuesTypes() {
        return new ResponseEntity<>(duesTypeService.getAllDuesTypes(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDuesTypeById(@PathVariable UUID id) {
        Optional<DuesType> duesType = duesTypeService.getDuesTypeById(id);
        if (duesType.isPresent()) {
            return new ResponseEntity<>(duesType.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>("The dues type with that id does not exist", HttpStatus.NOT_FOUND);
    }

@PutMapping("/update/{id}")
public ResponseEntity<?> updateDuesType(@PathVariable UUID id, @RequestBody DuesType duesType) {
    String msg = duesTypeService.updateDuesType(id, duesType);
    if (msg.contains("successufully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
} 
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<?> deleteDuesType(@PathVariable UUID id) {
    String msg = duesTypeService.deleteDuesType(id);
    if (msg.contains("successfull")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }
    return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
 }

 @GetMapping("/unused")
public ResponseEntity<?> getUnusedDuesTypes(){
    
    return new ResponseEntity<>(duesTypeService.getUnusedDuesTypes(), HttpStatus.OK);
}





}