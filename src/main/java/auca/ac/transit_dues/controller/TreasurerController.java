package auca.ac.transit_dues.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.transit_dues.domain.Treasurer;
import auca.ac.transit_dues.service.TreasurerService;


@RestController
@RequestMapping ("/api/treasurers")
public class TreasurerController {


    @Autowired
    private TreasurerService treasurerService;

    @PostMapping("/save")
    
    public ResponseEntity<?> saveTreasurer(@RequestBody Treasurer treasurer) {
        String msg = treasurerService.saveTreasurer(treasurer);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTreasurers() {
        return new ResponseEntity<>(treasurerService.getAllTreasurers(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTreasurerById(@PathVariable UUID id) {
        Optional<Treasurer> treasurer = treasurerService.getTreasurerById(id);
        if (treasurer.isPresent()) {
            return new ResponseEntity<>(treasurer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The treasurer with that id does not exist", HttpStatus.NOT_FOUND);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTreasurer(@PathVariable UUID id, @RequestBody Treasurer treasurer) {
        String msg = treasurerService.updateTreasurer(id, treasurer);
        if (msg.contains("successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTreasurer(@PathVariable UUID id) {
        String msg = treasurerService.deleteTreasurer(id);
        if (msg.contains("successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }



















}
