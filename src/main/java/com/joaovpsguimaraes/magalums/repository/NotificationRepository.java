package com.joaovpsguimaraes.magalums.repository;

import com.joaovpsguimaraes.magalums.entity.Notification;
import com.joaovpsguimaraes.magalums.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> statusList, LocalDateTime dateTime);
}
