package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.dao.IPlayerRepo;
import rs.saga.domain.Player;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-16
 */
@Service
@Transactional
public class PlayerService implements IPlayerService {

    private IPlayerRepo playerRepo;


    @Autowired
    public PlayerService(IPlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    @Override
    public Player findPlayer(Long id) {
        return playerRepo.findOne(id);
    }
}
