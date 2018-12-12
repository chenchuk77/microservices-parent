package net.kukinet.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by lms on 30/11/18.
 */

@RefreshScope
@RestController
public class FrontendController {

    @Value("${backend.service.url}")
    private String backendServiceUrl;

    @Value("${motd}")
    private String motd;

    // from git
    @Value("${app.name}")
    private String appName;

    // from git
    @Value("${stam.string}")
    private String stamString;

    // from git
    @Value("${environment}")
    private String environment;

    @RequestMapping("/params")
    public String showParams() {
        return "appName: " + appName + "\n" + "stamString: " + stamString + "\n";
    }

    @RequestMapping("/environment")
    public String showEnvironment() {
        return "Environment: " + environment + "\n";
    }

    @RequestMapping("/motd")
    public String showMotd() {
        return motd;
    }

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
        //String backendIdUrl= "http://" + System.getenv("BACKEND_IP") + ":8085/id";

        String backendIdUrl= "http://" + backendServiceUrl + "/id";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendIdUrl, String.class);
    }
}
