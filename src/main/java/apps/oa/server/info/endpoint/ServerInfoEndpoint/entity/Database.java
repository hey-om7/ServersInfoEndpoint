package apps.oa.server.info.endpoint.ServerInfoEndpoint.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Database {
    private String status;
    private long dbResponseTimeMs;
}
