package ru.equant.chekrest;

public enum Stand {
    TEST("-rest.ag-test.prostream.ru", true),
    DEV("-rest.ag-dev.prostream.ru", false),
    STAGE("-rest.ag-stage.prostream.ru", true),
    PROD("-rest.activegorozhanin.ru", true);

    private String pathOfPath;
    private String dbUrl;
    private Boolean isHttps;

    Stand(String url, boolean isHttps) {
        this.pathOfPath = url;
        this.isHttps = isHttps;
    }

    public String getSwaggerLink(String service) {
        return getUrl(service).append("/swagger-ui.html#").toString();
    }


    public StringBuilder getUrl(String service) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.isHttps) {
            stringBuilder.append("https://");
        } else {
            stringBuilder.append("http://");
        }
        stringBuilder.append(service)
                .append(pathOfPath);
        return stringBuilder;
    }
}
