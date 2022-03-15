package com.example.missingCatOfSpring.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    OwnerRepository ownerRepository;

    @GetMapping
    List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    @GetMapping("{ownerId}")
    public Optional<Owner> getSpecificOwner(@PathVariable Long ownerId) {
        return ownerRepository.findById(ownerId);
    }

    @PostMapping
    Owner createOwner(@RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }

    @DeleteMapping(path = "{ownerId}")
    public void deleteOwner(@PathVariable Long ownerId) {
        boolean exists = ownerRepository.existsById(ownerId);
        if (!exists) {
            throw new IllegalStateException("Owner with id " + ownerId + " does not exists");
        }
        ownerRepository.deleteById(ownerId);
    }
}
