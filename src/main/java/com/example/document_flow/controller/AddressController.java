package com.example.document_flow.controller;

import com.example.document_flow.entity.Address;
import com.example.document_flow.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    private ResponseEntity<?> add(@RequestBody Address address){
        return new ResponseEntity<>(addressService.addOne(address), HttpStatus.OK);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAll(){
        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getOne(@PathVariable Long id){
        return new ResponseEntity<>(addressService.getOne(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    private ResponseEntity<?> editOne(@RequestBody Address address){
        return new ResponseEntity<>(addressService.editOne(address), HttpStatus.OK);
    }
}
