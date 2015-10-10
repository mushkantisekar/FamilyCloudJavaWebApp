
package events;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutEventHandler extends EventHandlerBase{
    String path;
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mySession.invalidate();
        
        path="StartPage.jsp";
        
        request.setAttribute("alert_class", "alert alert-success");
        request.setAttribute("alert_message", "You have successfully loged out!");
    }
    
}
