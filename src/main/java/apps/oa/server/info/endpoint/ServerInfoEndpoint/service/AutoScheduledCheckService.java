package apps.oa.server.info.endpoint.ServerInfoEndpoint.service;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutoScheduledCheckService {
    public int timeServiceUpInMinutes = 0;

    @Scheduled(fixedDelay = 60000)// 1 minute = 1 * 1 * 60 * 1000 milliseconds
    public void serviceRunEachMinute() {
        timeServiceUpInMinutes++;
    }
}
