
package events;

import dao.DaoFactory;
import dao.ShoppingListDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import modelBO.ShoppingListBO;


public class ShopListSorted extends EventHandlerBase{

    String path;
    
    
    public static final String devicemobile="Mobile";
    
    
    @Override
    protected String getURL() {
        return path;
    }
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        
        ShoppingListDao myItemDAO = mySqlFactory.getShoppingListDao();
        
        UserDao myUserDAO = mySqlFactory.getUserDao();
        
        ShoppingListBO itemBO= new ShoppingListBO();
        List<ShoppingListBO> SortedfamSHOPList= new ArrayList<ShoppingListBO>();
        
         User cur_user = (User) mySession.getAttribute("curr_user");
        User director = myUserDAO.getFamilyDirector(cur_user.getUsername());
        
        String keysort=request.getParameter("sortedTag");
        
        String asc_desc_tag=request.getParameter("ssa");
        
        String keysort2="status";        
        
        
        
       SortedfamSHOPList= itemBO.family_toShoppingListBO(myItemDAO.getSortedFamilyShoppingList(director.getUsername(), keysort, asc_desc_tag));
        
        
        
               
        
        request.setAttribute("SortedfamSHOP", SortedfamSHOPList);
        request.setAttribute("cuRRENTuserDr", cur_user);
        
        
        String rh =request.getHeader("user-agent");
        
        
        
        if(detectmobile(rh)){
        
        path="ShopListSortedMob.jsp";  
        
        
        }else{
        
        
            path="ShopListSorted.jsp"; 
        }
        
                      
        
        
        
    }
    
    
    
    
     public boolean detectmobile(String rh){
    
        if(rh.indexOf(devicemobile)!=-1){
        
            return true;
        }
        
            return false;
        
    
    }
    
    
    
}
