package com.pawbla.project.home.weather.service.models;

/**
 *
 * @author blapaw
 *
 */
public class Connector {

    private String name;
    private String provider;
    private String linkToProviderPage;
    private String sensorPosition;

    private Request request;
    private Response response;

    private RequestCounter dailyRequestCounter;
    private RequestCounter sumRequestCounter;


    /**
     * Constructor, contains only parameters which could be set during construct the object
     * @param name
     * @param provider
     * @param linkToProviderPage
     * @param request
     */
    public Connector(String name, String provider, String linkToProviderPage, Request request) {
        this.name = name;
        this.provider = provider;
        this.linkToProviderPage = linkToProviderPage;
        this.request = request;
        this.dailyRequestCounter = new RequestCounter();
        this.sumRequestCounter = new RequestCounter();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public String getLinkToProviderPage() {
        return linkToProviderPage;
    }
    public void setLinkToProviderPage(String linkToProviderPage) {
        this.linkToProviderPage = linkToProviderPage;
    }
    public String getSensorPosition() {
        return sensorPosition;
    }
    public void setSensorPosition(String sensorPosition) {
        this.sensorPosition = sensorPosition;
    }
    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public Response getResponse() {
        return response;
    }
    public void setResponse(Response response) {
        this.response = response;
    }

    public void incrementRequestCnt() {
        dailyRequestCounter.incRequests();
        sumRequestCounter.incRequests();
    }

    public void incrementErrorRequestCnt() {
        sumRequestCounter.incErrorResponseCnt();
        sumRequestCounter.incErrorResponseCnt();
    }

    public void restartDailyRequestCounter() {
        dailyRequestCounter = new RequestCounter();
    }

    public RequestCounter getDailyRequestCounter() {
        return dailyRequestCounter;
    }

    public RequestCounter getSumRequestCounter() {
        return sumRequestCounter;
    }
}

