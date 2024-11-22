package com.example.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/authServiceFallBack")
    public String authServiceFallBackMethod(){
        return "Auth Service is taking longer than expected. Please try again late";
    }

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod(){
        return "User Service is taking longer than expected. Please try again late";
    }
    @GetMapping("/purchaseServiceFallBack")
    public String purchaseServiceFallBackMethod(){
        return "Purchase Service is taking longer than expected. Please try again late";
    }

    @GetMapping("/cardisassemblyServiceFallBack")
    public String cardisassemblyServiceFallBackMethod(){
        return "Car disassembly Service is taking longer than expected. Please try again late";
    }

    @GetMapping("/advertisementServiceFallBack")
    public String advertisementServiceFallBackMethod(){
        return "Advertising Service is taking longer than expected. Please try again late";
    }

    @GetMapping("/salesServiceFallBack")
    public String salesServiceFallBackMethod(){
        return "Sales Service is taking longer than expected. Please try again late";
    }

    @GetMapping("/storageServiceFallBack")
    public String storageServiceFallBackMethod(){
        return "Sales Service is taking longer than expected. Please try again late";
    }

    @GetMapping("/precariousServiceFallBack")
    public String precariousServiceFallBackMethod(){
        return "Precarious Service is taking longer than expected. Please try again late";
    }


}
