package rs.saga.builder;


import rs.saga.domain.Player;

public class PlayerBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;

    public Player createPlayer() {
        return new Player(id, firstName, lastName, address, email);
    }

    public static PlayerBuilder getInstance() {
        return new PlayerBuilder();
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

    public PlayerBuilder nino() {
        return setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs");
    }

    public PlayerBuilder slave() {
        return setFirstName("Slavisa").setLastName("Avramoviuc").setEmail("slavisa.a@saga.rs");
    }
}