package ukim.finki.kiii.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchItemException extends RuntimeException {
    public NoSuchItemException(Long id) {
        super(String.format("No item with id: %d", id));
    }
}
