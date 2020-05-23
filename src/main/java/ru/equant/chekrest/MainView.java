package ru.equant.chekrest;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.equant.chekrest.ServiceModel.BaseService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Route(value = "home")
public class MainView extends VerticalLayout {
    Grid<BaseService> bsGrid;
    @Autowired
    private final List<BaseService> services;

    @Autowired
    private final RestTemplate restTemplate;

    public MainView(List<BaseService> services, RestTemplate restTemplate) {
        this.services = services;
        this.restTemplate = restTemplate;


        Select<Stand> select = new Select<>();
        select.setLabel("Стенд");
        select.setTextRenderer(stand -> stand.name());
        select.setItems(Stand.values());
        select.setValue(Stand.TEST);
        select.addValueChangeListener(selectStandComponentValueChangeEvent ->
                services.forEach(baseService ->
                {
                    baseService.setStand(selectStandComponentValueChangeEvent.getValue());
                    baseService.setCheckValue(null);
                    bsGrid.getDataProvider().refreshAll();
                }));


        Button buttonRun = new Button("RUN");
         buttonRun.addClickListener(buttonClickEvent -> {
             services.forEach(service -> service.setCheckValue(check(service)));
             bsGrid.getDataProvider().refreshAll();
         });


//                item -> !"Developers Journey and Onboarding"
//                        .equals(item.getName()));


        // services.forEach(baseService -> rests.add(baseService));

        bsGrid = new Grid<>();
        bsGrid.setItems(services);
        bsGrid.addColumn(BaseService::getName).setHeader("Имя Сервиса");
        Grid.Column<BaseService> swaggerColumn = bsGrid.addColumn(new ComponentRenderer<>((service) -> {
            Anchor anchor1 = new Anchor();
            anchor1.setText("swagger");
            anchor1.setTarget("_blank");
            anchor1.setHref(service.getSwaggerLink());
            return anchor1;
        })).setHeader("Swagger");
        bsGrid.addColumn(service -> service.getCheckValue() == null ? "" : service.getCheckValue().toString());
        bsGrid.setHeightByRows(true);


        HorizontalLayout horizontalLayout = new HorizontalLayout(select, buttonRun);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        add(horizontalLayout);
        add(bsGrid);


    }

    Boolean check(BaseService service) {
        ResponseEntity<String> exchange = null;
        try {
            exchange = restTemplate.exchange(service.getCheckUrl(), service.getMethod(), getHttpEntity(),String.class);
        }catch(HttpClientErrorException | HttpServerErrorException e){
            int code = e.getStatusCode().value();
            return service.getResponseCodes().stream().anyMatch(integer -> integer == code);
        }catch (ResourceAccessException e){
  //          Notification notification = new Notification("ResourceAccessException. Возможно не работает VPN");
       //    notification.open();
            return false;
        }catch (Exception e){
            return false;
        }
        if(exchange.getBody()!= null && exchange.getBody().contains("<!DOCTYPE html>")){
            return  null;}
        ResponseEntity<String> finalExchange = exchange;
        return service.getResponseCodes().stream().anyMatch(integer -> integer == finalExchange.getStatusCode().value());
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer ");
        return new HttpEntity(headers);
    }

    private Boolean checkDb() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection("jdbc:postgresql://172.17.66.142:5432/camunda?currentSchema=entity");
        }catch(Exception e){
            return false;
        }
        return true;
    }

}