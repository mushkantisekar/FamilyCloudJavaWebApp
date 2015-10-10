
package dao.impl;

import dao.MediaObjectDao;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MediaObject;


public class MySqlMediaObjectDao implements MediaObjectDao {
    
    @Override
    public boolean insertUserMedia(MediaObject obj) {
        
        int validation = 0;
        PreparedStatement pst = null;
        Connection conn = MySqlDaoFactory.createConnection();
        
        try {
            
            pst = conn.prepareStatement("UPDATE user SET picture=? WHERE username=?");
            pst.setBinaryStream(1, new ByteArrayInputStream(obj.getContent()), obj.getContent().length);
            pst.setString(2, obj.getUsername());
            validation = pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MySqlMediaObjectDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (validation == 1) {
            return true;
        } else {
            return false;
        }
    }
    
}
