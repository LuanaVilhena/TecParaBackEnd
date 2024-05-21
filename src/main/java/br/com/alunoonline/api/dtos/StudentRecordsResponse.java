package br.com.alunoonline.api.dtos;

import lombok.Data;

import java.util.List;

@Data
public class StudentRecordsResponse {
    private String studentName;
    private String studentEmail;
    private List<SubjectStudentResponse> subjectStudentResponseList;
}
