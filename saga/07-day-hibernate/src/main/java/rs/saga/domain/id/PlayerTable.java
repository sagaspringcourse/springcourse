package rs.saga.domain.id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-01
 */
@Entity
@Table(name = "s_player_tab")
public class PlayerTable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "player_tab")
    @TableGenerator(name = "player_tab", table = "PLAYER_TABLE", pkColumnName = "PK_NAME", pkColumnValue = "PK_VALUE")
    private Long id;

    // field access
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ADDRESS")
    private String address;

    // throws hibernate exception if null upon save, generates a NOT_NULL constraint
    @Column(name = "EMAIL", nullable = false)
    private String email;

    public PlayerTable(Long id, String firstName, String username, String lastName, String password, String address, String email) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.email = email;
    }

    public PlayerTable() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
