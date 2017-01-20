package rpg;


public class Player extends Actor{
	Player(){
		super("Player",100,'P',4,4);
	}
	
	public int generateRandomAttack(){
		return (int)(Math.random()*20+10);
	}
}