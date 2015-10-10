
package events;

import dao.DaoFactory;
import dao.ToDoListItemDao;
import dao.UserDao;
import modelBO.ToDoListItemBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;
import org.json.simple.JSONObject;


public class ToDoListEventHandler extends EventHandlerBase {
    
    public static final String devicemobile="Mobile";

    String path;

    @Override
    protected String getURL() {
        return path;
    }

    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ToDoListItemDao myItemDAO = mySqlFactory.getToDoListItemDao();
        UserDao myUserDAO = mySqlFactory.getUserDao();
        ToDoListItemBO itemBO = new ToDoListItemBO();
        List<ToDoListItemBO> famToDoList = new ArrayList<ToDoListItemBO>();
        
        

        User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());

        famToDoList = itemBO.family_toToDoListItemBO(myItemDAO.getFamilyToDoList(director.getUsername()));

        //List<String> assigned_wrap = new ArrayList<>();
//        for(int i=0;i<famToDoList.size();i++){
//            String assignedTo= famToDoList.get(i).getAssignedTo();
//            List<String> assiggned_usernames_list= Arrays.asList(assignedTo.split("\\s*,\\s*"));
//            
//        }
        
        
        
        JSONObject jsme = new JSONObject();

        if (request.getParameter("mtag") != null) {

            String temp = request.getParameter("mtag");

            if (temp.equals("insert")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> TO-DoList Item Has Succefully Added!");

            } else if (temp.equals("update")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> TO-DoList Item Has Succefully Updated!");

            } else if (temp.equals("delete")) {

                jsme.put("classs", "alert alert-success alert_messa col-sm-8 ");
                jsme.put("message", "<span class=\"glyphicon glyphicon-ok-circle\"></span> TO-DoList Item Has Succefully Deleted!");

            }

            request.setAttribute("noti_message", jsme);

        }

        UserBO usBo = new UserBO();

        List<UserBO> assignedListTODO = new ArrayList<UserBO>();

        assignedListTODO = usBo.family_toUserBo(myUserDAO.getFamilyMembersWithDirector(cur_user.getUsername()));

        request.setAttribute("userlistTODO", assignedListTODO);

        
        request.setAttribute("famToDo", famToDoList);

        request.setAttribute("cur", cur_user);
        if(famToDoList.isEmpty()){
            request.setAttribute("emptya", 1);
        }else{
            request.setAttribute("emptya", 0);
        }
        
        String rh =request.getHeader("user-agent");
        
        
        if(detectmobile(rh)){
        
            path="ToDoListMob.jsp";
        
        }else{
        
        
            path = "ToDoList.jsp";
        
        }
        
        
        
        
    }
    
    
    
    
    
    public boolean detectmobile(String rh){
    
        if(rh.indexOf(devicemobile)!=-1){
        
            return true;
        }
        
            return false;
        
    
    }
    
    
    
    
}
