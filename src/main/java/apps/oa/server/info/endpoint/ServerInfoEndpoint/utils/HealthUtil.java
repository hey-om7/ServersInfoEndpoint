package apps.oa.server.info.endpoint.ServerInfoEndpoint.utils;


import apps.oa.server.info.endpoint.ServerInfoEndpoint.service.AutoScheduledCheckService;
import com.sun.management.OperatingSystemMXBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

import java.io.File;
import java.lang.management.ManagementFactory;

@Service
@RequiredArgsConstructor
public class HealthUtil {

    private final AutoScheduledCheckService autoScheduledCheckService;

    public long getMongoResponseTime() {
        return -1;
    }

    public double getCpuLoad() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getCpuLoad(); // Returns CPU load as a fraction (0.0 to 1.0)
    }

    public double getMemoryUsage() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalRAM = memory.getTotal();
        long usedRAM = totalRAM - memory.getAvailable();

        return (double) usedRAM / totalRAM; // Returns memory usage as a fraction (0.0 to 1.0)
    }

    public double getSwapMemoryUsage() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalSwap = memory.getVirtualMemory().getSwapTotal();
        long usedSwap = memory.getVirtualMemory().getSwapUsed();

        return (double) usedSwap / totalSwap; // Returns swap memory usage as a fraction (0.0 to 1.0)
    }

    public double getDiskUsage() {
        File root = new File("/"); // Root directory (Linux/Mac) or C:\ (Windows)
        long totalSpace = root.getTotalSpace();
        long usableSpace = root.getUsableSpace();
        long usedSpace = totalSpace - usableSpace;
        return (double) usedSpace / totalSpace; // Returns disk usage as a fraction (0.0 to 1.0)
    }

    public String checkEmailService() {
        return "NA";
    }

    public String getUptime() {
        int timeInMins = autoScheduledCheckService.timeServiceUpInMinutes;
        String mins = "";
        String hours = "";
        if (timeInMins % 60 != 1) {
            mins = String.format("%d minutes", timeInMins % 60);
        } else {
            mins = String.format("%d minute", timeInMins % 60);
        }
        if (timeInMins / 60 != 1) {
            hours = String.format("%d hours", timeInMins / 60);
        } else {
            hours = String.format("%d hour", timeInMins / 60);
        }

        return String.format("%s %s", hours, mins);
    }
}
