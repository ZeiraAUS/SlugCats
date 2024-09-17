package auth;

public interface UserService {
   boolean register(String userName, String password, String confirmPassword,String FirstName, String LastName, String email);

    boolean login(String username, String password);

}

