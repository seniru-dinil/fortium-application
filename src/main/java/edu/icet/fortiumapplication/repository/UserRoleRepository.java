package edu.icet.fortiumapplication.repository;

import edu.icet.fortiumapplication.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer> {
    Optional<UserRoleEntity> findByRole(String role);
}
