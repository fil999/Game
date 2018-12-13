package edu.ssu.netcracker.course.fil.repository;

import edu.ssu.netcracker.course.fil.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by --- on 26.11.2018.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
