
package dao;

import model.ToDoListItem;
import java.util.List;


public interface ToDoListItemDao {
    
    public boolean insertItem(ToDoListItem item);
    
    public boolean deleteItem(int itemID);
    
    public boolean updateItem(ToDoListItem item);
    
    public ToDoListItem getItem(int itemID);
    
    public List<ToDoListItem> getFamilyToDoList(String username);
        
    public List<ToDoListItem> getSortedFamilyToDoList(String username, String sortvalue,String asc_desc_tag);
}
