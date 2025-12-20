package com.pk.commerce.merchant.rest.merchant;

import com.pk.commerce.merchant.api.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
    //we should get redirected here from login.
    @GetMapping("/welcome")
    @ResponseBody
    RestResponse welcomePage(){
        //display merchant info and status.
        //depending on status, merchant actions will be enabled
        return new RestResponse("", Optional.empty());
    }
}
