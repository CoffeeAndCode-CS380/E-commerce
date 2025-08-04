package ecommerce.com.cart;
/**
 * Represents a shopper with a personal cart.
 */
public class User {
    private final String username;      // user's login name
    private final CartUtils cart = new CartUtils(); // user's shopping cart

    /**
     * Constructs a User with the given username.
     * @param username login name
     */
    public User(String username) { // constructor
        this.username = username;   // assign username
    }

    /**
     * @return user's login name
     */
    public String getUsername() { // getter for username
        return username;         // return username value
    }

    /**
     * @return user's shopping cart
     */
    public CartUtils getCart() {    // getter for cart
        return cart;           // return cart instance
    }
}