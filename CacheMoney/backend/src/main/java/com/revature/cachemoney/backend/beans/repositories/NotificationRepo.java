package com.revature.cachemoney.backend.beans.repositories;

import com.revature.cachemoney.backend.beans.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {
    Notification save(Notification notification);

    List<Notification> findAll();

    @Query("FROM Notification WHERE has_read = false AND user_id = :user_id")
    List<Notification> findAllByUnread(int user_id);

    @Modifying
    @Query("UPDATE Notification SET has_read = true where user_id = :user_id")
    void updateNotifications(int user_id);
}
