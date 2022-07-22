package com.pawbla.project.home.weather.service.models;

public class RequestCounter {
    private int requestsCnt;
    private int errorResponseCnt;

    public RequestCounter() {
        requestsCnt = 0;
        errorResponseCnt = 0;
    }

    public int getRequestsCnt() {
        return requestsCnt;
    }

    public void incRequests() {
        this.requestsCnt++;
    }

    public int getErrorResponseCnt() {
        return errorResponseCnt;
    }

    public void incErrorResponseCnt() {
        this.errorResponseCnt++;
    }
}
