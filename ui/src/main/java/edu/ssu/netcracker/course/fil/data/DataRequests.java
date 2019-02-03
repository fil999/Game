package edu.ssu.netcracker.course.fil.data;

import edu.ssu.netcracker.course.fil.entity.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 13.01.2019.
 */
public class DataRequests {

    public Resource<Long> authorization(String email, String password){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8081/player/authorization";
        RestTemplate restTemplate = new RestTemplate();
        List<String> list = new ArrayList<>();
        list.add(email);
        list.add(password);
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(list, headers);
        ResponseEntity<List<Resource<Long>>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Resource<Long>>>(){});
        List<Resource<Long>> c = responseEntity.getBody();
        return c.get(0);
    }

    public Resource<Player> getPlayer(Resource<Long> id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        //String url = "http://localhost:8081/player/findPlayer";
        String url = id.getLink("findPlayer").getHref();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> requestEntity = new HttpEntity<>(id.getContent(), headers);
        ResponseEntity<List<Resource<Player>>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Resource<Player>>>(){});
        List<Resource<Player>> playerResource = responseEntity.getBody();
        playerResource.get(0).getContent().setId(id.getContent());
        return playerResource.get(0);
    }

    public boolean createPlayer(String name, String email, String password, DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = "http://localhost:8081/player/createPlayer";
        RestTemplate restTemplate = new RestTemplate();
        Player player = new Player(email, name, password);
        HttpEntity<Player> requestEntity = new HttpEntity<>(player, headers);
        ResponseEntity<List<Resource<Player>>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Resource<Player>>>(){});
        List<Resource<Player>> player1 = responseEntity.getBody();
        if (!player1.isEmpty()) {
            if (player1.get(0) != null) {
                long id = authorization(email, password).getContent();
                player1.get(0).getContent().setId(id);
                dataPlayer.setPlayer(player1.get(0));
                return true;
            }
        }
        return false;
    }

    public boolean updatePlayer(String name, String email, String password, DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("update");
        RestTemplate restTemplate = new RestTemplate();
        Player player = dataPlayer.getPlayer();
        if (!name.isEmpty()){
            player.setName(name);
        }
        if (!email.isEmpty()){
            player.setEmail(email);
        }
        if (!password.isEmpty()){
            player.setPassword(password);
        }
        HttpEntity<Player> requestEntity = new HttpEntity<>(player, headers);
        ResponseEntity<List<Resource<Player>>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Resource<Player>>>(){});
        List<Resource<Player>> player1 = responseEntity.getBody();
        if (!player1.isEmpty()) {
            if (player1.get(0) != null) {
                player1.get(0).getContent().setId(dataPlayer.getPlayer().getId());
                dataPlayer.setPlayer(player1.get(0));
                return true;
            }
        }
        return false;
    }

    public boolean updatePlayer(DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("update");
        RestTemplate restTemplate = new RestTemplate();
        Player player = dataPlayer.getPlayer();
        HttpEntity<Player> requestEntity = new HttpEntity<>(player, headers);
        ResponseEntity<List<Resource<Player>>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Resource<Player>>>(){});
        List<Resource<Player>> player1 = responseEntity.getBody();
        if (!player1.isEmpty()) {
            if (player1.get(0) != null) {
                player1.get(0).getContent().setId(dataPlayer.getPlayer().getId());
                dataPlayer.setPlayer(player1.get(0));
                return true;
            }
        }
        return false;
    }

    public List<Back> getBacks(DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("backs");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Back>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Back>>(){});
        List<Back> backs = responseEntity.getBody();
        return backs;
    }

    public List<Face> getFaces(DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("faces");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Face>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Face>>(){});
        List<Face> faces = responseEntity.getBody();
        return faces;
    }

    public List<GamingTable> getGamingTables(DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("gamingTables");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<GamingTable>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<GamingTable>>(){});
        List<GamingTable> gamingTables = responseEntity.getBody();
        return gamingTables;
    }

    public boolean addComment(Comment comment, DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("addComment");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Comment> requestEntity = new HttpEntity<>(comment, headers);
        ResponseEntity<Number> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Number.class);
        Number b = responseEntity.getBody();
        if (b.intValue() == 0){
            return true;
        }
        return false;
    }

    public boolean addFriend(String email, DataPlayer dataPlayer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String url = dataPlayer.getHref("addFriend");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(email, headers);
        ResponseEntity<Friend> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Friend.class);
        Friend friend = responseEntity.getBody();
        if (friend != null){
            dataPlayer.getPlayer().getFriends().add(friend);
            return true;
        }
        return false;
    }


}
