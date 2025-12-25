package com.pk.commerce.merchant.rest.merchant;

import com.pk.commerce.merchant.api.RestResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

//TODO removal
public class MerchantController {
    //we should get redirected here from login.
    @GetMapping(value = "/welcome", produces = "application/json;charset=UTF-8")
    @ResponseBody
    RestResponse welcomePage(@AuthenticationPrincipal OAuth2User user) {
        //display merchant info and status.
        //depending on status, merchant actions will be enabled
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");

        return new RestResponse("User found.", Optional.of(String.format("{name: %s, email: %s}", name, email)));
    }
}
