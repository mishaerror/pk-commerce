package com.pk.commerce.merchant.rest.merchant;

import com.pk.commerce.merchant.api.RestResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    RestResponse welcomePage(@AuthenticationPrincipal OAuth2User user) {
        //display merchant info and status.
        //depending on status, merchant actions will be enabled
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");

        // if email exists and registration pending, show current info
        // else display registration details form

        return new RestResponse(String.format("name: %s, email: %s", name, email), Optional.empty());
    }
}
