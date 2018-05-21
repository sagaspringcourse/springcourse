package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.saga.dao.ITeamRepo;
import rs.saga.domain.Team;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
@Service
public class TeamService implements ITeamService {
    private ITeamRepo teamRepo;

    @Autowired
    public TeamService(ITeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    @Override
    public Team find(String name) {
        return teamRepo.findByName(name);
    }

    @Override
    public Team save(Team team) {
        return teamRepo.save(team);
    }


}
