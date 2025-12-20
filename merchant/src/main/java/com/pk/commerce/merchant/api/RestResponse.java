package com.pk.commerce.merchant.api;

import java.util.Optional;

public record RestResponse(String message, Optional<?> data) {

}
