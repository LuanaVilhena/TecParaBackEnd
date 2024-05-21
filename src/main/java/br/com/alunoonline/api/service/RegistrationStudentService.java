package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.StudentRecordsResponse;
import br.com.alunoonline.api.dtos.SubjectStudentResponse;
import br.com.alunoonline.api.dtos.UpdateGradesRequest;
import br.com.alunoonline.api.enums.RegistrationStudentStatusEnum;
import br.com.alunoonline.api.model.RegistrationStudent;
import br.com.alunoonline.api.repository.RegistrationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationStudentService {

    public static final double GRADE_AVG_TO_APPROVE = 7.0;

    @Autowired
    RegistrationStudentRepository registrationStudentRepository;

    public void create(RegistrationStudent RegistrationStudent) {
        RegistrationStudent.setStatus(RegistrationStudentStatusEnum.MATRICULADO);
        registrationStudentRepository.save(RegistrationStudent);
    }

    public void updateGrades(Long registrationStudentId, UpdateGradesRequest updateGradesRequest) {
        RegistrationStudent registrationStudent = registrationStudentRepository.findById(registrationStudentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada."));
    }

    public void updateStudentGrades(RegistrationStudent registrationStudent, UpdateGradesRequest updateGradesRequest) {
        if (updateGradesRequest.getGrade1() != null) {
            registrationStudent.setGrade1(updateGradesRequest.getGrade1());
        }

        if (updateGradesRequest.getGrade2() != null) {
            registrationStudent.setGrade2(updateGradesRequest.getGrade2());
        }
    }

    public void updateStudentStatus(RegistrationStudent registrationStudent) {
        Double grade1 = registrationStudent.getGrade1();
        Double grade2 = registrationStudent.getGrade2();

        if (grade1 != null && grade2 != null) {
            double average = (grade1 + grade2) / 2;
            RegistrationStudent.setStatus(average >= GRADE_AVG_TO_APPROVE ? RegistrationStudentStatusEnum.APROVADO : RegistrationStudentStatusEnum.REPROVADO);
        }
    }

    public void updateStatusToBreak(Long registrationStudentId) {
        RegistrationStudent registrationStudent =
                registrationStudentRepository.findById(registrationStudentId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        if (!RegistrationStudentStatusEnum.MATRICULADO.equals(registrationStudent.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível trancar uma matrícula com o status MATRICULADO");
        }

        changeStatus(registrationStudent, RegistrationStudentStatusEnum.TRANCADO);
    }

    public void changeStatus(RegistrationStudent registrationStudent, RegistrationStudentStatusEnum registrationStudentStatusEnum) {
        registrationStudent.setStatus(registrationStudentStatusEnum);
        registrationStudentRepository.save(registrationStudent);
    }

    public StudentRecordsResponse getRecordsFromStudent(Long studentId) {
        List<RegistrationStudent> registrationStudent = registrationStudentRepository.findByStudentId(studentId);

        if (registrationStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não está matriculado.");
        }

        StudentRecordsResponse record = new StudentRecordsResponse();
        record.setStudentName(registrationStudent.get(0).getStudent().getName());
        record.setStudentEmail(registrationStudent.get(0).getStudent().getEmail());

        List<SubjectStudentResponse> subjectsList = new ArrayList<>();

        for (RegistrationStudent registration : registrationStudent) {
            SubjectStudentResponse subjectStudentResponse = new SubjectStudentResponse();
            SubjectStudentResponse.setSubjectName(registration.getSubject().getName());
            SubjectStudentResponse.setProfessorName(registration.getSubject().getProfessor().getName());
            SubjectStudentResponse.setGrade1(registration.getGrade1());
            SubjectStudentResponse.setGrade2(registration.getGrade2());
        }
        return null;
    }
}
