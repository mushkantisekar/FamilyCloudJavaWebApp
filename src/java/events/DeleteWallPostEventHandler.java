
package events;

import dao.DaoFactory;
import dao.WallPostDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteWallPostEventHandler extends EventHandlerBase{
    String path;
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        WallPostDao myPostDAO = mySqlFactory.getWallPostDao();
        boolean success;
        
        int itemID;
        String test=request.getParameter("id");
        
        itemID= Integer.parseInt(test);
        success= myPostDAO.deletePost(itemID);
        
        if(success){
            path="controller_servl?event=WALLPOST&mtag=delete";

        }
    }
    
    
}
