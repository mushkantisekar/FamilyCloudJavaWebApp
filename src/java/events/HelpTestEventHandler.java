
package events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;




public class HelpTestEventHandler extends EventHandlerBase{
    String path;
    @Override
       
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
    
    
        
        String tag=request.getParameter("tag");
        
        
        User cur_user = (User) mySession.getAttribute("curr_user");
        
        
        request.setAttribute("user_ident", cur_user.getLastName()+" "+cur_user.getFirstName());
        
        
        
        path="HelpTest.jsp";
        
        
        
        
    }
    
}
