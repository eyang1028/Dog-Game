import java.lang.Math;
import java.util.Scanner;

class Dog { //dog class; this class does not contain any System.out.println() instructions, in case I decide to modify this program to display a GUI
	String name;
	boolean alive, healthy, happy, hungry, clean;
	int age;

	public Dog(String n) { //constructor
		name = n;
		alive = true;
		healthy = true;
		happy = true;
		hungry = false;
		clean = true;
		age = 0;
	}

	public boolean getAlive() { //alive accessor method
		return alive;
	}

	public boolean getHealthy() { //health accessor method
		return healthy;
	}

	public boolean getHappy() { //happy accessor method
		return happy;
	}

	public boolean getHungry() { //hungry accessor method
		return hungry;
	}

	public boolean getClean() { //clean accessor method
		return clean;
	}

	public int getAge() { //age accessor method
		return age;
	}

	public String vet() { //take dog to vet
		if(healthy)
			return name + " does not need to go to the vet.";
		else {
			healthy = true;
			return name + " now feels better.";
		}
	}

	public String play() { //play with dog
		happy = true;
		int x = (int)(100 * Math.random());
		if(x <= 25) {
			clean = false;
			return name + " played, but is now filthy!";
		} else if( x >= 40 && x <= 60) {
			healthy = false;
			happy = false;
			return name + " played, but got injured!";
		} else if(x >= 75) {
			hungry = true;
			return name + " played, but is hungry now!";
		} else
			return name + " played, and had a lot of fun!";
	}

	private boolean yummy(String f) { //yummy food test
		if(f.equals("bacon") || f.equals("apples") || f.equals("peanut butter"))
			return true;
		return false;
	}

	private boolean poisonous(String f) { //poisonous food test
		if(f.equals("chocolate") || f.equals("grapes"))
			return true;
		return false;
	}

	public String feed(String food) { //feed the dog
		if(!hungry)
			return name + " does not want to eat.";
		else if(yummy(food)) {
			hungry = false;
			happy = true;
			return name + " happily ate the " + food + "!";	
		} else if(poisonous(food)) {
			hungry = false;
			healthy = false;
			happy = false;
			return name + " ate the " + food + ", but is now sick!";
		} else
			hungry = false;
			return name + " ate the " + food + " and is not hungry anymore.";
	}

	public String bathe() { //bathe the dog
		if(clean)
			return name + " does not need a bath.";
		else {
			clean = true;
			int x = (int)(10 * Math.random());
			if(x <= 4) {
				happy = true;
				return name + " is clean and had fun taking the bath!";
			} else
				return name + " is clean now.";
		}
	}

	public String grow() { //growth of the dog
		if(age < 15) {
			age++;
			int x = (int)(100 * Math.random());
			if(x > 80) {
				happy = false;
				return name + " woke up grumpy!";
			} else if(x < 20) {
				healthy = false;
				happy = false;
				return name + " woke up feeling ill!";
			} else if(x % 10 == 0) {
				alive = false;
				return "Oh no, " + name + " got run over by a car!";
			} else
				return "";
		} else {
			alive = false;
			return name + " lived to an old age and is now at a better place.";
		}
	}
}

public class DogGame { //dog game class
	String dogName, input, food;
	Dog theDog;
	int counter = 1;

	public void gamePlay() { //the game play
		Scanner in = new Scanner(System.in);
		System.out.println("What is your dog's name?");
		dogName = in.nextLine();
		theDog = new Dog(dogName);
		System.out.println();
		displayHelp();
		System.out.println();
		while(theDog.getAlive()) {
			System.out.println("What would you like to do?");
			input = in.nextLine();
			System.out.println();
			if(input.equals("stats"))
				displayStats();
			else if(input.equals("play")) {
				System.out.println(theDog.play());
				counter++;
			} else if(input.equals("feed")) {
				System.out.println("What do you want to feed " + dogName + "?");
				food = in.nextLine();
				System.out.println();
				System.out.println(theDog.feed(food));
				counter++;
			} else if(input.equals("bathe")) {
				System.out.println(theDog.bathe());
				counter++;
			} else if(input.equals("vet")) {
				System.out.println(theDog.vet());
				counter++;
			} else if(input.equals("help"))
				displayHelp(); 
			else if(input.equals("quit"))
				break;
			else {
				System.out.println("Please enter one of the following.");
				System.out.println();
				displayHelp();
			}
	
			if(counter % 3 == 0 && !input.equals("stats") && !input.equals("help"))
				System.out.println("\n" + theDog.grow());
			System.out.println();
		}

		if(!theDog.getAlive()) { //if the dog died
			System.out.println(".::GAME OVER::.");
			System.out.println();
			System.out.println("Final stats:");
			System.out.println();
			displayStats();
			System.out.println();
		}
	}
	
	public void displayStats() { //display dog stats
		System.out.println("Name: " + dogName);
		System.out.println("Age: " + theDog.getAge() + " y/o");
		if(theDog.getHealthy())
			System.out.println("Healthy: Yes");
		else
			System.out.println("Healthy: No");
		if(theDog.getHappy())
			System.out.println("Happy: Yes");
		else
			System.out.println("Happy: No");
		if(theDog.getHungry())
			System.out.println("Hungry: Yes");
		else
			System.out.println("Hungry: No");
		if(theDog.getClean())
			System.out.println("Clean: Yes");
		else
			System.out.println("Clean: No");
	}

	public void displayHelp() { //display help/options
		System.out.println("Enter 'stats' to view your dog's stats.");
		System.out.println("Enter 'play' to play with your dog.");
		System.out.println("Enter 'feed' to feed your dog.");
		System.out.println("Enter 'bathe' to bathe your dog.");
		System.out.println("Enter 'vet' to take your dog to the vet.");
		System.out.println("Enter 'help' to view this list of options.");
		System.out.println("Enter 'quit' to quit the game.");
		System.out.println("Note: This game is case-sensitive.");
	}
}

class ExecuteDogGame { //class with main method; this is the .class file to execute
	public static void main(String args[]) { //main method
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("\nWould you like to raise a dog? y/n");
			String answer = in.nextLine();
			if(answer.equals("y") || answer.equals("Y")) {
				System.out.println("\nGreat!\n");
				DogGame newGame = new DogGame();
				newGame.gamePlay();
				System.out.println("Thanks for playing!\n");
				break;
			} else if(answer.equals("n") || answer.equals("N")) {
				System.out.println("\nOkay, have a nice day!\n");
				break;
			} else
				System.out.println("\nPlease enter y/n.");
		}
	}
}
