package br.com.alunoonline.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor //gera um construtor que requer um argumento para cada campo da classe anotada
@NoArgsConstructor //gera um construtor default sem parâmetros definidos
@Data //é um shortcut pra gerar @ToString, @Getter, @Setter, @EqualsAndHashCode e @RequiredArgsConstructor
      //se precisar de argumentos mais específicos, como Getter e Setter privados, é necessário explicitar os casos
@Entity //informa que a classe também é uma entidade
public class Aluno implements Serializable {
    //gera @toString,@EqualsAndHashCode
    @Id //informa qual campo/atributo de uma entidade está relacionado a chave primária da respectiva tabela no dba
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //informa que a geração do valor do identificador único da entidade será gerenciada pelo provedor
    //GenerationType.IDENTITY- uma chave primária vai ser atribuída automaticamente cada vez que uma linha é inserida na tabela
    private Long id;
    // Long é pra números muito extensos, negativos ou positivos
    private String name;
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
