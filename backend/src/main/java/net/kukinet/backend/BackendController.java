package net.kukinet.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lms on 30/11/18.
 */

@RestController
public class BackendController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello from backend.";
    }

}
