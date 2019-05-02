package cotube.services;

import cotube.domain.Notification;
import cotube.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    public void setProductRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {

        List<Notification> notifications = (List<Notification>) notificationRepository.findAll();
        return notifications;
    }

    @Override
    public void deleteNotification(Notification notification) {
        notificationRepository.delete(notification);
    }

}