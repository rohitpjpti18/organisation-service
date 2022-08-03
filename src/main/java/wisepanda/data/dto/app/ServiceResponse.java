package wisepanda.data.dto.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse implements Serializable{
    @JsonProperty("http-status")
    private HttpStatus httpStatus = HttpStatus.OK;
    
    @JsonProperty("path")
    private String requestPath;
    
    @JsonProperty("result")
    private Object result;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonProperty("errors")
    private Map<String, String> errors;
    
    @JsonProperty("warnings")
    private Map<String, String> warnings;
}
