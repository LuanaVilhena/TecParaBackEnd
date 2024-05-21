package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.RegistrationStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationStudentRepository extends JpaRepository<RegistrationStudent, Long> {
    List<RegistrationStudent> findByStudentId(Long StudentId);
}
