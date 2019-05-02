package cotube.services;

import cotube.domain.Notification;

import java.util.List;

public interface NotificationService {
    Notification addNotification(Notification notification); //adds a comic to db *C
    List<Notification> getAllNotifications(); //get all followUser pairs in db *R
    void deleteNotification(Notification notification);
}
