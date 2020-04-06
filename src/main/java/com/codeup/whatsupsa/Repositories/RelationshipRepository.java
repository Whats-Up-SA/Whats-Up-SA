package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Relationship;
import com.codeup.whatsupsa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
//    @Query("SELECT a FROM Relationship a WHERE a.userOneID = '%term%' OR a.userOneID = '%term%' AND a.status = 0 AND a.actionUserID <> '%term%'")
//    List<Relationship> viewPendingRequests(@Param("term") User user);

    @Query("FROM Relationship a WHERE (a.userOneID = ?1 OR a.userTwoID = ?1) AND a.status = 0 AND a.actionUserID <> ?1")
    List<Relationship> viewPendingRequests(User user);

    @Query("FROM Relationship a WHERE (a.userOneID = ?1 OR a.userTwoID = ?1) AND a.status = 1")
    List<Relationship> viewFriendsList(User user);

    @Query("FROM Relationship a WHERE a.userOneID = ?1 AND a.userTwoID = ?2")
    Relationship returnRelationshipFriends(User user, User checkUser);

    @Query("SELECT count(a) FROM Relationship a WHERE a.userOneID = ?1 AND a.userTwoID = ?2")
    Long getRelationshipByFriends(User user, User checkUser);

    @Query("SELECT count(a) FROM Relationship a WHERE a.userOneID = ?1 AND a.userTwoID = ?2 AND a.status = 1")
    Long checkFriendship(User user, User checkUser);

    @Query("SELECT count(a) FROM Relationship a WHERE a.userOneID = ?1 AND a.userTwoID = ?2 AND a.status = 2")
    Long checkDecline(User user, User checkUser);

    @Query("SELECT count(a) FROM Relationship a WHERE a.userOneID = ?1 AND a.userTwoID = ?2 AND a.status = 0")
    Long checkPending(User user, User checkUser);
}
