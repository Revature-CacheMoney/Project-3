package com.revature.cachemoney.backend.beans.services;

import com.revature.cachemoney.backend.beans.models.Notification;
import com.revature.cachemoney.backend.beans.models.User;
import com.revature.cachemoney.backend.beans.repositories.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mika Nelson, Dylan Wilson, Cullen Kuch, Max Hilken, Tyler Seufert
 */
@Transactional
@Service
public class NotificationService {
    private NotificationRepo notificationRepo;

    @Autowired
    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

    public List<Notification> findAllByUnread(User user) {
        return notificationRepo.findAllByUnread(user.getUserId());
    }

    public void updateNotifications(User user) {
        notificationRepo.updateNotifications(user.getUserId());
    }
}