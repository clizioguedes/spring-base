package com.acer.sellercenter.sellercenter.utils.exception;

import java.time.ZonedDateTime;

public record ErrorDTO(ZonedDateTime timestamp, Integer status, String error, String message, String path) {
}
