package space.springbok.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class OrderServiceApplication {

    @Autowired
    private OrderServiceConfiguration properties;

    @Value("${service.instance.name}")
    private String instance;

    @Value("${some.other.property}")
    private String someOtherProperty;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @RequestMapping("/")
    public String message() {
        return "Hello from instance " + instance;
    }

    @RequestMapping("/fromLocation")
    public String fromLocation(@RequestHeader("x-location") String location) {
        return "Hello from location: " + location;
    }
    @RequestMapping("/printConfig")
    public String printConfig() {
        StringBuilder sb = new StringBuilder();
        sb.append(properties.getProperty());
        sb.append(" || ");
        sb.append(instance);
        sb.append(" || ");
        sb.append(someOtherProperty);

        return sb.toString();
    }

}
