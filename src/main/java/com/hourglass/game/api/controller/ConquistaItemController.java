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

import com.hourglass.game.api.entity.ConquistaItemEntity;
import com.hourglass.game.api.service.ConquistaItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/conquista-item")
public class ConquistaItemController {

private final ConquistaItemService ConquistaItemService;

    @GetMapping
    public ResponseEntity<List<ConquistaItemEntity>> listarTodos() {
        List<ConquistaItemEntity> lista = ConquistaItemService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<ConquistaItemEntity> incluir(@RequestBody ConquistaItemEntity ConquistaItem) {
        ConquistaItemEntity novo = ConquistaItemService.incluir(ConquistaItem);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConquistaItemEntity> editar(@PathVariable int id, @RequestBody ConquistaItemEntity ConquistaItem) {
        ConquistaItemEntity atualizado = ConquistaItemService.editar(id, ConquistaItem);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        ConquistaItemService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

