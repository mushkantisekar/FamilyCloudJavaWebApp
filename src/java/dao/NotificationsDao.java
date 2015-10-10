
package dao;

import java.util.List;
import model.Notifications;


public interface NotificationsDao {
    
    public boolean UpdateNotificationsEvent(Notifications notifications);
    
    public boolean MarkAsReadA(int id);
    
    public boolean MarkAsReadB(int id);

    public List<Notifications> getNotifications(String username);
    
    public List<Notifications> getMessageNotifications(String username);
    
    public Notifications getNotification(int id);
    
    public boolean insertNotification(Notifications notification);
    
    public int getUnreadNotifications(String username);
    
    public int getUnreadMessageNotifications(String username);

    public int getLastInsertedNotificationID();
    
    public boolean deleteNotification(int id);
    
    public boolean updateNotificationReminder(Notifications notifications);
    
}
