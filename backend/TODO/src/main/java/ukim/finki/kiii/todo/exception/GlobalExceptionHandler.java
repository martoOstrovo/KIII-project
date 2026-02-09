package ukim.finki.kiii.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ukim.finki.kiii.todo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseDto.setTimeStamp(System.currentTimeMillis());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }

    @ExceptionHandler(NoSuchItemException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchItemException(NoSuchItemException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponseDto.setTimeStamp(System.currentTimeMillis());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateItemException(DuplicateItemException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus(HttpStatus.CONFLICT.value());
        errorResponseDto.setTimeStamp(System.currentTimeMillis());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponseDto);
    }
}
