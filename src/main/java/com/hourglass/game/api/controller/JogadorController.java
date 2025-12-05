package com.hourglass.game.api.controller;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.service.JogadorService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<?> criar(@PathVariable int usuarioId,
                                @RequestBody Map<String, String> body) {

        String nome = body.get("nomeJogador");
        JogadorEntity jogador = jogadorService.incluir(usuarioId, nome);

        return ResponseEntity.status(HttpStatus.CREATED).body(jogador);
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(jogadorService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar jogadores");
        }
    }


    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> buscar(@PathVariable int usuarioId) {
        try {
            return ResponseEntity.ok(jogadorService.buscarPorId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<?> atualizar(@PathVariable int usuarioId,
                                       @RequestBody JogadorEntity body) {

        try {
            return ResponseEntity.ok(jogadorService.atualizar(usuarioId, body));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> excluir(@PathVariable int usuarioId) {

        try {
            jogadorService.excluir(usuarioId);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}