package ukim.finki.kiii.todo.service;

import ukim.finki.kiii.todo.dto.ItemDto;
import ukim.finki.kiii.todo.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(ItemDto itemDto);
    List<Item> getItems();
    Item updateItem(Long id, ItemDto itemDto);
    void deleteItem(Long id);
}
