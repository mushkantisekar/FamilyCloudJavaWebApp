
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;


public class getImageEventHandler extends EventHandlerBase{
    String path;
    
    @Override
    protected String getURL() {
        return path;
    }
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws IOException{
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        UserBO myUserBO = new UserBO();
        
        String username=request.getParameter("username");
        User user = myUserDAO.getUser(username);
        response.setContentType("image/jpeg");
        //response.setContentType("application/pdf");
        response.setContentLength(user.getPicture().length);
        response.getOutputStream().write(user.getPicture());
        //response.getOutputStream().write(scale(user.getPicture(), 500, 500));
        
        path=null;
        
    }
    
   
    
}
