package ecommerce.com.login;

import java.io.File;

public class LoginTest {
    public static void main(String[] args) {
        File f = new File("usersInfo.txt");

        // Test 1: Valid registration
        boolean added = LoginUtils.saveLoginInfo("hirbod", "password123");
        System.out.println("Test 1 - New user added: " + added);

        // Test 2: Duplicate user
        boolean duplicate = LoginUtils.saveLoginInfo("hirbod", "password123");
        System.out.println("Test 2 - Duplicate user rejected: " + !duplicate);

        // Test 3: Weak password
        boolean weak = LoginUtils.saveLoginInfo("newUser", "123");
        System.out.println("Test 3 - Weak password rejected: " + !weak);

        // Test 4: Another valid new user
        boolean added2 = LoginUtils.saveLoginInfo("sara", "securePass99");
        System.out.println("Test 4 - Second new user added: " + added2);

    }
}
