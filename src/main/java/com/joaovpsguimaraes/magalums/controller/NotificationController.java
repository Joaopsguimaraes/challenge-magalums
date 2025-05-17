package com.joaovpsguimaraes.magalums.controller;

import com.joaovpsguimaraes.magalums.dto.ScheduleNotificationDto;
import com.joaovpsguimaraes.magalums.entity.Notification;
import com.joaovpsguimaraes.magalums.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping
    public ResponseEntity<Void> scheduleNotification(
            @RequestBody ScheduleNotificationDto scheduleNotificationDto
    ) {
        notificationService.scheduleNotification(scheduleNotificationDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotification(
            @PathVariable("notificationId") Long notificationId
    ) {
        var notification = this.notificationService.findById(notificationId);

        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notification.get());
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> cancelNotification(
            @PathVariable("notificationId") Long notificationid
    ){
        this.notificationService.cancelNotification(notificationid);

        return ResponseEntity.noContent().build();
    }
}
