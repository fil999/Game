package edu.ssu.netcracker.course.fil.requests;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by --- on 03.02.2019.
 */
public class RequestBot {

    public void newBot(long idGame, boolean transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8085/bot/newBot/" + transfer;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> requestEntity = new HttpEntity<>(idGame, headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
    }
}
