package net.kukinet.frontend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by lms on 30/11/18.
 */

@RestController
public class FrontendController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello from frontend.\n";
    }

    @RequestMapping("/id")
    public String myContainerId() {
        return myId() + "\n";
    }

    @RequestMapping("/vars")
    public String showEnvironmetVars() {

        String reply = "";
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            reply += String.format("%s=%s%n", envName, env.get(envName));
        }
        return reply + "\n";
    }

    // method that calls another container
    @RequestMapping("/be")
    public String getBackendViaFrontend() {
        return "served by: " + myId() + " / " + backendId();
    }

    private String myId (){
        return System.getenv("ARTIFACT_ID") + ":" +
                System.getenv("LMS_VERSION") + ":" +
                System.getenv("HOSTNAME");
    }

    private String backendId(){
        // BACKEND_IP should be injected into env when invoking docker run
        String backendIdUrl= "http://" + System.getenv("BACKEND_IP") + ":8085/id";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendIdUrl, String.class);
    }
}
