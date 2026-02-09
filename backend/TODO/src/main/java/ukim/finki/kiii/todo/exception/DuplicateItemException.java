package ukim.finki.kiii.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(String name) {
        super(String.format("Item with name: %s already exists", name));
    }
}
