package com.arbocontrol.sisvetor.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.arbocontrol.sisvetor.entity.Item;
import com.arbocontrol.sisvetor.entity.SubItem;
import com.arbocontrol.sisvetor.repository.SubItemRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "sub-item", tags = {"SubItem"})
@RestController
@RequestMapping("/api/subitem")
class SubItemController {

    @Autowired
    SubItemRepository repository;

    @ApiOperation(value = "Listagem de subitens cadastrados")
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

    @ApiOperation(value="Cadastro de um sub-item")
    @PostMapping("/cadastrar")
    public ResponseEntity<SubItem> cadastrar(@RequestBody SubItem SubItem) {
        try {
            SubItem savedSubItem = repository.save(SubItem);
            return new ResponseEntity<>(savedSubItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value="Atualização de um sub-item já existente")
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

    @ApiOperation(value="Consulta por um sub-item cadastrado")
    @GetMapping("/consultar/{id}")
    public ResponseEntity<SubItem> getById(@PathVariable("id") BigInteger id) {
        Optional<SubItem> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value="Exclusão de um item")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletar(@PathVariable("id") BigInteger id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    @ApiOperation(value="Listagem de itens relacionados à um sub-item")
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
