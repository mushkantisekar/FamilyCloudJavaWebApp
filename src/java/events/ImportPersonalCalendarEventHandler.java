
package events;

import dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ImportPersonalCalendarEventHandler extends EventHandlerBase{
    
    String path;
    @Override
    protected String getURL() {
        return path;
    }
    
    
     public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        path="ImportPersonalCal.jsp";
     }
    
    
    
}
