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

import com.hourglass.game.api.entity.ItemEntity;
import com.hourglass.game.api.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/item")
public class ItemController {

private final ItemService ItemService;

    @GetMapping
    public ResponseEntity<List<ItemEntity>> listarTodos() {
        List<ItemEntity> lista = ItemService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<ItemEntity> incluir(@RequestBody ItemEntity Item) {
        ItemEntity novo = ItemService.incluir(Item);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemEntity> editar(@PathVariable int id, @RequestBody ItemEntity Item) {
        ItemEntity atualizado = ItemService.editar(id, Item);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        ItemService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

