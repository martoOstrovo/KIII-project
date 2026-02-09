package ukim.finki.kiii.todo.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ukim.finki.kiii.todo.dto.ItemDto;
import ukim.finki.kiii.todo.entity.Item;
import ukim.finki.kiii.todo.exception.DuplicateItemException;
import ukim.finki.kiii.todo.exception.NoSuchItemException;
import ukim.finki.kiii.todo.repository.ItemRepository;
import ukim.finki.kiii.todo.service.ItemService;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemServiceImp implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item createItem(ItemDto itemDto) {
        Optional<Item> itemOptional = itemRepository.findItemsByItemName(itemDto.getItemName());
        if(itemOptional.isPresent()) {
            throw new DuplicateItemException(itemDto.getItemName());
        }
        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemDescription(itemDto.getItemDescription());
        itemRepository.save(item);
        return item;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Long id, ItemDto itemDto) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isEmpty()) {
            throw new NoSuchItemException(id);
        }
        Item item = itemOptional.get();
        item.setItemName(itemDto.getItemName());
        item.setItemDescription(itemDto.getItemDescription());
        itemRepository.save(item);
        return item;
    }

    @Override
    public void deleteItem(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isEmpty()) {
            throw new NoSuchItemException(id);
        }
        itemRepository.deleteById(id);
    }
}
