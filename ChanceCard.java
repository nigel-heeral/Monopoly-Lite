import java.util.*;

public class ChanceCard {
	private int cardNumber;
	private String description;
	private int randCard;
	
	public ChanceCard() {
		cardNumber = -1;
		description = "";
	}
	
	public ChanceCard(int num, String desc) {
		cardNumber = num;
		description = desc;
	}
	
	public void doAction(Player p) {
		Random rand = new Random();
		randCard = rand.nextInt(4) + 1;
		if(randCard == 1) {
			System.out.println("You are going to Go!");
			//move player to go
			p.setCardOn(1);
			//give 200 since landed on Go
			p.addMoney(200);
		}
		else if(randCard ==2) {
			System.out.println("You are getting $100");
			p.addMoney(100);
		}
		else if(randCard ==3) {
			System.out.println("You are going to lose $50");
			//you lose money
			p.addMoney(-50);
		}
		else if(randCard == 4) {
			//check to see if user passes go
			System.out.println("You are moving forward 10 spaces!");
			if((p.getCardOn() + 10) > 20 ) {
				p.setCardOn(p.getCardOn() + 10 - 20);
				p.addMoney(200);
			}
			else if ((p.getCardOn() + 10) == 20) {
				p.setCardOn(1);
				p.addMoney(200);	
			}
			else {
				p.setCardOn(p.getCardOn() + 10);
			}
			
		}
		else
		{
			System.out.println("You are getting $50");
			p.addMoney(50);
		}
	}
}
