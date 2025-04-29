package edu.icet.fortiumapplication.repository;

import edu.icet.fortiumapplication.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    Optional<DepartmentEntity> findByName(String name);
}
