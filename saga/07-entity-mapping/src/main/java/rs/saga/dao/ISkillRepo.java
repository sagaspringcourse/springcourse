package rs.saga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.saga.domain.Skill;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
public interface ISkillRepo extends JpaRepository<Skill, Long> {
}
