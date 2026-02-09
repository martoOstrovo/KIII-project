package ukim.finki.kiii.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukim.finki.kiii.todo.dto.ItemDto;
import ukim.finki.kiii.todo.entity.Item;
import ukim.finki.kiii.todo.service.ItemService;
import java.util.List;

@RestController
@RequestMapping(value = "/api/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TodoController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto) {
        Item item = itemService.createItem(itemDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        Item item = itemService.updateItem(id, itemDto);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
