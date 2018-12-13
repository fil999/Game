package edu.ssu.netcracker.course.fil.repository;

import edu.ssu.netcracker.course.fil.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by --- on 26.11.2018.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select c from Player c where c.email = :email and c.password = :password")
    Player findPlayerByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
