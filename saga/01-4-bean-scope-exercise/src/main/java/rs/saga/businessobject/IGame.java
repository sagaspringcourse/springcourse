package rs.saga.businessobject;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-11
 */
public interface IGame {
    Referee getReferee();

    void play();
}
