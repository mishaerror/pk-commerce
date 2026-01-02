package com.pk.commerce.api.merchant;

import java.util.Optional;

public record RestResponse(String status, String message, Optional<?> data) {

}
