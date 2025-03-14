package apps.oa.server.info.endpoint.ServerInfoEndpoint.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class APIResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private Object data;
    @JsonProperty("success")
    private boolean success;
    private int statusCode;

    @JsonCreator
    public APIResponse(
            @JsonProperty("message") String message,
            @JsonProperty("data") Object data,
            @JsonProperty("success") boolean success,
            @JsonProperty("statusCode") int statusCode) {
        this.message = message;
        this.data = data;
        this.success = success;
        try {
            this.statusCode = HttpStatus.valueOf(statusCode).value(); // Convert enum to int
        } catch (IllegalArgumentException e) {
            this.statusCode = 500; // Default to 500 if unknown status
        }
    }


}
