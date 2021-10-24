package com.example.lib.model;

import java.util.List;

public class DanhMucModel {
    boolean success;
    String message;
    List<DanhMuc> result;

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

    public List<DanhMuc> getResult() {
        return result;
    }

    public void setResult(List<DanhMuc> result) {
        this.result = result;
    }
}
