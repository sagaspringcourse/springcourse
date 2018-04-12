CREATE PROCEDURE count_players (
  IN playerId INT,
  OUT playerCount INT
)
BEGIN
  SELECT COUNT(*) INTO playerCount
  FROM s_player
  WHERE s_player.id = playerId;
END;

CREATE PROCEDURE find_players ()
BEGIN
SELECT COUNT(*) INTO playerCount
FROM s_player
WHERE s_player.id = playerId;
END