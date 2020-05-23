package ru.equant.chekrest.ServiceModel;

import org.springframework.http.HttpMethod;
import ru.equant.chekrest.Stand;

import java.util.List;
import java.util.Map;


public abstract class BaseService {
    private String name;
    private String checkUrl;
    private List<Integer> responseCodes;
    private HttpMethod method;
    private Map<String, String> headers;
    private Stand stand = Stand.TEST;
    private Boolean checkValue;


    BaseService(String name, String checkUrl, HttpMethod method, List<Integer> responseCodes) {
        this.name = name;
        this.checkUrl = checkUrl;
        this.method = method;
        this.responseCodes = responseCodes;
//        add(new Span(name));
//
//        Anchor anchor = new Anchor();
//        anchor.setText("swagger");
//        anchor.setTarget("_blank");
//        anchor.setHref(stand.getSwaggerLink(this.name));
//        add(anchor);
    }
    public String getSwaggerLink() {
    return stand.getSwaggerLink(name);
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckUrl() {
        return stand.getUrl(name).append(checkUrl).toString();
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public List<Integer> getResponseCodes() {
        return responseCodes;
    }

    public void setResponseCodes(List<Integer> responseCodes) {
        this.responseCodes = responseCodes;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setCheckValue(Boolean checkValue){
        this.checkValue = checkValue;
    }

    public Boolean getCheckValue() {
        return checkValue;
    }
}
