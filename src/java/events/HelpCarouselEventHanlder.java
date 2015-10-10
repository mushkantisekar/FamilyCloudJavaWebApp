
package events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HelpCarouselEventHanlder extends EventHandlerBase{

    String path;
    @Override
    protected String getURL() {
        
        return path;
         }
    
    
    
    
    
    public void process(HttpSession mySession, HttpServletRequest request, HttpServletResponse response) {
        
        
        
        String tag=request.getParameter("tag");
        
        
        
        if(tag.equals("myf")){
        
        
            request.setAttribute("pic1","img/family.png");
            request.setAttribute("pic2","img/addfamily.png");
            request.setAttribute("pic3","img/editfamily.png");
            request.setAttribute("pic4","img/viewfamily.png");
            
        
        }else if(tag.equals("fwall")){
        
        
          request.setAttribute("pic1","img/wall.PNG");
            request.setAttribute("pic2","img/addpost.PNG");
            request.setAttribute("pic3","img/wall.PNG");
            request.setAttribute("pic4","img/addpost.PNG");
        
        
        }else if(tag.equals("famcal")){
        
        
            request.setAttribute("pic1","img/famcal1.PNG");
            request.setAttribute("pic2","img/famcal2.PNG");
            request.setAttribute("pic3","img/famcal3.PNG");
            request.setAttribute("pic4","img/famcal4.PNG");
        
        }else if(tag.equals("percal")){
             request.setAttribute("pic1","img/percal1.PNG");
            request.setAttribute("pic2","img/percal2.PNG");
            request.setAttribute("pic3","img/percal3.PNG");
            request.setAttribute("pic4","img/percal4.PNG");
        }else if(tag.equals("mealcal")){
        
        
           request.setAttribute("pic1","img/meal1.PNG");
            request.setAttribute("pic2","img/meal2.PNG");
            request.setAttribute("pic3","img/meal3.PNG");
            request.setAttribute("pic4","img/meal4.PNG");
        
        }else if(tag.equals("shop")){
        
        
         request.setAttribute("pic1","img/shop1.PNG");
            request.setAttribute("pic2","img/shop2.PNG");
            request.setAttribute("pic3","img/shop3.PNG");
            request.setAttribute("pic4","img/shop4.PNG");
        }else if(tag.equals("todo")){
        
             request.setAttribute("pic1","img/todo1.PNG");
            request.setAttribute("pic2","img/todo2.PNG");
            request.setAttribute("pic3","img/todo3.PNG");
            request.setAttribute("pic4","img/todo4.PNG");
        
        }
        
        
        
        path="HelpSlider.jsp";
        
        
        
    }
    
}
