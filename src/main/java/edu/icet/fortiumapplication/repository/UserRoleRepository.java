package edu.icet.fortiumapplication.repository;

import edu.icet.fortiumapplication.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer> {
    Optional<UserRoleEntity> findByRole(String role);
}
