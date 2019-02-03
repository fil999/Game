package edu.ssu.netcracker.course.fil.repository;

import edu.ssu.netcracker.course.fil.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by --- on 26.11.2018.
 */
public interface FriendRepository extends JpaRepository<Friend, Long> {

}
