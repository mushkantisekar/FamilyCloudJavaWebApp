
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import dao.NotificationsDao;
import dao.PerCalEventDao;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FamCalEvent;
import model.Notifications;
import model.PerCalEvent;
import model.User;
import org.joda.time.DateTime;


public class getNotificationDetailsEventHandler extends EventHandlerBase {

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        NotificationsDao myNotificationsDao = mySqlFactory.getNotificationsDao();

        User cur_user = (User) mySession.getAttribute("curr_user");

        String str_id = request.getParameter("id");
        int id = Integer.parseInt(str_id);
        Notifications notification = new Notifications();
        notification = myNotificationsDao.getNotification(id);

        if (notification.getIsReadB().equals("N")) {
            boolean success = myNotificationsDao.MarkAsReadB(id);

            if (notification.getNotification_type().equals("reminder_f")) {
                FamCalEventDao myfamcal = mySqlFactory.getFamCalEventDao();
                FamCalEvent event = myfamcal.getFamCalEvent(notification.getReferred_id());
                if (event.getStartRepeatDate() != null) {
                    Date date_calc = calculate_RepeatDate(event.getStart_date(), event.getEndRepeatDate(), event.getRepeat_every(), event.getRepeatTime());

                    if (date_calc != null) {
                        Date date_desi;
                        date_desi = calculate_Date(event.getNotificationTime(), event.getNotificationDate(), date_calc);
                        notification.setDate_created(date_desi);
                        boolean success2 = myNotificationsDao.UpdateNotificationsEvent(notification);
                        if (success) {

                        } else {

                        }

                    }
                } else {
                    //myNotificationsDao.deleteNotification(id);
                }
            } else if (notification.getNotification_type().equals("reminder_p")) {
                PerCalEventDao myPercal = mySqlFactory.getPerCalEventDao();
                PerCalEvent event = myPercal.getPerCalEvent(notification.getReferred_id());
                if (event.getStartRepeatDate() != null) {
                    Date date_calc = calculate_RepeatDate(event.getStart_date(), event.getEndRepeatDate(), event.getRepeat_every(), event.getRepeatTime());

                    if (date_calc != null) {
                        Date date_desi;
                        date_desi = calculate_Date(event.getNotificationTime(), event.getNotificationDate(), date_calc);
                        notification.setDate_created(date_desi);
                        boolean success2 = myNotificationsDao.UpdateNotificationsEvent(notification);
                        if (success2) {

                        } else {

                        }

                    }
                } else {
                    //myNotificationsDao.deleteNotification(id);
                }
            } else if (notification.getNotification_type().equals("reminder_m")) {
                PerCalEventDao myPercal = mySqlFactory.getPerCalEventDao();
                PerCalEvent event = myPercal.getPerCalEvent(notification.getReferred_id());
                if (event.getStartRepeatDate() != null) {
                    Date date_calc = calculate_RepeatDate(event.getStart_date(), event.getEndRepeatDate(), event.getRepeat_every(), event.getRepeatTime());

                    if (date_calc != null) {
                        Date date_desi;
                        date_desi = calculate_Date(event.getNotificationTime(), event.getNotificationDate(), date_calc);
                        notification.setDate_created(date_desi);
                        boolean success2 = myNotificationsDao.UpdateNotificationsEvent(notification);
                    }
                } else {
                    //myNotificationsDao.deleteNotification(id);
                }
            }
        }
        request.setAttribute("notification", notification);

        path = "controller_servl?event=NOTIFICATIONS&notification_type=" + notification.getNotification_type();
    }

    public Date calculate_RepeatDate(Date start_date, Date endRepeat_Date, int repeat_time, String repeat_every) {

        DateTime dateTime = new DateTime(start_date);
        if (repeat_every.equals("Weekly")) {
            dateTime = dateTime.plusWeeks(repeat_time);
        } else if (repeat_every.equals("Daily")) {
            dateTime = dateTime.plusDays(repeat_time);
        } else if (repeat_every.equals("Monthly")) {
            dateTime = dateTime.plusMonths(repeat_time);
        }
        DateTime dateTime_endrRepeat = new DateTime(endRepeat_Date);
        if (dateTime.compareTo(dateTime_endrRepeat) < 0) {
            return dateTime.toDate();
        } else {
            return null;
        }
    }

    public Date calculate_Date(int time, String Date_period, Date start_date) {
        DateTime dateTime = new DateTime(start_date);

        if (Date_period.equals("Minutes")) {
            dateTime = dateTime.minusMinutes(time);
        } else if (Date_period.equals("Hours")) {
            dateTime = dateTime.minusHours(time);
        } else if (Date_period.equals("Days")) {
            dateTime = dateTime.minusDays(time);
        } else if (Date_period.equals("Weeks")) {
            dateTime = dateTime.minusWeeks(time);
        }
        Date datef = dateTime.toDate();
        return datef;
    }

}
