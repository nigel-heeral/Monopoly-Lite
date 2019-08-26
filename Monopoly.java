import java.util.*;
import java.io.*;
public class Monopoly {
//there is a bug i cannot figure out where it doesn't go through a players turn fully

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Player> playersList= new ArrayList<Player>();
		Player ignore = new Player ();
		//used to align index number to player number
		playersList.add(ignore);
		ArrayList<PropertyCard> cardsList = new ArrayList<PropertyCard>();
		PropertyCard temp = new PropertyCard();
		//used to align index number to card number so cards start at 1 to 20
		cardsList.add(temp);
		Scanner scan = new Scanner(System.in);
		String playerName = "";
		String playerPiece = "";
		String cardName = "";
		int cardPrice = 0;
		int cardRent = 0;
		//created Players and adding to arrayList
		for(int i = 1; i <= 4; i++) {
			System.out.println("Please enter player " + i + "'s name");
			playerName = scan.nextLine();
			Player tempi = new Player(playerName, i, "temp", 1000);
			playersList.add(tempi);
		}
		//opens a file and scans in the property cards
		
		try {
			File card = new File("cards.txt");
			Scanner fileScan = new Scanner(card);
			while(fileScan.hasNext()) {
				cardName = fileScan.nextLine();
				cardPrice = fileScan.nextInt();
				fileScan.nextLine();
				cardRent = fileScan.nextInt();
				fileScan.nextLine();
				PropertyCard temp1 = new PropertyCard(cardName, cardPrice, cardRent, false, -1);
				cardsList.add(temp1);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Please make sure cards.txt is in the same folder");
			System.exit(0);
		}	
		
		//FSM of how monopoly works is represented by switch case statements
		int playerTurn = 1;
		int count = 4;
		int diceRoll = -1;
		String userChoice = "";
		long startTime = System.currentTimeMillis();
		while(count > 1) {
			Player currentPlayer;
			switch(playerTurn) {
			
			case 1: 
				if(playersList.get(1).getPlayingStatus()) {				
					currentPlayer = playersList.get(playerTurn);
					System.out.println("It is " + currentPlayer.getPlayerName() + "'s turn" );
					System.out.println("You have $" + currentPlayer.getMoney());
					System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
					
					//prints out the player's cards if they have any
					if(currentPlayer.playersCard.size() > 0) {
						System.out.println("You own " + currentPlayer.playersCard.size() + " properties");
						System.out.println(currentPlayer.getPlayersCard());
					}
					System.out.println("Time to roll the die!");
					diceRoll = Dice.diceRoll();
					System.out.println("You rolled a " + diceRoll);
					//dice roll is over and need to do turn, can prolly write entire case in a function 
					if (currentPlayer.getCardOn() + diceRoll == 21) {
						System.out.println("Congratulations you are on go\nYou get $200");
						currentPlayer.addMoney(200);
					}
					else {
						//user didn't land on go, so they landed on a regular card.
						if(currentPlayer.getCardOn() + diceRoll > 20) {
							System.out.println("Congrats, you passed go. You get $200");
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll - 20);
							currentPlayer.addMoney(200);
						}
						//if statement used to give them money if they passed go, and if not then just add regularly
						else {
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll);
							
						}
						System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
						if(cardsList.get(currentPlayer.getCardOn()).isOwned() == false) {
							System.out.println(" The property costs $" + cardsList.get(currentPlayer.getCardOn()).getCost());
							System.out.println("Would you like to buy the property? Yes or No");
							userChoice = scan.nextLine();
							char choice = userChoice.charAt(0);
							if(choice == 'Y' | choice == 'y') {
								//set player ownership of card
								cardsList.get(currentPlayer.getCardOn()).setNameOwnedBy(currentPlayer.getPlayerName());
								cardsList.get(currentPlayer.getCardOn()).setOwnedBy(currentPlayer.getPlayerID());
								cardsList.get(currentPlayer.getCardOn()).setIsOwned(true);
								//need to add card to players list 
								currentPlayer.playersCard.add(cardsList.get(currentPlayer.getCardOn()));
								//update player's money
								currentPlayer.setMoney(currentPlayer.getMoney() - cardsList.get(currentPlayer.getCardOn()).getCost());;
								
							}
						}
						else {
							//someone owns the card so pay rent
							int costCard = cardsList.get(currentPlayer.getCardOn()).getRent();
							System.out.println( cardsList.get(currentPlayer.getCardOn()).getOwnedBy() + " owns the property. You are going to pay " + " $" + costCard );
							System.out.println("Enter any key to comfirm");
							scan.nextLine();
							int idOfOwner = cardsList.get(currentPlayer.getCardOn()).ownedBy();
							if(playersList.get(currentPlayer.getPlayerID()).getMoney() <= costCard) {
								System.out.println("Sorry but you will lose all your money since your funds aren't greater than rent.");
								playersList.get(idOfOwner).addMoney(playersList.get(currentPlayer.getPlayerID()).getMoney());
								playersList.get(currentPlayer.getPlayerID()).setMoney(0);
								currentPlayer.setPlayingStatus(false);
								
							}
							else {
								playersList.get(idOfOwner).addMoney(costCard);
								playersList.get(currentPlayer.getPlayerID()).addMoney((-1 * costCard));
								System.out.println("Your new balance is: " + currentPlayer.getMoney() );
							
							}
						}
					}
					
					if(currentPlayer.getMoney() <= 0) {
						currentPlayer.setPlayingStatus(false);
						System.out.println("You dont have money left, you are out of the game");
					}
					
				}
				System.out.println("-----------------------------------------------------");				
				playerTurn = 2;
				break;
				//cases just repeat
			case 2:
				if(playersList.get(2).getPlayingStatus()) {				
					currentPlayer = playersList.get(playerTurn);
					System.out.println("It is " + currentPlayer.getPlayerName() + "'s turn" );
					System.out.println("You have $" + currentPlayer.getMoney());
					System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
					System.out.println("You own " + currentPlayer.playersCard.size() + " properties");
					if(currentPlayer.playersCard.size() > 0)
						System.out.println(currentPlayer.getPlayersCard());
					
					System.out.println("Time to roll the die!");
					diceRoll = Dice.diceRoll();
					System.out.println("You rolled a " + diceRoll);
					//dice roll is over and need to do turn, can prolly write entire case in a function 
					if (currentPlayer.getCardOn() + diceRoll == 21) {
						System.out.println("Congratulations you are on go\nYou get $200");
						currentPlayer.addMoney(200);
					}
					else {
						//user didn't land on go, so they landed on a regular card.
						if(currentPlayer.getCardOn() + diceRoll > 20) {
							System.out.println("Congrats, you passed go. You get $200");
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll - 20);
							currentPlayer.addMoney(200);
						}
						//if statement used to give them money if they passed go, and if not then just add regularly
						else {
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll);
							
						}
						System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
						if(cardsList.get(currentPlayer.getCardOn()).isOwned() == false) {
							System.out.println(" The property costs $" + cardsList.get(currentPlayer.getCardOn()).getCost());
							System.out.println("Would you like to buy the property? Yes or No");
							userChoice = scan.nextLine();
							char choice = userChoice.charAt(0);
							if(choice == 'Y' | choice == 'y') {
								//set player ownership of card
								cardsList.get(currentPlayer.getCardOn()).setNameOwnedBy(currentPlayer.getPlayerName());
								cardsList.get(currentPlayer.getCardOn()).setOwnedBy(currentPlayer.getPlayerID());
								cardsList.get(currentPlayer.getCardOn()).setIsOwned(true);
								//need to add card to players list 
								currentPlayer.playersCard.add(cardsList.get(currentPlayer.getCardOn()));
								//update player's money
								currentPlayer.setMoney(currentPlayer.getMoney() - cardsList.get(currentPlayer.getCardOn()).getCost());;
								
							}
						}
						else {
							int costCard = cardsList.get(currentPlayer.getCardOn()).getRent();
							System.out.println( cardsList.get(currentPlayer.getCardOn()).getOwnedBy() + " owns the property. You are going to pay " + " $" + costCard );
							System.out.println("Enter any key to comfirm");
							scan.nextLine();
							int idOfOwner = cardsList.get(currentPlayer.getCardOn()).ownedBy();
							if(playersList.get(currentPlayer.getPlayerID()).getMoney() <= costCard) {
								System.out.println("Sorry but you will lose all your money since your funds aren't greater than rent.");
								playersList.get(idOfOwner).addMoney(playersList.get(currentPlayer.getPlayerID()).getMoney());
								playersList.get(currentPlayer.getPlayerID()).setMoney(0);
								currentPlayer.setPlayingStatus(false);
								
							}
							else {
								playersList.get(idOfOwner).addMoney(costCard);
								playersList.get(currentPlayer.getPlayerID()).addMoney((-1 * costCard));
								System.out.println("Your new balance is: " + currentPlayer.getMoney() );
							
							}
						}
					}
					
					if(currentPlayer.getMoney() <= 0) {
						currentPlayer.setPlayingStatus(false);
						System.out.println("You dont have money left, you are out of the game");
					}
					
				}
				System.out.println("-----------------------------------------------------");
				playerTurn = 3;
				break;
			case 3:
				if(playersList.get(3).getPlayingStatus()) {				
					currentPlayer = playersList.get(playerTurn);
					System.out.println("It is " + currentPlayer.getPlayerName() + "'s turn" );
					System.out.println("You have $" + currentPlayer.getMoney());
					System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
					System.out.println("You own " + currentPlayer.playersCard.size() + " properties");
					if(currentPlayer.playersCard.size() > 0)
						System.out.println(currentPlayer.getPlayersCard());
					
					System.out.println("Time to roll the die!");
					diceRoll = Dice.diceRoll();
					System.out.println("You rolled a " + diceRoll);
					//dice roll is over and need to do turn, can prolly write entire case in a function 
					if (currentPlayer.getCardOn() + diceRoll == 21) {
						System.out.println("Congratulations you are on go\nYou get $200");
						currentPlayer.addMoney(200);
					}
					else {
						//user didn't land on go, so they landed on a regular card.
						if(currentPlayer.getCardOn() + diceRoll > 20) {
							System.out.println("Congrats, you passed go. You get $200");
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll - 20);
							currentPlayer.addMoney(200);
						}
						//if statement used to give them money if they passed go, and if not then just add regularly
						else {
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll);
							
						}
						System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
						if(cardsList.get(currentPlayer.getCardOn()).isOwned() == false) {
							System.out.println(" The property costs $" + cardsList.get(currentPlayer.getCardOn()).getCost());
							System.out.println("Would you like to buy the property? Yes or No");
							userChoice = scan.nextLine();
							char choice = userChoice.charAt(0);
							if(choice == 'Y' | choice == 'y') {
								//set player ownership of card
								cardsList.get(currentPlayer.getCardOn()).setNameOwnedBy(currentPlayer.getPlayerName());
								cardsList.get(currentPlayer.getCardOn()).setOwnedBy(currentPlayer.getPlayerID());
								cardsList.get(currentPlayer.getCardOn()).setIsOwned(true);
								//need to add card to players list 
								currentPlayer.playersCard.add(cardsList.get(currentPlayer.getCardOn()));
								//update player's money
								currentPlayer.setMoney(currentPlayer.getMoney() - cardsList.get(currentPlayer.getCardOn()).getCost());;
								
							}
						}
						else {
							int costCard = cardsList.get(currentPlayer.getCardOn()).getRent();
							System.out.println( cardsList.get(currentPlayer.getCardOn()).getOwnedBy() + " owns the property. You are going to pay " + " $" + costCard );
							System.out.println("Enter any key to comfirm");
							scan.nextLine();
							int idOfOwner = cardsList.get(currentPlayer.getCardOn()).ownedBy();
							if(playersList.get(currentPlayer.getPlayerID()).getMoney() <= costCard) {
								System.out.println("Sorry but you will lose all your money since your funds aren't greater than rent.");
								playersList.get(idOfOwner).addMoney(playersList.get(currentPlayer.getPlayerID()).getMoney());
								playersList.get(currentPlayer.getPlayerID()).setMoney(0);
								currentPlayer.setPlayingStatus(false);
								
							}else {
								playersList.get(idOfOwner).addMoney(costCard);
								playersList.get(currentPlayer.getPlayerID()).addMoney((-1 * costCard));
								System.out.println("Your new balance is: " + currentPlayer.getMoney() );
							
							}
						}
					}
					
					if(currentPlayer.getMoney() <= 0) {
						currentPlayer.setPlayingStatus(false);
						System.out.println("You dont have money left, you are out of the game");
					}
					
				}
				System.out.println("-----------------------------------------------------");
				playerTurn = 4;
				break;
			case 4:
				if(playersList.get(4).getPlayingStatus()) {				
					currentPlayer = playersList.get(playerTurn);
					System.out.println("It is " + currentPlayer.getPlayerName() + "'s turn" );
					System.out.println("You have $" + currentPlayer.getMoney());
					System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
					System.out.println("You own " + currentPlayer.playersCard.size() + " properties");
					if(currentPlayer.playersCard.size() > 0)
						System.out.println(currentPlayer.getPlayersCard());
					
					System.out.println("Time to roll the die!");
					diceRoll = Dice.diceRoll();
					System.out.println("You rolled a " + diceRoll);
					//dice roll is over and need to do turn, can prolly write entire case in a function 
					if (currentPlayer.getCardOn() + diceRoll == 21) {
						System.out.println("Congratulations you are on go\nYou get $200");
						currentPlayer.addMoney(200);
					}
					else {
						//user didn't land on go, so they landed on a regular card.
						if(currentPlayer.getCardOn() + diceRoll > 20) {
							System.out.println("Congrats, you passed go. You get $200");
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll - 20);
							currentPlayer.addMoney(200);
						}
						//if statement used to give them money if they passed go, and if not then just add regularly
						else {
							currentPlayer.setCardOn(currentPlayer.getCardOn() + diceRoll);
							
						}
						System.out.println("You are on " + cardsList.get(currentPlayer.getCardOn()).getName());
						if(cardsList.get(currentPlayer.getCardOn()).isOwned() == false) {
							System.out.println(" The property costs $" + cardsList.get(currentPlayer.getCardOn()).getCost());
							System.out.println("Would you like to buy the property? Yes or No");
							userChoice = scan.nextLine();
							char choice = userChoice.charAt(0);
							if(choice == 'Y' | choice == 'y') {
								//set player ownership of card
								cardsList.get(currentPlayer.getCardOn()).setNameOwnedBy(currentPlayer.getPlayerName());
								cardsList.get(currentPlayer.getCardOn()).setOwnedBy(currentPlayer.getPlayerID());
								cardsList.get(currentPlayer.getCardOn()).setIsOwned(true);
								//need to add card to players list 
								currentPlayer.playersCard.add(cardsList.get(currentPlayer.getCardOn()));
								//update player's money
								currentPlayer.setMoney(currentPlayer.getMoney() - cardsList.get(currentPlayer.getCardOn()).getCost());;
								
							}
						}
						else {
							int costCard = cardsList.get(currentPlayer.getCardOn()).getRent();
							System.out.println( cardsList.get(currentPlayer.getCardOn()).getOwnedBy() + " owns the property. You are going to pay " + " $" + costCard );
							System.out.println("Enter any key to comfirm");
							scan.nextLine();
							int idOfOwner = cardsList.get(currentPlayer.getCardOn()).ownedBy();
							if(playersList.get(currentPlayer.getPlayerID()).getMoney() <= costCard) {
								System.out.println("Sorry but you will lose all your money since your funds aren't greater than rent.");
								playersList.get(idOfOwner).addMoney(playersList.get(currentPlayer.getPlayerID()).getMoney());
								playersList.get(currentPlayer.getPlayerID()).setMoney(0);
								currentPlayer.setPlayingStatus(false);
								
							}
							else {
							playersList.get(idOfOwner).addMoney(costCard);
							playersList.get(currentPlayer.getPlayerID()).addMoney((-1 * costCard));
							System.out.println("Your new balance is: " + currentPlayer.getMoney() );
						
							}
						}
					}					
					
					if(currentPlayer.getMoney() <= 0) {
						currentPlayer.setPlayingStatus(false);
						System.out.println("You dont have money left, you are out of the game");
					}
					playerTurn = 1;
					System.out.println("-----------------------------------------------------");
					break;
				}
				
				
				
				
			}//switch
			
			//check to see if game is over by seeing if there's one player remaining
			int num = 0;
			for(int i = 1; i < playersList.size(); i++) {
				if(playersList.get(i).getPlayingStatus() == true) {
					num++;
				}
			}
			count = num;
		
			//need to implement chance and community chest cards
			//if chance, else if comm, else { if owned == false else pay up}
		}//while loop
		
		for(int i = 0; i < playersList.size(); i++) {
			if(playersList.get(i).getPlayingStatus())
			System.out.println("The winner is: " + playersList.get(i).getPlayerName());
		}
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = elapsedTime / 100.0;
	    System.out.println(time);
		scan.close();
	}
	
	//helper methods
	public static void printPlayers(ArrayList<Player> p) {
		for(Player temp : p) {
			System.out.println(temp.toString());
		}
	}
	public static void printCards(ArrayList<PropertyCard> c) {
		for(PropertyCard temp : c) {
			System.out.println(c.toString());
		}
	}

}
