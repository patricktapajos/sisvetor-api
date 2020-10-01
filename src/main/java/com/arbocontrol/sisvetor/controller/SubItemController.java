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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "sub-item", tags = {"SubItem"})
@RestController
@RequestMapping("/api/subitem")
class SubItemController {

    @Autowired
    SubItemRepository repository;

    @ApiOperation(value = "Listagem de subitens cadastrados")
    @GetMapping("")
    public ResponseEntity<List<SubItem>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        try {
            Pageable pageRequest = PageRequest.of(page, size, Sort.by("nome").ascending());
            Page<SubItem> pagedResult = repository.findAll(pageRequest);

            if (pagedResult.getContent().isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value="Cadastro de um sub-item")
    @PostMapping("/")
    public ResponseEntity<SubItem> create(@RequestBody SubItem SubItem) {
        try {
            SubItem savedSubItem = repository.save(SubItem);
            return new ResponseEntity<>(savedSubItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value="Atualização de um sub-item já existente")
    @PutMapping("/{id}")
    public ResponseEntity<SubItem> update(@PathVariable("id") BigInteger id, @RequestBody SubItem SubItem) {
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
    @GetMapping("/{id}")
    public ResponseEntity<SubItem> getById(@PathVariable("id") BigInteger id) {
        Optional<SubItem> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value="Exclusão de um item")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") BigInteger id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    @ApiOperation(value="Listagem de itens relacionados à um sub-item")
    @GetMapping("/{id}/itens")
    public ResponseEntity<List<Item>> listItens(@PathVariable("id") BigInteger id) {

        Optional<SubItem> existingItemOptional = repository.findById(id);
        SubItem subItem = existingItemOptional.get();

        if (!subItem.getItens().isEmpty()) {
            return new ResponseEntity<>(subItem.getItens(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
