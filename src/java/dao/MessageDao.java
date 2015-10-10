
package dao;

import java.util.List;
import model.Message;


public interface MessageDao {
    
    public boolean addMessage(Message msg);
    
    public List<Message> getMessages(String sender, String receiver);
    
    public Message getMessage(int messageid);
    
}
