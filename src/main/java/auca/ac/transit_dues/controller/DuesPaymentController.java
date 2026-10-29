package auca.ac.transit_dues.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.service.DuesPaymentService;


@RestController
@RequestMapping("/api/dues-payments")
public class DuesPaymentController {

    @Autowired
    private DuesPaymentService duesPaymentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDuesPayment(@RequestBody DuesPayment duesPayment){
        String msg =duesPaymentService.saveDuesPayment(duesPayment);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }



    @GetMapping("/all")
    public ResponseEntity<?>getAllDuesPayments() {

        return new ResponseEntity<>(duesPaymentService.getAllDuesPayments(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getDuesPaymentById(@PathVariable UUID id) {
        Optional<DuesPayment> duesPayment =duesPaymentService.getDuesPaymentById(id);
        if (duesPayment.isPresent()) {
            return new ResponseEntity<>(duesPayment.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("The dues payment with that id does not exist", HttpStatus.NOT_FOUND);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDuesPayment(@PathVariable UUID id, @RequestBody DuesPayment duesPayment) {
        String msg =duesPaymentService.updateDuesPayment(id, duesPayment);
        if (msg.contains("successfully")) {

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDuesPayment(@PathVariable UUID id) {
        String msg =duesPaymentService.deleteDuesPayment(id);
        if (msg.contains("successfully")) {

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }












}