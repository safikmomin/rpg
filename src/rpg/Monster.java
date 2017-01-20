package rpg;

public class Monster extends Actor{
	Monster(){
		super("Monster",100,'M',0,0);
		int x,y;
		do{
			x = (int)(Math.random()*9);
			y = (int)(Math.random()*9);
		}while(x == 4 && y == 4);
		setX(x);
		setY(y);
	}
	
	public int generateRandomAttack(){
		return (int)(Math.random()*10+10);
	}
}