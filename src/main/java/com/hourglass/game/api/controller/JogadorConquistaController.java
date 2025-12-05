package com.hourglass.game.api.controller;

import java.util.List;

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

import com.hourglass.game.api.entity.JogadorConquistaEntity;
import com.hourglass.game.api.service.JogadorConquistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/jogador-conquista")
public class JogadorConquistaController {

private final JogadorConquistaService JogadorConquistaService;

    @GetMapping
    public ResponseEntity<List<JogadorConquistaEntity>> listarTodos() {
        List<JogadorConquistaEntity> lista = JogadorConquistaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<JogadorConquistaEntity> incluir(@RequestBody JogadorConquistaEntity JC) {
        JogadorConquistaEntity novo = JogadorConquistaService.incluir(JC);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{jogadorId}/{conquistaId}")
    public ResponseEntity<JogadorConquistaEntity> editar(@PathVariable int jogadorId, @PathVariable int conquistaId, @RequestBody JogadorConquistaEntity JC) {
        JogadorConquistaEntity atualizado = JogadorConquistaService.editar(jogadorId, conquistaId, JC);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{jogadorId}/{conquistaId}")
    public ResponseEntity<Void> excluir(@PathVariable int jogadorId, @PathVariable int conquistaId) {
        JogadorConquistaService.excluir(jogadorId, conquistaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

