package rs.saga.dto;

import rs.saga.domain.Player;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
public class PlayerDTO {

    private Long id;
    private String name;
    private String displayName;

    public PlayerDTO() {
    }

    public static PlayerDTO convertToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getFirstName());
        playerDTO.setDisplayName(upperCaseFirstLetter(player.getFirstName()) + upperCaseFirstLetter(player.getLastName()));
        return playerDTO;
    }

    private static String upperCaseFirstLetter(String string) {
        return String.valueOf(string.charAt(0)).toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
