package ru.equant.chekrest.ServiceModel;

import ru.equant.chekrest.Stand;

public class BaseBd {
    private Stand stand = Stand.TEST;
    private Boolean checkValue;
    private String url = "jdbc:postgresql://172.17.66.142:5432/camunda?currentSchema=entity";

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public Boolean getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(Boolean checkValue) {
        this.checkValue = checkValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
