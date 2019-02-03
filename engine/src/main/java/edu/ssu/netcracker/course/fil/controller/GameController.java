package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.game.Card;
import edu.ssu.netcracker.course.fil.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by --- on 18.12.2018.
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/createGame/{transfer}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long createGame(@PathVariable(value = "transfer") boolean transfer, @RequestBody int count){
        return gameService.createGame(count, transfer);
    }


    @RequestMapping(value = "/{idGame}/{idPlayer}/requestCards", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Card> requestCards(@PathVariable(value = "idGame") long idGame, @PathVariable(value = "idPlayer") long idPlayer, @RequestBody int count){
        return gameService.requestCards(idGame, count, idPlayer);
    }


    @RequestMapping(value = "/{idGame}/requestTrump", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Card requestTrump(@PathVariable(value = "idGame") long idGame){
        return gameService.requestTrump(idGame);
    }


  /*  @RequestMapping(value = "/{idGame}/isCover", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean isCover(@PathVariable(value = "idGame") long idGame, @RequestBody List<Card> cards){
        return gameService.isCover(idGame, cards);
    }

    @RequestMapping(value = "/{idGame}/canPut", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean isPut(@PathVariable(value = "idGame") long idGame, @RequestBody List<Card> cards){
        return gameService.isPut(idGame, cards);
    }


    @RequestMapping(value = "/{idGame}/isCover2/{under}/{over}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  List<Resource<Card>> isNewCover(@PathVariable(value = "idGame") long idGame, @PathVariable(value = "under") Card under, @PathVariable(value = "over") Card over){
        return test();
    }*/


    @RequestMapping(value = "/{idGame}/{idPlayer}/putCard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  Boolean putCard(@PathVariable(value = "idGame") long idGame, @PathVariable(value = "idPlayer") long idPlayer, @RequestBody Card card){
        return gameService.putCard(idGame, idPlayer, card);
    }


    @RequestMapping(value = "/{idGame}/{idPlayer}/cover", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  Boolean cover(@PathVariable(value = "idGame") long idGame, @PathVariable(value = "idPlayer") long idPlayer, @RequestBody Card card){
        return gameService.cover(idGame, idPlayer, card);
    }

    @RequestMapping(value = "/{idGame}/{idPlayer}/takeCards", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  List<Card> takeCards(@PathVariable(value = "idGame") long idGame, @PathVariable(value = "idPlayer") long idPlayer){
        return gameService.takeCards(idGame, idPlayer);
    }

    @RequestMapping(value = "/{idGame}/setExit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  Boolean setExit(@PathVariable(value = "idGame") long idGame){
        return gameService.setExit(idGame);
    }

    @RequestMapping(value = "/{idGame}/{idPlayer}/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  Boolean transfer(@PathVariable(value = "idGame") long idGame, @PathVariable(value = "idPlayer") long idPlayer, @RequestBody Card card){
        return gameService.transfer(idGame, idPlayer, card);
    }

}
