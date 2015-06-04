package edufarm.viewModel;

/**
 * Created by jazzbach on 15. 3. 29.
 */
public class UserResponse {
    private int id;
    private String displayName;
    private int role;

    public UserResponse(int id, String displayName, int role) {
        this.id = id;
        this.displayName = displayName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
