package apps.oa.server.info.endpoint.ServerInfoEndpoint.service;

import apps.oa.server.info.endpoint.ServerInfoEndpoint.common.APIResponse;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.common.CustomHttpAPIResponse;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.dto.ServerInfoResponseDTO;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.entity.Database;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.entity.ExternalServices;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.utils.HealthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServersInfoServiceImpl implements ServersInfoService {

    private final HealthUtil healthUtil;
    @Value("${app.server.name}")
    private String serverName;

    @Value("${app.server.desc}")
    private String serverDesc;

    @Value("${app.server.secretAccessToken}")
    private String secretAccessToken;


    @Override
    public ResponseEntity<APIResponse> getServerHealthInfo(String secretAccessToken) {
        if (secretAccessToken == null || !secretAccessToken.equals(this.secretAccessToken)) {
            return CustomHttpAPIResponse.res("Access Token Invalid", HttpStatus.UNAUTHORIZED);
        }
        ServerInfoResponseDTO serverInfoResponseDTO = new ServerInfoResponseDTO();
        serverInfoResponseDTO.setServerName(serverName);
        serverInfoResponseDTO.setServerDescription(serverDesc);
        serverInfoResponseDTO.setStatus("UP");
        serverInfoResponseDTO.setDatabase(new Database("DOWN", healthUtil.getMongoResponseTime()));
        serverInfoResponseDTO.setCpuLoadInPercentage(healthUtil.getCpuLoad() * 100);
        serverInfoResponseDTO.setMemoryUsageInPercentage(healthUtil.getMemoryUsage() * 100);
        serverInfoResponseDTO.setDiskUsageInPercentage(healthUtil.getDiskUsage() * 100);
        serverInfoResponseDTO.setUptime(healthUtil.getUptime());
        serverInfoResponseDTO.setExternalServices(new ExternalServices("NA", "NA"));
        serverInfoResponseDTO.setHealthScore(100 -
                (
                        0.4 * serverInfoResponseDTO.getCpuLoadInPercentage() +
                                0.3 * serverInfoResponseDTO.getMemoryUsageInPercentage() +
                                0.3 * serverInfoResponseDTO.getDiskUsageInPercentage()));
        serverInfoResponseDTO.setTimestamp(LocalDateTime.now());
        return CustomHttpAPIResponse.res("Successfully fetched the server info.", serverInfoResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse> pingTest() {
        return CustomHttpAPIResponse.res("Success.", HttpStatus.OK);
    }
}
