package edu.ssu.netcracker.course.fil;

import edu.ssu.netcracker.course.fil.game.Card;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by --- on 18.12.2018.
 */
public class Test {
    List<Socket> sockets = new ArrayList<>();
    public void request(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        String url = "http://localhost:8082/game/1/isCover";

        RestTemplate restTemplate = new RestTemplate();
       // HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        ResponseEntity<List<Resource<Card>>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Resource<Card>>>(){},
//                new Integer(5));
//
//
//        List<Resource<Card>> resources = responseEntity.getBody();
//        //System.out.println(resources);
//
//        Link link = resources.get(0).getLinks().get(0);
//        List<Link> links= resources.get(0).getLinks();
//        Links links1 = new Links(links);

        Map<String, List<Card>> map = new HashMap<>();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(4, 'a'));
        cards.add(new Card(4, 'a'));
        map.put("cards", cards);

        HttpEntity<List<Card>> requestEntity = new HttpEntity<>(cards, headers);

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Boolean.class);

        Boolean b = responseEntity.getBody();

    }


    public void socets() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8083, 10);
        Socket connection;
        while (true) {
            connection = serverSocket.accept();
            sockets.add(connection);
        }

    }
}
