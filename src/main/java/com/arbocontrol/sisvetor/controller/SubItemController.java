package com.arbocontrol.sisvetor.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.arbocontrol.sisvetor.entity.Item;
import com.arbocontrol.sisvetor.entity.SubItem;
import com.arbocontrol.sisvetor.repository.SubItemRepository;
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

@RestController
@RequestMapping("/api/subitem")
class SubItemController {

    @Autowired
    SubItemRepository repository;

    @GetMapping("/listar")
    public ResponseEntity<List<SubItem>> listar() {
        try {
            List<SubItem> SubItems = new ArrayList<SubItem>();

            repository.findAll().forEach(SubItems::add);

            if (SubItems.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(SubItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SubItem> cadastrar(@RequestBody SubItem SubItem) {
        try {
            SubItem savedSubItem = repository.save(SubItem);
            return new ResponseEntity<>(savedSubItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<SubItem> editar(@PathVariable("id") BigInteger id, @RequestBody SubItem SubItem) {
        Optional<SubItem> existingSubItemOptional = repository.findById(id);
        if (existingSubItemOptional.isPresent()) {
            SubItem existingSubItem = existingSubItemOptional.get();
            existingSubItem.setNome(SubItem.getNome());
            return new ResponseEntity<>(repository.save(existingSubItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<SubItem> getById(@PathVariable("id") BigInteger id) {
        Optional<SubItem> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletar(@PathVariable("id") BigInteger id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<Item>> listarItens(@PathVariable("id") BigInteger id) {

        Optional<SubItem> existingItemOptional = repository.findById(id);
        SubItem subItem = existingItemOptional.get();

        if (!subItem.getItens().isEmpty()) {
            return new ResponseEntity<>(subItem.getItens(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
