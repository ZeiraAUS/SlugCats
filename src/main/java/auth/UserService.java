package auth;

public interface UserService {
    boolean register(String user, String email, String password, String confirmPassword);

    boolean login(String username, String password);

}

