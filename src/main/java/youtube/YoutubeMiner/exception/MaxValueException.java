package youtube.YoutubeMiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El valor de Max debe ser mayor o igual que 0")
public class MaxValueException extends Exception{
}
