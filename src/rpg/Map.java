//Safik Momin
//I did this by myself, however my group member kenny did contibuted some code

package rpg;

import java.util.Scanner;


public class Map {
	private char[][] mapPlot = new char[9][9];
	private Player p1;
	private Monster m1;
	
	public Map(){
		updateMap();
		p1 = new Player();
		m1 = new Monster();
		updateActorMap();
	}
	public void updateMap(){
		mapPlot = new char[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				mapPlot[i][j] = '-';
			}
		}
	}
	
	public void updateActorMap(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				mapPlot[i][j] = '-';
			}
		}
		mapPlot[p1.getY()][p1.getX()] = p1.getSymbol();
		mapPlot[m1.getY()][m1.getX()] = m1.getSymbol();
	}
	
	public void drawMap(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(mapPlot[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("up: w | down: s | left: a | right: d | quit: q");
	}
	
	public boolean checkEncounter(){
		return p1.getX() == m1.getX() && p1.getY() == m1.getY();
	}
	
	public String battle(){
		System.out.println(p1.getName() + " battle " + m1.getName() + "!");
		System.out.print(p1.getName() + " HP: " + p1.getHP() + " | ");
		System.out.println(m1.getName() + " HP: " + m1.getHP());

		int turn = 1;
		while(p1.getHP() > 0 && m1.getHP() > 0){
			if(turn > 0){
				int dmg = p1.generateRandomAttack();
				System.out.println(p1.getName() + " attacked with " + dmg);
				m1.setHP(m1.getHP()-dmg);
				System.out.println(m1.getName() + " HP: " + m1.getHP());
				turn *= -1;
			}else{
				int dmg = m1.generateRandomAttack();
				System.out.println(m1.getName() + " attacks with " + dmg);
				p1.setHP(p1.getHP()-dmg);
				System.out.println(p1.getName() + " HP: " + p1.getHP());
				turn *= -1;
			}
		}
		
		System.out.println("Combat is over!");
		if(p1.getHP() < 0){
			System.out.println(p1.getName() + " is defeated");
		}else{
			System.out.println(m1.getName() + " is defeated");
		}
		return "q";		
	}
	
	public boolean boundCheck(int i){
		return i >= 0 && i < 9; 
	}
	
	public boolean readCommand(String s){
		if(s.equals("w")){
			if(boundCheck(p1.getY()-1))
				p1.setY(p1.getY()-1);
		}else if(s.equals("s")){
			if(boundCheck(p1.getY()+1))
				p1.setY(p1.getY()+1);
		}else if(s.equals("a")){
			if(boundCheck(p1.getX()-1))
				p1.setX(p1.getX()-1);
		}else if(s.equals("d")){
			if(boundCheck(p1.getX()+1))
				p1.setX(p1.getX()+1);
		}else if(s.equals("q")){
			System.out.println("Game Quit");
			return false;
		}else{
			System.out.println("Invalid Input!");
			return false;
		}
		updateActorMap();
		return true;
	}
	
	public void printAll(){
		Scanner scan = new Scanner(System.in);
		String input;
		drawMap();
		do{
			System.out.print("move: ");
			input = scan.nextLine();
			String[] line = input.split(" ");
			input = line[0];
			if(readCommand(input)){
				if(checkEncounter())
					input = battle();
				else{
					drawMap();
				}
			}
		}while(!input.equals("q"));
		scan.close();
	}
	
	public static void main(String[] args){
		Map map = new Map();
		map.printAll();
	}
}