package edu.ssu.netcracker.course.fil.repository;

import edu.ssu.netcracker.course.fil.entity.LastResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by --- on 07.12.2018.
 */
@Repository
public interface LastResultsRepository extends JpaRepository<LastResult, Long>{

    @Query("select c.id, c.opponent, c.result from LastResult c where c.player = :player")
    List<LastResult> findLastResultByPlayer(@Param("player") long player);

}
