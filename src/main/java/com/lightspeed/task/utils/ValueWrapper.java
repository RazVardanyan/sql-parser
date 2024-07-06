package com.lightspeed.task.utils;

import lombok.Builder;

@Builder
public record ValueWrapper<T>(T value, boolean success) {
}
