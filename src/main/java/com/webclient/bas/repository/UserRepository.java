package com.webclient.bas.repository;

import com.webclient.bas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Paso 5: Método para buscar y retornar el usuario por su email
    Optional<User> findByEmail(String email);
}

