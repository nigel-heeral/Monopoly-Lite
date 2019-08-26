import java.util.ArrayList;

public class Player {

	private String playerName;
	private int playerID;
	private String playerPiece;
	private int money;
	private int cardOn;
	private boolean isPlaying;
	public ArrayList<PropertyCard> playersCard = new ArrayList<PropertyCard>();
	
	public Player() {
		playerName = "";
		playerPiece = "";
		cardOn = -1;
		money = -1;
		isPlaying = false;		
	}
	
	public Player(String name, int id, String piece, int money) {
		playerName = name;
		playerID = id;
		playerPiece = piece;
		this.money = money;
		cardOn = 1;
		isPlaying = true;
	}
	public String getPlayerName() {
		return playerName;
	}
	public int getPlayerID() {
		return playerID;
	}
	public String getPlayerPiece() {
		return playerPiece;
	}
	public int getMoney() {
		return money;
	}
	public int getCardOn() {
		return cardOn;
	}
	public boolean getPlayingStatus() {
		return isPlaying;
	}
	public void setMoney(int m) {
		this.money = m;
	}
	public void setPlayingStatus(boolean b) {
		this.isPlaying = b;
	}
	public void setCardOn(int n) {
		cardOn = n;
	}
	public void subtractMoney(int m) {
		this.money -= m;
	}
	public void addMoney(int m) {
		this.money += m;
	}
	public String getPlayersCard() {
		String temp = "";
		if(!playersCard.isEmpty()){
			for(PropertyCard p : playersCard)
				temp += p.getName() + "\n";
		}
		return temp;
	}
	public String toString() {
		String temp = "Player name: " + playerName + "\nPlayerID: " + playerID + "\nPlayer Piece: " + playerPiece + "\nMoney: " + money + "\nIs Playing: " + isPlaying + "\nCard on: " + cardOn + "\nCards they own: ";
		if(!playersCard.isEmpty()){
			for(PropertyCard p : playersCard)
				temp += p.getName() + " ";
		}
		temp += "\n";
		return temp;
		
	}
}
