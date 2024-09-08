package com.trabalhofullstack.cadastro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhofullstack.cadastro.Service.PessoaService;
import com.trabalhofullstack.cadastro.model.Pessoa;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        if (!pessoaService.buscarPessoaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pessoa.setId(id);
        Pessoa pessoaAtualizada = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        if (!pessoaService.buscarPessoaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
