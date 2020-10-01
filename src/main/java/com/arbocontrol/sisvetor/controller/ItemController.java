package com.arbocontrol.sisvetor.controller;
    
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import com.arbocontrol.sisvetor.entity.Item;
import com.arbocontrol.sisvetor.entity.ItemSubItemID;
import com.arbocontrol.sisvetor.entity.SubItem;
import com.arbocontrol.sisvetor.repository.ItemRepository;
import com.arbocontrol.sisvetor.repository.ItemSubItemRepository;
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

@Api(value = "item", tags = {"Item"})
@RestController
@RequestMapping("/api/item")
class ItemController {

    @Autowired
    ItemRepository repository;

    @Autowired
    ItemSubItemRepository itemSubItemRepository;

    @ApiOperation(value = "Listagem de itens cadastrados")
    @GetMapping("")
    public ResponseEntity<List<Item>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                             @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        try {
            Pageable pageRequest = PageRequest.of(page, size, Sort.by("nome").ascending());

            Page<Item> pagedResult = repository.findAll(pageRequest);

            if (pagedResult.getContent().isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Cadastro de um item")
    @PostMapping("/")
    public ResponseEntity<Item> create(@RequestBody Item item) {
        try {
            Item savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Atualização de um item já existente")
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable("id") BigInteger id, @RequestBody Item item) {
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

    @ApiOperation(value = "Consulta por um item cadastrado")
    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") BigInteger id) {
        Optional<Item> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Exclusão de um item")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") BigInteger id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    @ApiOperation(value = "Cadastro de um sub-item para um item")
    @PostMapping("/{id}/cadastrarsubitem")
    public ResponseEntity<Item> createsubitem(@PathVariable("id") BigInteger id, @RequestBody SubItem subItem) {
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

    @ApiOperation(value = "Listagem de sub-itens relacionados à um item")
    @GetMapping("/{id}/subitens")
    public ResponseEntity<Set<SubItem>> listSubItens(@PathVariable("id") BigInteger id) {

        Optional<Item> existingItemOptional = repository.findById(id);
        Item item = existingItemOptional.get();

        if (!item.getSubItens().isEmpty()) {
            return new ResponseEntity<>(item.getSubItens(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Exclusão da ligação de um item e um sub-item")
    @DeleteMapping("/{item}/deletarsubitem/{subitem}")
    public ResponseEntity<HttpStatus> deleteSubItem(@PathVariable("item") BigInteger item_id, @PathVariable("subitem") BigInteger subitem_id) {
        try {
            ItemSubItemID itemSubItem = new ItemSubItemID();
            itemSubItem.setItem_id(item_id);
            itemSubItem.setSub_item_id(subitem_id);
            itemSubItemRepository.deleteById(itemSubItem);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
