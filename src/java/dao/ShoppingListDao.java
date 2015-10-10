
package dao;

import java.util.List;
import model.ShoppingListItem;


public interface ShoppingListDao {
    
    
    public boolean insertShoppingItem(ShoppingListItem shl);
    
    
    
    public List<ShoppingListItem> getFamilyShoppingList(String username);
    
    
    public ShoppingListItem getshopItem(int item);
    
    
   

    public boolean updateItem(ShoppingListItem sli);
    
    
    public boolean deleteItem(int item);
    
    public List<ShoppingListItem> getSortedFamilyShoppingList(String username, String sortvalue, String asc_desc_tag);
    
  
    
}
