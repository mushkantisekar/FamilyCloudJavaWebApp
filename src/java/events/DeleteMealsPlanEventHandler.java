
package events;

import dao.DaoFactory;
import dao.MealsPlanEventDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteMealsPlanEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        MealsPlanEventDao myEventDao= mySqlFactory.getMealsPlanEventDao();
        boolean success;
        
        String id= request.getParameter("id");
        int ID= Integer.parseInt(id);
        
        success= myEventDao.deleteMealsPlanEvent(ID);
        if(success){
            path="controller_servl?event=MEALSPLAN&mtag=delete";
        }else{
            
        }
    }
    
}
