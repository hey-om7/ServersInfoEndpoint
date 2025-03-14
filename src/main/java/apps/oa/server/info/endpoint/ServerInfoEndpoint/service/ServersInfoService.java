package apps.oa.server.info.endpoint.ServerInfoEndpoint.service;

import apps.oa.server.info.endpoint.ServerInfoEndpoint.common.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public interface ServersInfoService {
    ResponseEntity<APIResponse> getServerHealthInfo(String secretAccessToken);

    ResponseEntity<APIResponse> pingTest();
}
