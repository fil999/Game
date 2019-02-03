package edu.ssu.netcracker.course.fil.game;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 22.01.2019.
 */
public class Requests {

    private boolean friend;

    public Requests(boolean friend){
        this.friend = friend;
    }

    public void rezult(long id1, long id2, long rezult){
        if (id1 > 1) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
            String url = "http://localhost:8081/procedures/game";
            RestTemplate restTemplate = new RestTemplate();
            List<Long> list = new ArrayList<>();
            list.add(id1);
            if (id2 > 1) {
                list.add(id2);
            } else {
                list.add(new Long(1));
            }
            list.add(rezult);
            HttpEntity<List<Long>> requestEntity = new HttpEntity<>(list, headers);
            //ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
        }
    }

    private void rezultFriend(long id1, long id2, long rezult){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8081/procedures/friendsGame";
        RestTemplate restTemplate = new RestTemplate();
        List<Long> list = new ArrayList<>();
        list.add(id1);
        if (id2 > 1) {
            list.add(id2);
        } else {
            list.add(new Long(1));
        }
        list.add(rezult);
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(list, headers);
        //ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
    }
}
