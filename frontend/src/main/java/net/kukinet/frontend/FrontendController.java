package net.kukinet.frontend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lms on 30/11/18.
 */

@RestController
public class FrontendController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello from frontend.";
    }

    @RequestMapping("/id")
    public String myContainerId() {
        return System.getenv("HOSTNAME");
    }

    @RequestMapping("/vars")
    public String showEnvironmetVars() {

        String reply = "";
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            reply += String.format("%s=%s%n", envName, env.get(envName));
        }
        return reply;
    }

}
