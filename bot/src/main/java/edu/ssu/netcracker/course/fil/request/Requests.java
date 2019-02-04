package edu.ssu.netcracker.course.fil.request;

import edu.ssu.netcracker.course.fil.forgame.Card;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by --- on 03.02.2019.
 */
public class Requests {

    private long idGame;

    public Requests(long idGame) {
        this.idGame = idGame;
    }

    public Card requestTrump(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/requestTrump";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Card> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Card.class);
        Card trump = responseEntity.getBody();
        return trump;
    }

    public List<Card> requestCards(int count, long idPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/" + idPlayer + "/requestCards";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Integer> requestEntity = new HttpEntity<>(count, headers);
        ResponseEntity<List<Card>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Card>>(){});
        List<Card> cards = responseEntity.getBody();
        return cards;
    }

    public boolean putCard(Card card, long idPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/" + idPlayer + "/putCard";          //idPlayer!!!!!!!
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
        Boolean b = responseEntity.getBody();
        return b;
    }

    public boolean cover(Card card, long idPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/" + idPlayer + "/cover";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
        Boolean b = responseEntity.getBody();
        return b;
    }

    public List<Card> takeCards(long idPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/" + idPlayer + "/takeCards";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Card>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Card>>(){});
        List<Card> cards = responseEntity.getBody();
        return cards;
    }

    public boolean setExit(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/setExit";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
        Boolean b = responseEntity.getBody();
        return b;
    }

    public boolean transfer(Card card, long idPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8082/game/"+ idGame + "/" + idPlayer + "/transfer";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Card> requestEntity = new HttpEntity<>(card, headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);
        Boolean b = responseEntity.getBody();
        return b;
    }
}
