import java.util.Random;

public class CommunityChest {
	private int cardNumber;
	private String description;
	private int randCard;
	
	public CommunityChest() {
		cardNumber = -1;
		description = "";
	}
	
	public CommunityChest(int num, String desc) {
		cardNumber = num;
		description = desc;
	}
	
	public void doAction(Player p) {
		Random rand = new Random();
		randCard = rand.nextInt(4) + 1;
		if(randCard == 1) {
			System.out.println("You are moving 2 spaces backwards, sorry");
			//move player 2 spaces backwards
			p.setCardOn(p.getCardOn() - 1);
		}
		else if(randCard ==2) {
			System.out.println("You are getting $75");
			p.addMoney(75);
		}
		else if(randCard ==3) {
			//you lose money
			System.out.println("You are losing $100");
			p.addMoney(-100);
		}
		else if(randCard == 4) {
			//lose 10% of your money
			System.out.println("You are losing %10 of your money");
			int money = p.getMoney();
			money = Math.round(money * 0.9f);
			p.setMoney(money);
		}
		else
		{
			//gain 10%
			System.out.println("You are going to gain %10 of your money");
			int money = p.getMoney();
			money = Math.round(money * 1.1f);
			p.setMoney(money);
		}
	}
}
