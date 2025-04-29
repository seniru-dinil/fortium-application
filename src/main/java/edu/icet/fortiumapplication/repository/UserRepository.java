package edu.icet.fortiumapplication.repository;

import edu.icet.fortiumapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);
}
