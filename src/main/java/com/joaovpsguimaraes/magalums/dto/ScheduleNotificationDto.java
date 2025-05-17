package com.joaovpsguimaraes.magalums.dto;

import com.joaovpsguimaraes.magalums.entity.Channel;
import com.joaovpsguimaraes.magalums.entity.Notification;
import com.joaovpsguimaraes.magalums.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(
        LocalDateTime dateTime,
        String destination,
        String message,
        Channel.Values channel
) {

    public Notification toNotification(){
        return new Notification(
                dateTime,
                destination,
                message,
                channel.toChannel(),
                Status.Values.PENDING.toStatus()

        );
    }
}
