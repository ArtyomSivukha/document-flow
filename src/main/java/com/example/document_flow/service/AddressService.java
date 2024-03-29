package com.example.document_flow.service;

import com.example.document_flow.entity.Address;
import com.example.document_flow.entity.Person;
import com.example.document_flow.repository.AddressRepository;
import com.example.document_flow.repository.PersonRepository;
import com.example.document_flow.util.CurrentUser;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements Getable<Address> {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;

    public Address addOne(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getOne(Long id) {
        return addressRepository.findById(id).get();
    }

    public Address editOne(Address address) {
        Address addressDB = CurrentUser.getInstance().getUser().getPerson().getAddress();
        addressDB.setCity(address.getCity());
        addressDB.setStreet(address.getStreet());
        addressDB.setPostalCode(address.getPostalCode());
        return addressRepository.save(addressDB);
    }
}
