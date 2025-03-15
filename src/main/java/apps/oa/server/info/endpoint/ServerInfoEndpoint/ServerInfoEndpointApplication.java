package apps.oa.server.info.endpoint.ServerInfoEndpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerInfoEndpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerInfoEndpointApplication.class, args);
	}

}
