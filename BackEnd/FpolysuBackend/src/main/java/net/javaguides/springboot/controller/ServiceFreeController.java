package net.javaguides.springboot.controller;


import net.javaguides.springboot.entity.ServiceFee;
import net.javaguides.springboot.repository.ServiceFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class ServiceFreeController {
    @Autowired
    private ServiceFeeRepository serviceFeeRepository;


    @GetMapping("/servicefee")
    public List<ServiceFee> getAllServiceFee() {
        return serviceFeeRepository.findAll();
    }

    @GetMapping("/servicefee/{id}")
    public Optional<ServiceFee> getServiceFeeById(@PathVariable int id) {
        return serviceFeeRepository.findById(id);
    }

    @PostMapping("/servicefee/create")
    public ServiceFee createServiceFee(@RequestBody ServiceFee serviceFee) {
        return serviceFeeRepository.save(serviceFee);
    }

    @PutMapping("/servicefee/update")
    public ResponseEntity updateServiceFee(@RequestBody ServiceFee serviceFee) {
        ServiceFee dto = serviceFeeRepository.save(serviceFee);
        System.out.println("servicefeeeeeeeee" + serviceFee);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


}
