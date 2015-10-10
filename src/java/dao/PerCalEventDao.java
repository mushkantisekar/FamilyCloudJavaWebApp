
package dao;

import java.util.List;
import model.PerCalEvent;


public interface PerCalEventDao {
    
    public boolean insertPerCalEvent(PerCalEvent event);
    
    
    public List<PerCalEvent> getPerCalEvents(String username);
    
    
    public PerCalEvent getPerCalEvent(int eventID);
    
    public boolean deletePerCalEvent(int eventID);
    
    public boolean updatePerCalEvent(PerCalEvent event);
    
    
    public List<PerCalEvent> getPerCalEventsByCateg(String username,String cat);
    
    public int getLastInsertedID();
    
}
