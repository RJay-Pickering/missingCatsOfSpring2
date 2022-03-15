package com.example.missingCatOfSpring.cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    CatRepository catRepository;

    @GetMapping
    List<Cat> getCats() {
        return catRepository.findAll();
    }

    @GetMapping("{catId}")
    public Optional<Cat> getSpecificCat(@PathVariable Long catId) {
        return catRepository.findById(catId);
    }

    @PostMapping
    Cat createCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }

    @DeleteMapping(path = "{catId}")
    public void deleteCat(@PathVariable Long catId) {
        boolean exists = catRepository.existsById(catId);
        if (!exists) {
            throw new IllegalStateException("Cat with id " + catId + " does not exists");
        }
        catRepository.deleteById(catId);
    }
}