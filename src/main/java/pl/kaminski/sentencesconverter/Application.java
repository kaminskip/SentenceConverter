package pl.kaminski.sentencesconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to run sentence converter application
 * All configuration is located in application.properties file
 */
@SpringBootApplication
public class Application {

    /**
     * Starter method
     * @param args not used
     * @throws Exception application exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}