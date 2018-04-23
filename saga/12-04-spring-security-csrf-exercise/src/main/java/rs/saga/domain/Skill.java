package rs.saga.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@Table(name = "s_skill")
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE")
    private String type;
    @Column(name = "VALUE")
    private Integer value;
    @ManyToMany(mappedBy = "skills")
    private List<Player> players = new ArrayList<>();

    public Skill(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Skill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
