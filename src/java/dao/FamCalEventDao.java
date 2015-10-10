
package dao;

import java.util.List;
import model.FamCalEvent;


public interface FamCalEventDao {
    
    public boolean insertFamCalEvent(FamCalEvent event);
    
    public boolean updateFamCalEvent(FamCalEvent event);
    
    public boolean deleteFamCalEvent(int id);
    
    public List<FamCalEvent> getFamCalEvents(String username);
    
    public FamCalEvent getFamCalEvent(int eventID);
    
    public List<FamCalEvent> getFamCalEventsByUser(String username);

    public List<FamCalEvent> getFamCalEvents(String category, String username);
    
    public int getLastInsertedID();
    
}
