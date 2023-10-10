package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Integer> {
        Optional<Show> findById(int id);
}
