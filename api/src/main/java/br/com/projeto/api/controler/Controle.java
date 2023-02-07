package br.com.projeto.api.controler;

import java.util.List;

import org.hibernate.sql.results.graph.collection.CollectionLoadingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;
import jakarta.validation.Valid;

@RestController
public class Controle {
    
    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }
    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }
    
    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){
        return servico.editar(obj);
    }
    
    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
        return servico.remover(codigo);  
    }
    
    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List <Pessoa> ordenarNomes(){
        return acao.findByOrderByNome();
    }
    @GetMapping("/api/ordenarNomes2")
    public List <Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("Julio");
    }

    @GetMapping("/api/procurarTermo/{termo}")
    public List <Pessoa> procurarTermo(@PathVariable String termo){
        return acao.findByNomeContaining(termo);
    }

    @GetMapping("/api/procurarPrimeiraLetra/{letra}")
    public List <Pessoa> procurarPrimeiraLetra(@PathVariable String letra){
        return acao.findByNomeStartsWith(letra);
    }

    @GetMapping("/api/procurarUltimaLetra/{letra}")
    public List <Pessoa> procurarUltimaLetra(@PathVariable String letra){
        return acao.findByNomeEndsWith(letra);
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual/{idade}")
    public List <Pessoa> idadeMaiorIgual(@PathVariable Integer idade){
        return acao.idadeMaiorIgual(idade);
    }

    @GetMapping("/")
    public String mensagem(){
        return "Hello World!";  

    }
    @GetMapping("home/{nome}")
    public String boasVindas(@PathVariable String nome){
        return "Seja Bem vindo a minha API " + nome;
    }

    @GetMapping("home")
    public String boasVindasPadrao(){
        return "Seja Bem vindo a minha API!";
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa pessoa){
        
        return pessoa;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){
        
    }
    
        
}
