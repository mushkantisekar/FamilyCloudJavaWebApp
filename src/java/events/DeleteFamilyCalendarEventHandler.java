
package events;

import dao.DaoFactory;
import dao.FamCalEventDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteFamilyCalendarEventHandler extends EventHandlerBase{
    String path;
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        FamCalEventDao myEventDao= mySqlFactory.getFamCalEventDao();
        boolean success;
        
        String id= request.getParameter("id");
        int ID= Integer.parseInt(id);
        
        success= myEventDao.deleteFamCalEvent(ID);
        if(success){
            path="controller_servl?event=FAMCAL&mtag=delete";
        }else{
            
        }
    }
    
    
    
}
