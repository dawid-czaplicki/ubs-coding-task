package com.gft.ddcz.ubs.codingtask.validation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {

    public static enum Result {
        SUCCESS, ERROR
    }

    private Result result = Result.SUCCESS;
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        result = Result.ERROR;
        errors.add(error);
    }

    public boolean hasError() {
        return result == Result.ERROR;
    }
}
