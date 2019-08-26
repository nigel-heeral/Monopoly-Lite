import java.util.*;
//class is used to do a dice roll
public class Dice {

		public static int diceRoll() {
			int roll;
			Random rand = new Random();
			roll = rand.nextInt(12) + 1;
			return roll;
		}
}
