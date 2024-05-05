package br.com.contato.controller;

import br.com.contato.entity.Contato;
import br.com.contato.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> getAllContatos() {
        return contatoService.getAllContatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Contato contato = contatoService.getContatoById(id);
        if (contato != null) {
            return ResponseEntity.ok(contato);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Contato createContato(@RequestBody Contato contato) {
        return contatoService.createContato(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> updateContato( @RequestBody Contato contatoDetails) {
        Contato updatedContato = contatoService.updateContato(contatoDetails);
        if (updatedContato != null) {
            return ResponseEntity.ok(updatedContato);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        contatoService.deleteContato(id);
        return ResponseEntity.noContent().build();
    }
}