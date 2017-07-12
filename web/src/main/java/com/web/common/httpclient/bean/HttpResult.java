package com.hikvision.mdp.web.common.httpclient.bean;

public class HttpResult {
    private Integer statusCode;

    private String data;

    public HttpResult() {// TODO Auto-generated constructor stub
    }


    public HttpResult(Integer statusCode, String data) {
        this.statusCode = statusCode;
        this.data = data;

    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
