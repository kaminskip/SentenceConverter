package pl.kaminski.sentencesconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Main class to run sentence converter application
 * All configuration is located in application.properties file
 */
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Starter method
     * @param args not used
     * @throws Exception application exception
     */
    public static void main(String[] args) throws Exception {
        showJVMParams();
        SpringApplication.run(Application.class, args);
    }

    /**
     * Show JVM params for diagnostics
     */
    private static void showJVMParams(){
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        log.info("JVM params");
        runtimeMxBean.getInputArguments().stream().forEach(log::info);
    }
}