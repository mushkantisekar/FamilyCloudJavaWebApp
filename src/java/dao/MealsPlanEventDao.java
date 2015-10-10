
package dao;

import java.util.List;
import model.MealsPlanEvent;


public interface MealsPlanEventDao {
    
     public boolean insertMealsPlanEvent(MealsPlanEvent event);
    
    public boolean updateMealsPlanEvent(MealsPlanEvent event);
    
    public boolean deleteMealsPlanEvent(int id);
    
    public List<MealsPlanEvent> getMealsPlanEvents(String username);
    
    public MealsPlanEvent getMealsCalEvent(int eventID);

    public List<MealsPlanEvent> getMealsPlanEvents(String username, String type);
    
    public int getLastInsertedID();
    
}
