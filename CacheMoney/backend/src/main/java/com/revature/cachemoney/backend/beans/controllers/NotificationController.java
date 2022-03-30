package com.revature.cachemoney.backend.beans.controllers;

import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Notification;
import com.revature.cachemoney.backend.beans.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/all")
    List<Notification> findAll() {
        return notificationService.findAll();
    }

    @GetMapping("/unread/{user_id}")
    List<Notification> findAllUnread(@PathVariable int user_id) {
        return notificationService.findAllByUnread(user_id);
    }

    @PostMapping("/add")
    Notification findAllUnread(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/update/{user_id}")
    void updateNotifications(@PathVariable int user_id) {
        notificationService.updateNotifications(user_id);
    }
}
