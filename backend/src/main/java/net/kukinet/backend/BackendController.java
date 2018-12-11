package net.kukinet.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lms on 30/11/18.
 */

@RestController
public class BackendController {

    @Value("${motd}")
    private String motd;

    @RequestMapping("/motd")
    public String showMotd() {
        return motd;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello from backend.";
    }

    @RequestMapping("/id")
    public String myContainerId() {
        return myId();
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

    private String myId (){
        return System.getenv("ARTIFACT_ID") + ":" +
                System.getenv("LMS_VERSION") + ":" +
                System.getenv("HOSTNAME") + "\n";
    }

}
