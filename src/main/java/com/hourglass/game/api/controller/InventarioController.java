package com.hourglass.game.api.controller;

import com.hourglass.game.api.entity.InventarioEntity;
import com.hourglass.game.api.service.InventarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    // Criar inventário
    @PostMapping("/{jogadorId}")
    public InventarioEntity criar(
            @PathVariable int jogadorId,
            @RequestBody InventarioEntity body) {

        return inventarioService.criar(jogadorId, body);
    }

    // Buscar inventário
    @GetMapping("/{jogadorId}")
    public InventarioEntity buscar(@PathVariable int jogadorId) {
        return inventarioService.buscarPorJogadorId(jogadorId);
    }

    // Atualizar inventário cheio
    @PutMapping("/{jogadorId}")
    public InventarioEntity atualizar(
            @PathVariable int jogadorId,
            @RequestBody InventarioEntity body) {

        return inventarioService.atualizar(jogadorId, body);
    }

    // Atualizar APENAS chaves
    @PatchMapping("/{jogadorId}/chaves/{qtd}")
    public InventarioEntity atualizarChaves(
            @PathVariable int jogadorId,
            @PathVariable int qtd) {

        return inventarioService.atualizarChaves(jogadorId, qtd);
    }

    // Atualizar APENAS buffs
    @PatchMapping("/{jogadorId}/buffs/{qtd}")
    public InventarioEntity atualizarBuffs(
            @PathVariable int jogadorId,
            @PathVariable int qtd) {

        return inventarioService.atualizarBuffs(jogadorId, qtd);
    }

    // Listar tudo
    @GetMapping
    public List<InventarioEntity> listar() {
        return inventarioService.listarTodos();
    }

    // Excluir inventário
    @DeleteMapping("/{jogadorId}")
    public void excluir(@PathVariable int jogadorId) {
        inventarioService.excluir(jogadorId);
    }
}
