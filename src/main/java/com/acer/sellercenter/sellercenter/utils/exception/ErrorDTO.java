package com.acer.sellercenter.sellercenter.utils.exception;

import java.time.Instant;

public record ErrorDTO(Instant timestamp, Integer status, String error, String message, String path) {
}
