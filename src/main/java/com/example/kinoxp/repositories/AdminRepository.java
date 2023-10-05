package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
