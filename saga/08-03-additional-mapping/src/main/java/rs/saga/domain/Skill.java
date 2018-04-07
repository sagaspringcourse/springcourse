package rs.saga.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    public enum SKILL_TYPE {
        OFFENSE, DEFENSE
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private SKILL_TYPE type;

    public Skill(SKILL_TYPE type, Integer value) {
        this.type = type;
        this.value = value;
    }

    @Column(name = "VALUE")
    private Integer value;

    @ManyToMany(mappedBy = "skills")
    private List<Player> players = new ArrayList<>();

    public Skill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SKILL_TYPE getType() {
        return type;
    }

    public void setType(SKILL_TYPE type) {
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
