package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //inclui @Controller e @ResponseBody
@RequestMapping("/aluno") //define a rota que será usada para fazer o mapping do atributo
public class AlunoController {

    @Autowired //as dependências são injetadas automaticamente
    AlunoService alunoService;

    @PostMapping //é uma versão específica de @RequestMapping só pro método POST
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Aluno aluno) {
        alunoService.create(aluno);
    }
    //RequestBody informa o controller que o objeto retornado é automaticamente serializado como JSON e escreve para o
    // HttpStatus automaticamente.

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> findById(@PathVariable Long id) {
        return alunoService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Aluno aluno, @PathVariable Long id) {
        alunoService.update(id, aluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        alunoService.deleteById(id);
    }
}
