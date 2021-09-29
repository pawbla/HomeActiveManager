package com.pawbla.project.home.models;

/**
 * Class for create REST Response object
 * @author pawbla
 * @param <T>
 *
 */
public class Response {

    private String date;
    private boolean isModified;
    private int responseCode;
    private String errorMsg;
    private String body;
    private boolean isError;

    public Response() {
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
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public boolean isError() {
        return isError;
    }
    public void setError(boolean isError) {
        this.isError = isError;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
