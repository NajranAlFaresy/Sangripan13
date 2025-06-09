package auth;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private Map<String, String> users = new HashMap<>();

    public LoginService() {
        // Data dummy user: username -> password
        users.put("admin", "admin123");
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

}
