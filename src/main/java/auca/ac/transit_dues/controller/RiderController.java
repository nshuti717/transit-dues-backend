package auca.ac.transit_dues.controller;

import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.transit_dues.domain.Rider;
import auca.ac.transit_dues.service.RiderService;

@RestController
@RequestMapping("/api/riders")
public class RiderController {
    @Autowired
    private RiderService riderService;

    @PostMapping("/save")
    public ResponseEntity<?>saveRider(@RequestBody Rider rider) {
        String msg =riderService.saveRider(rider);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")

    public ResponseEntity<?> getAllRiders() {

        return new ResponseEntity<>(riderService.getAllRiders(), HttpStatus.OK);

    }

    @GetMapping("/{id}")

    public ResponseEntity<?>getRiderById(@PathVariable UUID id) {
        Optional<Rider> rider =riderService.getRiderById(id);
        if (rider.isPresent()) {

            return new ResponseEntity<>(rider.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("The rider with that id does not exist", HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")

    public ResponseEntity<?> updateRider(@PathVariable UUID id, @RequestBody Rider rider) {
        String msg =riderService.updateRider(id, rider);
        if (msg.contains("successfully")){
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }   


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRider(@PathVariable UUID id) {
        String msg =riderService.deleteRider(id);

        if (msg.contains("successfully")) {

            return new ResponseEntity<>(msg, HttpStatus.OK);

        }
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-last-name")

public ResponseEntity<?> getRidersByLastName(@RequestParam String lastName) {

    return new ResponseEntity<>(riderService.getRidersByLastName(lastName), HttpStatus.OK);
    
} 











}


