package osrs.model.npc;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "players")
public class PlayerListWrapper {
	private List<Player> players;

	@XmlElement(name = "player")
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players){
		this.players = players;
	}
}
