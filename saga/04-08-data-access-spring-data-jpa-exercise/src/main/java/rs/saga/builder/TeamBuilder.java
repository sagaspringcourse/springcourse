package rs.saga.builder;

import rs.saga.businessobject.Team;

public class TeamBuilder {
    private Long id;
    private String name;

    public static TeamBuilder getInstance() {
        return new TeamBuilder();
    }


    public Team createTeam() {
        return new Team(id, name);
    }

    public TeamBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TeamBuilder setName(String name) {
        this.name = name;
        return this;
    }
}