
package events;

import dao.DaoFactory;
import dao.UserDao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.UserBO;
import org.json.simple.JSONObject;



public class SignUpEventHandler extends EventHandlerBase {
private String path;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param mySession
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    @Override
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        boolean success_signup = false;
        
        String firstname= request.getParameter("firstname_n");
        String lastname= request.getParameter("lastname_n");
        String username= request.getParameter("username_n");
        String password= request.getParameter("password_n");
        String birthdate= request.getParameter("birthdate_n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate_d = null;
        try {
            birthdate_d = sdf.parse(birthdate);
        } catch (ParseException ex) {
            Logger.getLogger(SignUpEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String email = request.getParameter("email_n");
        String town = request.getParameter("town_n");
        
        
        UserBO userbo= new UserBO();
        userbo.setFirstName(firstname);
        userbo.setLastName(lastname);
        userbo.setUsername(username);
        userbo.setPassword(password);
        userbo.setBirthdate(birthdate_d);
        userbo.setEmail(email);
        userbo.setTown(town);
        userbo.setDirector("Y");
        
        
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        User myUser= new User();
        myUser.setFirstName(userbo.getFirstName());
        myUser.setLastName(userbo.getLastName());
        myUser.setUsername(userbo.getUsername());
        myUser.setPassword(userbo.getPassword());
        myUser.setBirthdate(userbo.getBirthdate());
        myUser.setEmail(userbo.getEmail());
        myUser.setTown(userbo.getTown());
        myUser.setDirector(userbo.getDirector());
        myUser.setPicture(extractBytes(request));
        success_signup= myUserDAO.insertUser(myUser);
        if(success_signup){
            JSONObject obj = new JSONObject();
            User cur_user = myUserDAO.getUser(username);
            mySession.setAttribute("curr_user", cur_user);
            obj.put("cur_user", cur_user);
            request.setAttribute("json", obj);
            path="controller_servl?event=MYFAMILY";
            //path="HomePage.jsp";
            mySession.setAttribute("tag", 1);
            //path= "/HomePage.jsp";
        }else{
            path= "/StartPage.jsp";
        }
        
        
        
    }
    
    
     public byte[] extractBytes(HttpServletRequest request) {
        try {
            String relativeWebPath = "/img/default.png";
            String defpath=request.getServletContext().getRealPath(relativeWebPath);
            // open image
            File imgPath = new File(defpath);
            BufferedImage bufferedImage = ImageIO.read(imgPath);
                
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            return baos.toByteArray();
          
        } catch (IOException ex) {
            
        }
        return null;
    }

    @Override
    protected String getURL() {
        return path;
    }
    


   

}
