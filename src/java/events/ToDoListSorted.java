
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.ToDoListItemBO;


public class ToDoListSorted extends EventHandlerBase{

    String path;
    
     public static final String devicemobile="Mobile";

    
    @Override
    protected String getURL() {
        
        return path;
    }
    
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        ToDoListItemBO itemBO= new ToDoListItemBO();
        List<ToDoListItemBO> SortedfamToDoList= new ArrayList<ToDoListItemBO>();
        
         User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        String keysort=request.getParameter("sortedTag");
        
        String asc_desc_tag=request.getParameter("ssa");
        
        String keysort2="status";
        
        SortedfamToDoList=itemBO.family_toToDoListItemBO( myItemDAO.getSortedFamilyToDoList(director.getUsername(), keysort,asc_desc_tag));
               
        
        request.setAttribute("SortedfamToDo", SortedfamToDoList);
        request.setAttribute("cur", cur_user);
        
        
        
        String rh =request.getHeader("user-agent");
        
        
        if(detectmobile(rh)){
        
        path="ToDoListSortedMob.jsp";
        }
        
        else{
        
            path="ToDoListSorted.jsp";
        
        }
        
        
        
    }
    
    
    
    
    public boolean detectmobile(String rh){
    
        if(rh.indexOf(devicemobile)!=-1){
        
            return true;
        }
        
            return false;
        
    
    }
    
    
}
