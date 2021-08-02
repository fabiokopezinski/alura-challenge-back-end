package br.com.alura.challenge.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.back.domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    
    Optional<Profile> findByName(String profile);
}
