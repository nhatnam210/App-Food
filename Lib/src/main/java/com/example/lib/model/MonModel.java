package com.example.lib.model;

import java.util.List;

public class MonModel {
    boolean success;
    String message;
    List<Mon> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Mon> getResult() {
        return result;
    }

    public void setResult(List<Mon> result) {
        this.result = result;
    }
}
