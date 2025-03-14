package apps.oa.server.info.endpoint.ServerInfoEndpoint.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomHttpAPIResponse {
    public static ResponseEntity<APIResponse> res(String message, HttpStatus status) {
        return new ResponseEntity<>(new APIResponse(message, null, status == HttpStatus.OK, status.value()), status);
    }

    public static ResponseEntity<APIResponse> res(String message, Object data, HttpStatus status) {
        return new ResponseEntity<>(new APIResponse(message, data, status == HttpStatus.OK, status.value()), status);
    }

    public static ResponseEntity<APIResponse> res(String message, Object data, boolean successStatus, HttpStatus status) {
        return new ResponseEntity<>(new APIResponse(message, data, successStatus, status.value()), status);
    }
}
