
package dao;

import java.util.List;
import model.WallPost;


public interface WallPostDao {
    
    
    public boolean insertPost(WallPost post);

    
        
    public boolean deletePost(int idWallPost);

    public boolean updatePost(WallPost post, int idWallPost);

    public WallPost getPost(int idWallPost);
    
    public List<WallPost> getFamilyPosts(String username);
    
}
