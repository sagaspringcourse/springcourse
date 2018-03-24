package rs.saga.domain.basic;


public class PlayerBuilder {
    private Long id;
    private String firstName;
    private String username;
    private String lastName;
    private String password;
    private String address;
    private String email;

    public Player createPlayer() {
        return new Player(id, firstName, username, lastName, password, address, email);
    }

    public PlayerBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public PlayerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PlayerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PlayerBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PlayerBuilder setUsername(String username) {
        this.username = username;
        return this;
    }
}