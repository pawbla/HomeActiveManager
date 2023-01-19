package com.pawbla.project.home.weather.service.models;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Class for create REST Response object
 * @author pawbla
 * @param
 *
 */
public class Response {

    private String date;
    private String okResponseDate;
    private boolean isModified;
    private int responseCode;
    private String errorMsg;
    private String body;
    private boolean isError;

    public Response() {
        date = Instant.ofEpochMilli(0L).atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
        okResponseDate = Instant.ofEpochMilli(0L).atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
        responseCode = 0;
        errorMsg = "";
        isModified = true;
        isError = false;
        setBody("");
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getOkResponseDate() {
        return okResponseDate;
    }

    public void setOkResponseDate(String okResponseDate) {
        this.okResponseDate = okResponseDate;
    }
    public boolean isModified() {
        return isModified;
    }
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public boolean isError() {
        return isError;
    }
    public void setErrorAndMessage(String errorMsg, boolean isError) {
        this.errorMsg = errorMsg;
        this.isError = isError;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
