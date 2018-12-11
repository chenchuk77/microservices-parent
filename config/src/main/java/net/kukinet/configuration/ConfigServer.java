package net.kukinet.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by lms on 10/12/18.
 */

@SpringBootApplication
@EnableConfigServer
public class ConfigServer {
    public static void main(String[] args) {
        // pass this class with the args
        SpringApplication.run(ConfigServer.class, args);
    }

}
