package apps.oa.server.info.endpoint.ServerInfoEndpoint.dto;

import apps.oa.server.info.endpoint.ServerInfoEndpoint.entity.Database;
import apps.oa.server.info.endpoint.ServerInfoEndpoint.entity.ExternalServices;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServerInfoResponseDTO {
    private String serverName;
    private String serverDescription;
    private String status;
    private String build;
    private Database database;
    private ExternalServices externalServices;
    private double healthScore;
    private String uptime;
    private double memoryUsageInPercentage;
    private double cpuLoadInPercentage;
    private double diskUsageInPercentage;
    private LocalDateTime timestamp;

}
