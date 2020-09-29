package com.arbocontrol.sisvetor.controller;
    
import java.math.BigInteger;
import java.util.*;

import com.arbocontrol.sisvetor.model.Item;
import com.arbocontrol.sisvetor.model.ItemSubItem;
import com.arbocontrol.sisvetor.model.ItemSubItemID;
import com.arbocontrol.sisvetor.model.SubItem;
import com.arbocontrol.sisvetor.repository.ItemRepository;
import com.arbocontrol.sisvetor.repository.ItemSubItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
class ItemController {

    @Autowired
    ItemRepository repository;

    @Autowired
    ItemSubItemRepository deleteItemSubItem;

    @GetMapping("/listar")
    public ResponseEntity<List<Item>> listar() {
        try {
            List<Item> items = new ArrayList<Item>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Item> cadastrar(@RequestBody Item item) {
        try {
            Item savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Item> editar(@PathVariable("id") BigInteger id, @RequestBody Item item) {
        try {
            Optional<Item> existingItemOptional = repository.findById(id);
            if (existingItemOptional.isPresent()) {
                Item existingItem = existingItemOptional.get();
                existingItem.getSubItens().addAll(item.getSubItens());
                existingItem.setNome(item.getNome());
                return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") BigInteger id) {
        Optional<Item> existingItemOptional = repository.findById(id);
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

    @PostMapping("/{id}/cadastrarsubitem")
    public ResponseEntity<Item> cadastrarsubitem(@PathVariable("id") BigInteger id, @RequestBody SubItem subItem) {
        try {
            Optional<Item> existingItemOptional = repository.findById(id);
            if (existingItemOptional.isPresent()) {
                Item existingItem = existingItemOptional.get();
                existingItem.getSubItens().add(subItem);
                return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{item}/deletarsubitem/{subitem}")
    public ResponseEntity<HttpStatus> deletarSubItem(@PathVariable("item") BigInteger item_id, @PathVariable("subitem") BigInteger subitem_id) {
        try {
            ItemSubItemID itemSubItem = new ItemSubItemID();
            itemSubItem.setItem_id(item_id);
            itemSubItem.setSub_item_id(subitem_id);
            deleteItemSubItem.deleteById(itemSubItem);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
