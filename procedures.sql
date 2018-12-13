create procedure game_func
(id_player_game in number, id_opponent in number, rezult in number)
is 
number_result number;
begin
    if rezult = 0 then
        update player
            set tie_player = (select (tie_player + 1) from Player where id_player = id_player_game)
            where id_player = id_player_game;
    elsif rezult = 1 then
        update player
            set wins_player = (select (wins_player + 1) from Player where id_player = id_player_game),
                balance = (select (balance + 40) from Player where id_player = id_player_game)
            where id_player = id_player_game;
    elsif rezult = 2 then
        update player
            set losing_player = (select (losing_player + 1) from Player where id_player = id_player_game)
            where id_player = id_player_game;
    end if;
    select count(*) INTO number_result from last_results_player where player_result = id_player_game;
    if number_result = 10 then
        delete from last_results_player
            where ID_RESULT = (select min(id_result) from last_results_player where player_result = id_player_game);
    end if;
    insert into last_results_player (player_result, opponent_result, result_result) values (id_player_game, id_opponent, rezult);
end;




create procedure game_friend_func
(id_friends_game in number, rezult in number)
is 
number_result number;
begin
    select count(*) INTO number_result from RESULTS_FRIEND where FRIEND_RESULTS_FRIEND = id_friends_game;
    if number_result = 10 then
        delete from RESULTS_FRIEND
            where ID_RESULTS_FRIEND = (select min(ID_RESULTS_FRIEND) from RESULTS_FRIEND where FRIEND_RESULTS_FRIEND = id_friends_game);
    end if;
    insert into RESULTS_FRIEND(FRIEND_RESULTS_FRIEND, RESULT_RESULTS_FRIEND) values (id_friends_game, rezult);
end;




create procedure save_game2_func
(player1 in number, player2 in number, cards1 in varchar2, cards2 in varchar2, cardsResidual in varchar2, trump in varchar2)
is
begin
    delete from SAVED_GAME_2_PLAYER
        where PLAYER_1_SAVED_2 = player1 AND PLAYER_2_SAVED_2 = player2;
    insert into SAVED_GAME_2_PLAYER (player_1_saved_2, player_2_saved_2, cards_player_1_saved_2, cards_player_2_saved_2, cards_residual_saved_2, trump_card_saved_2)
        values (player1, player2, cards1, cards2, cardsResidual, trump);
end;




create procedure save_game3_func
(player1 in number, player2 in number, player3 in number, cards1 in varchar2, cards2 in varchar2, cards3 in varchar2, cardsResidual in varchar2, trump in varchar2)
is
begin
    delete from SAVED_GAME_3_PLAYER
        where PLAYER_1_SAVED_3 = player1 AND (PLAYER_2_SAVED_3 = player2 OR PLAYER_3_SAVED_3 = player2) AND (PLAYER_2_SAVED_3 = player3 OR PLAYER_3_SAVED_3 = player3);
    insert into SAVED_GAME_3_PLAYER (player_1_saved_3, player_2_saved_3, player_3_saved_3, cards_player_1_saved_3, cards_player_2_saved_3, cards_player_3_saved_3, cards_residual_saved_3, trump_card_saved_3)
        values (player1, player2, player3, cards1, cards2, cards3, cardsResidual, trump);
end;




create procedure save_game4_func
(player1 in number, player2 in number, player3 in number, player4 in number, cards1 in varchar2, cards2 in varchar2, cards3 in varchar2, cards4 in varchar2, cardsResidual in varchar2, trump in varchar2)
is
begin
    delete from SAVED_GAME_4_PLAYER
        where PLAYER_1_SAVED_4 = player1 AND (PLAYER_2_SAVED_4 = player2 OR PLAYER_3_SAVED_4 = player2 OR PLAYER_4_SAVED_4 = player2) 
        AND (PLAYER_2_SAVED_4 = player3 OR PLAYER_3_SAVED_4 = player3 OR PLAYER_4_SAVED_4 = player3) AND (PLAYER_2_SAVED_4 = player4 OR PLAYER_3_SAVED_4 = player4 OR PLAYER_4_SAVED_4 = player4);
    insert into SAVED_GAME_4_PLAYER (player_1_saved_4, player_2_saved_4, player_3_saved_4, player_4_saved_4, cards_player_1_saved_4, cards_player_2_saved_4, cards_player_3_saved_4, cards_player_4_saved_4, cards_residual_saved_4, trump_card_saved_4)
        values (player1, player2, player3, player4, cards1, cards2, cards3, cards4, cardsResidual, trump);
end;