package apps.oa.server.info.endpoint.ServerInfoEndpoint.controller;

import apps.oa.server.info.endpoint.ServerInfoEndpoint.common.APIResponse;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.service.ServersInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ServersInfoController {

    private final ServersInfoService serversInfoService;

    @GetMapping("serverinfo")
    public ResponseEntity<APIResponse> getServerHealthInfo(@RequestHeader(value = "authToken", required = false) String secretAccessToken) {
        return serversInfoService.getServerHealthInfo(secretAccessToken);
    }

    @GetMapping("ping")
    public ResponseEntity<APIResponse> getPingInfo() {
        return serversInfoService.pingTest();
    }
}
