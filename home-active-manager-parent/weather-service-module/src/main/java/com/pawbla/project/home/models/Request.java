package com.pawbla.project.home.models;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

/**
 * Class for create REST Request object
 * @author blapaw
 *
 */
public class Request {

    private HttpMethod method;
    private HttpEntity<Object> entity;
    private String ip;
    private String body;

    public Request(HttpMethod method, HttpEntity<Object> entity, String ip, String body) {
        this.method = method;
        this.entity = entity;
        this.ip = ip;
        this.body = body;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public HttpEntity<Object> getEntity() {
        return entity;
    }

    public String getIp() {
        return ip;
    }

    public String getBody() {
        return body;
    }


}

