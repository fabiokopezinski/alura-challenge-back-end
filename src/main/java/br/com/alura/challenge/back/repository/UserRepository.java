package br.com.alura.challenge.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.back.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional<User> findByEmail(String username);
}
