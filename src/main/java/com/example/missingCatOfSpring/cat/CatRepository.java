package com.example.missingCatOfSpring.cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {}
