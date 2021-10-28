package com.example.lib.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DanhMuc implements Serializable  {
    public class Result implements Serializable
    {
        private int id;
        private String tendanhmuc;
        private String hinhdanhmuc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTendanhmuc ()
        {
            return tendanhmuc;
        }

        public void setTendanhmuc (String tendanhmuc)
        {
            this.tendanhmuc = tendanhmuc;
        }

        public String getHinhdanhmuc ()
        {
            return hinhdanhmuc;
        }

        public void setHinhdanhmuc (String hinhdanhmuc)
        {
            this.hinhdanhmuc = hinhdanhmuc;
        }

    }
    boolean success;
    String message;
    List<Result> result;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}

