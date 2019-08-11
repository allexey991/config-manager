package com.crif.contact.cloud.manger.model;

import java.io.Serializable;

public class SuccessResponse implements Serializable {
    private static final long serialVersionUID = 6091860730101502652L;
    private String data;

    public SuccessResponse(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
