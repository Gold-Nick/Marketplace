package com.edu.kpi.marketplace.marketplace.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiError {

    private Instant timestamp;

    private int status;

    private String error;

    private String message;

    private Map<String, String> validationErrors;
}
