package auca.ac.transit_dues.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import auca.ac.transit_dues.domain.DuesPayment;
import auca.ac.transit_dues.domain.PaymentStatus;
import auca.ac.transit_dues.service.DuesPaymentService;


@RestController
@RequestMapping("/api/dues-payments")
public class DuesPaymentController {

    @Autowired
    private DuesPaymentService duesPaymentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDuesPayment(@RequestBody DuesPayment duesPayment){
        String msg =duesPaymentService.saveDuesPayment(duesPayment);
        if (msg.contains("successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        }
        if (msg.contains("past")) {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
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
        if (msg.contains("receipts")) {

            return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }



    @GetMapping("/by-status")
public ResponseEntity<?> getPaymentsByStatus(@RequestParam PaymentStatus status) {

    return new ResponseEntity<>(duesPaymentService.getPaymentsByStatus(status), HttpStatus.OK);
}

@GetMapping("/between")
public ResponseEntity<?> getPaymentsBetween(@RequestParam String start, @RequestParam String end) {
    LocalDate startDate =LocalDate.parse(start);
    LocalDate endDate =LocalDate.parse(end);

    return new ResponseEntity<>(duesPaymentService.getPaymentsBetween(startDate, endDate), HttpStatus.OK);

}

@PatchMapping("/{id}/status")
public ResponseEntity<?> changeStatus(@PathVariable UUID id, @RequestParam PaymentStatus status) {
    String msg = duesPaymentService.changeStatus(id, status);
    if (msg.contains("successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    if (msg.contains("Cannot change")) {
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
}

@GetMapping("/stats/by-status")
public ResponseEntity<?> getStatusCounts() {
    return new ResponseEntity<>(duesPaymentService.getStatusCounts(), HttpStatus.OK);
}

@PatchMapping("/cancel-day")
public ResponseEntity<?> cancelDay(@RequestParam UUID treasurerId, @RequestParam String date) {
    LocalDate day =LocalDate.parse(date);
    return new ResponseEntity<>(duesPaymentService.cancelDay(treasurerId, day), HttpStatus.OK);
}

@GetMapping("/page")
public ResponseEntity<?> getPaymentsPage(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
    String[] parts =sort.split(",");
    Sort sortObject =Sort.by(parts[0]);
    if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
        sortObject = sortObject.descending();
    }
    Pageable pageable =PageRequest.of(page, size, sortObject);
    return new ResponseEntity<>(duesPaymentService.getPaymentsPage(pageable), HttpStatus.OK);
}




    












}