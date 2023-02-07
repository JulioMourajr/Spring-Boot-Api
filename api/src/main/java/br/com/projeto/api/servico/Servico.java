package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;



@Service
public class Servico {
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // metodo para cadastrar pessoas.
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equalsIgnoreCase("")){
            mensagem.setMensagem("O nome precisa ser preenchido.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() < 0){
            mensagem.setMensagem("Digite uma idade correta.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // metodo para selecionar pessoas.

    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // metodo para selecionar pessoas através do codigo.

    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        if(acao.countByCodigo(codigo)== 0){
             mensagem.setMensagem("Esse id não existe, passe um id válido.");
             return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }

    // metodo para editar dados

    public ResponseEntity<?> editar(Pessoa obj){
        if(acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("Esse código não existe.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(obj.getNome().equals("")){
            mensagem.setMensagem("É nescessario informar um nome.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade()< 0){
            mensagem.setMensagem("É nescessario informar idade válida.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }

    }

    // metodo para remover registros

    public ResponseEntity<?> remover(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Esse código não existe!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else {
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);
            mensagem.setMensagem("Pessoa Removida com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }

    }
}
