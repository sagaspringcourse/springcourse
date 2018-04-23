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
    public void deleteById(Long id) {
        playerRepo.delete(id);
    }

    //@PostFilter("hasRole('ROLE_ADMIN') or principal.id == filterObject.id")
    @Override
    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    @Override
    public Player findById(Long id) {
        return playerRepo.findOne(id);
    }

    public Player findByUsername(String userName) {
        return playerRepo.findByCredentials_Username(userName);
    }

    @Override
    public List<String> findPlayerRoles(String userName) {
        return playerRepo.findPlayerRoles(userName);
    }
}
