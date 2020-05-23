package ru.equant.chekrest.ServiceModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class ServiceConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    BaseService issueAdapter() {
        return new BaseService("issueadapter",
                "/api/v1/issue/statusList",
                HttpMethod.GET, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService issueManager() {
        return new BaseService("issuemanager",
                "/api/v1/issueCategory",
                HttpMethod.GET, Arrays.asList(200)) {
        };
    }

    @Bean
    BaseService newsAdapter() {
        return new BaseService("newsadapter",
                "/api/v1/news",
                HttpMethod.POST, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService newsManager() {
        return new BaseService("newsmanager",
                "/api/v1/newsBaseInfo",
                HttpMethod.GET, Arrays.asList(200)) {
        };
    }

    @Bean
    BaseService surveyAdapter() {
        return new BaseService("surveyadapter",
                "/api/v1/survey",
                HttpMethod.POST, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService surveyManager() {
        return new BaseService("surveymanager",
                "/api/v1/survey",
                HttpMethod.GET, Arrays.asList(200)) {
        };
    }

    @Bean
    BaseService attachManager() {
        return new BaseService("attachmanager",
                "/api/v1/picture/5db82824149b3f0001b79aea",
                HttpMethod.GET, Arrays.asList(400)) {
        };
    }

    @Bean
    BaseService camundaAdapter() {
        return new BaseService("camundaadapter",
                "/api/v1/process-definition/key/issue/start",
                HttpMethod.POST, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService camundaManager() {
        return new BaseService("camundamanager",
                "/api/v1/task",
                HttpMethod.POST, Arrays.asList(200)) {
        };
    }

    @Bean
    BaseService fiasManager() {
        return new BaseService("fiasmanager",
                "/api/v1/fias/addressResponse/list",
                HttpMethod.GET, Arrays.asList(400)) {
        };
    }

    @Bean
    BaseService fileAdapter() {
        return new BaseService("fileadapter",
                "/api/v1/file/dynamic/123",
                HttpMethod.GET, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService keycloAkadapter() {
        return new BaseService("keycloakadapter",
                "/api/v1/cities",
                HttpMethod.GET, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService localAdapter() {
        return new BaseService("localadapter",
                "/api/v1/user/profile",
                HttpMethod.GET, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService notificationAdapter() {
        return new BaseService("notificationadapter",
                "/api/v1/notification/error",
                HttpMethod.POST, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService notificationManager() {
        return new BaseService("notificationmanager",
                "/api/v1/notification/templateByType/1",
                HttpMethod.GET, Arrays.asList(400)) {
        };
    }

    @Bean
    BaseService repairworksAdapter() {
        return new BaseService("repairworksadapter",
                "/api/v1/repairWorksFullInfo/1",
                HttpMethod.GET, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService repairworksManager() {
        return new BaseService("repairworksmanager",
                "/api/v1/repairWorksFullInfo/1?unitCode=1",
                HttpMethod.GET, Arrays.asList(403)) {
        };
    }

    @Bean
    BaseService urbanplanAdapter() {
        return new BaseService("urbanplanadapter",
                "/api/v1/urbanPlan",
                HttpMethod.POST, Arrays.asList(401)) {
        };
    }

    @Bean
    BaseService urbanplanManager() {
        return new BaseService("urbanplanmanager",
                "/api/v1/urbanPlanBaseInfo/5db82824149b3f0001b79aea?unitCode=1",
                HttpMethod.GET, Arrays.asList(400, 500)) {
        };
    }
}
