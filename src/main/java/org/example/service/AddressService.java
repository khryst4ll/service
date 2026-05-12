package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Address;
import org.example.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Address getOrCreate(String pureAddress) {
        return addressRepository.findByPureAddress(pureAddress)
                .orElseGet(() -> {
                    Address newAddress = new Address();
                    newAddress.setPureAddress(pureAddress);
                    return this.addressRepository.save(newAddress);
                });
    }
}