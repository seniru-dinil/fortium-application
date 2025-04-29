package edu.icet.fortiumapplication.config;

import edu.icet.fortiumapplication.entity.DepartmentEntity;
import edu.icet.fortiumapplication.entity.UserRoleEntity;
import edu.icet.fortiumapplication.repository.DepartmentRepository;
import edu.icet.fortiumapplication.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class DefaultValueInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final UserRoleRepository userRoleRepository;



    @Override
    public void run(String... args) throws Exception {
            if(userRoleRepository.findByRole("ROLE_ADMIN").isEmpty()){
                userRoleRepository.save(
                        UserRoleEntity.builder()
                                .role("ROLE_ADMIN")
                                .build()
                );
            } if(userRoleRepository.findByRole("ROLE_EMPLOYEE").isEmpty()){
                userRoleRepository.save(
                        UserRoleEntity.builder()
                                .role("ROLE_EMPLOYEE")
                                .build()
                );
            }
        if (departmentRepository.findByName("IT").isEmpty()) {
            departmentRepository.save(
                    DepartmentEntity.builder()
                            .name("IT")
                            .build()
            );
        }

        if (departmentRepository.findByName("HR").isEmpty()) {
            departmentRepository.save(
                    DepartmentEntity.builder()
                            .name("HR")
                            .build()
            );
        }

        if (departmentRepository.findByName("FINANCE").isEmpty()) {
            departmentRepository.save(
                    DepartmentEntity.builder()
                            .name("FINANCE")
                            .build()
            );
        }

        if (departmentRepository.findByName("OPERATIONS").isEmpty()) {
            departmentRepository.save(
                    DepartmentEntity.builder()
                            .name("OPERATIONS")
                            .build()
            );
        }
    }
}
