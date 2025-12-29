package com.pk.commerce.merchant.api;

import java.util.Optional;

public record RestResponse(String status, String message, Optional<?> data) {

}
