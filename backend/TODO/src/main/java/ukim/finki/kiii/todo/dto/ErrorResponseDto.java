package ukim.finki.kiii.todo.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String message;
    private int status;
    private long timeStamp;
}
