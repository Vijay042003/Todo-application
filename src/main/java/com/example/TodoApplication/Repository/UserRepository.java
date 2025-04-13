package com.example.TodoApplication.Repository;

import com.example.TodoApplication.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {
    public List<Users> findByEmail(String email);
}
