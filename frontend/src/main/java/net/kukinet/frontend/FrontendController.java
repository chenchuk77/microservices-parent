package net.kukinet.frontend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lms on 30/11/18.
 */

@RestController
public class FrontendController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello from frontend.";
    }

}
