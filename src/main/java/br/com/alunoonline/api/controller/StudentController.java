package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Student;
import br.com.alunoonline.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //inclui @Controller e @ResponseBody
@RequestMapping("/aluno") //define a rota que será usada para fazer o mapping do atributo
public class StudentController {

    @Autowired //as dependências são injetadas automaticamente
    StudentService studentService;

    @PostMapping //é uma versão específica de @RequestMapping só pro método POST
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Student student) {
        studentService.create(student);
    }
    //RequestBody informa o controller que o objeto retornado é automaticamente serializado como JSON e escreve para o
    // HttpStatus automaticamente.

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Student> findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Student student, @PathVariable Long id) {
        studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}