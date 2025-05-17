package com.joaovpsguimaraes.magalums.services;

import com.joaovpsguimaraes.magalums.dto.ScheduleNotificationDto;
import com.joaovpsguimaraes.magalums.entity.Notification;
import com.joaovpsguimaraes.magalums.entity.Status;
import com.joaovpsguimaraes.magalums.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        this.notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return this.notificationRepository.findById(notificationId);
    }


    public void cancelNotification(Long notificationId) {
        var notification = this.findById(notificationId);

        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            this.notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var listOfStatus = List.of(
                Status.Values.PENDING.toStatus(), Status.Values.ERROR.toStatus()
        );
        var notifications = this.notificationRepository.findByStatusInAndDateTimeBefore(listOfStatus, dateTime);
        notifications.forEach(notification -> {

        });
    }

    public Consumer<Notification> sendNotification(){
        return n -> {
            //TODO -> Realizar o Envio da notificacao
            n.setStatus(Status.Values.SUCCESS.toStatus());
            this.notificationRepository.save(n);
        };
    }
}
