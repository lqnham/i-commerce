package com.icommerce.iproduct.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class InterCommunicationService {
    public static final String AUDIT_URL = "http://localhost:8003/";
    public static final String AUDIT_PROPERTY_ACTION = "action";
    public static final String AUDIT_PROPERTY_CONTENT = "actionContent";

    private static InterCommunicationService INSTANCE = new InterCommunicationService();

    private InterCommunicationService(){};

    public static InterCommunicationService getInstance(){
        return INSTANCE;
    }

    public void triggerAudit(String action, String content) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.put(AUDIT_PROPERTY_ACTION, Collections.singletonList(action));
        form.put(AUDIT_PROPERTY_CONTENT, Collections.singletonList(content));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, httpHeaders);
        restTemplate.postForObject(AUDIT_URL, requestEntity, String.class);
    }
}
