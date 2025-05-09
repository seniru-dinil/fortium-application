package edu.icet.fortiumapplication.repository;

import edu.icet.fortiumapplication.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    Optional<DepartmentEntity> findByName(String name);
}
