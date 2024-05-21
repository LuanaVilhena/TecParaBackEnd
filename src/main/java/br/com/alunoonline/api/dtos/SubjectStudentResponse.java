package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.RegistrationStudentStatusEnum;
import lombok.Data;

@Data
public class SubjectStudentResponse {
        private String subjectName;
        private String professorName;
        private Double grade1;
        private Double grade2;
        private Double average;
        private RegistrationStudentStatusEnum status;
    }
