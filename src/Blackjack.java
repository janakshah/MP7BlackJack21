import java.lang.Math;

public class Blackjack {

	public static void main(String[] args) {

		playGame(deck, name);
	}

	//Names of Deck of Cards 
	public static int[] deck = {2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11,11,11,11};
	public static String[] name = {"2","2","2","2","3","3","3","3","4","4","4","4","5","5","5","5","6","6","6","6","7","7","7","7","8","8","8","8","9","9","9","9","10","10","10","10","Jack","Jack","Jack","Jack","Queen","Queen","Queen","Queen","King","King","King","King","Ace","Ace","Ace","Ace"};

	public static int[] deckArray = new int[52]; 
	
	public static void deckShuffler(){		
		for (int i = 0; i < deck.length; i++) {
		int randomNum = (int) ((Math.random()) * 52);
		deckArray[i] = randomNum;
		}
	}

	public static void playGame(int[] newDeck, String[] deckName) {
		
		//Shuffle the deck, by creating an array of random integers.
		deckShuffler();

		//This is the point where our game begins.
		System.out.println("Welcome to BlackJack! What is your name?");
		//Asks for your name
		String name = TextIO.getln();
		System.out.println();
		System.out.println("You start with a " + deckName[deckArray[0]] + " and a " + deckName[deckArray[1]] + ".");
		int playerCardTotal = newDeck[deckArray[0]] + newDeck[deckArray[1]];
		System.out.println("Your total is " + playerCardTotal + ".");
		System.out.println();

		//This is the first check-up if the Player wins in round 1 with BlackJack.
		if (playerCardTotal == 21){
			System.out.println("Blackjack, You Win.");
			System.exit(0);

		}
		//If player goes over 21, player loses.
		if (playerCardTotal > 21){
			System.out.println("Bust, You Lose.");
			System.exit(0);

		}
		//We must create a hand for the Dealer as well.
		System.out.println("The dealer has a " + deckName[deckArray[2]] + " showing, and a hidden card.");
		int dealerCardTotal = newDeck[deckArray[2]] + newDeck[deckArray[3]];
		
		//Dealer Bust Check.
		if (dealerCardTotal > 21) {     
			System.out.println();
			System.out.println("Dealers total is " + dealerCardTotal + ".");
			System.out.println("Dealer Busted, You Win!");
			System.exit(0);

		}
		
		//Dealer Blackjack Check.
		if (dealerCardTotal == 21) {    
			System.out.println();
			System.out.println("Dealer reveals his second card: " + deckName[deckArray[3]] + ".");
			System.out.println("Dealers total is " + dealerCardTotal + ".");
			System.out.println();
			System.out.println("Dealer has BlackJack, You Lost.");
			System.exit(0);

		}
		System.out.println("His total is hidden.");
		System.out.println();


		////We incorporated TextIO into this code because it made inputing much easier.
		System.out.print("Would you like to \"hit\" or \"stay\"? ");
		String nextCard = TextIO.getln();
		System.out.println();

		int deckCount = 4; 
		if (nextCard.equalsIgnoreCase("hit")){
			// While loop to ensure different decks & multiple "hits".
			while (playerCardTotal < 21 && nextCard.equalsIgnoreCase("hit")){
				if (nextCard.equalsIgnoreCase("hit")) {
					if (newDeck[deckArray[deckCount]] == 11 && (playerCardTotal + newDeck[deckArray[deckCount]]) > 21) {
						newDeck[deckArray[deckCount]] = 1;
					} else {
						System.out.println("You drew a " + deckName[deckArray[deckCount]] + ".");
						playerCardTotal = playerCardTotal + newDeck[deckArray[deckCount]];
						System.out.println("Your total is " + playerCardTotal + ".");
						System.out.println();
						deckCount++;        //Adds 1 to ensure next card then what was previously pulled.
						
						//Bust check.
						if (playerCardTotal > 21){
							System.out.println("You Bust, You Lose.");
							System.exit(0);
						}
						//BlackJack check.
						if (playerCardTotal == 21){
							System.out.println("Blackjack, You Win.");
							System.exit(0);
						}
						
						System.out.print("Would you like to \"hit\" or \"stay\"? ");
						nextCard = TextIO.getln();
						System.out.println();
					}
				}
			}        
		}
		
		System.out.println("Dealers Turn.");
		
		// Dealers hidden card.
		System.out.println("His hidden card was a " + deckName[deckArray[3]] + ".");

		deckCount++;
		//We decided to limit the dealer to not being able to "hit" if his total is larger than 16.
		while (dealerCardTotal < 16){
			System.out.println();
			System.out.println("Dealer chooses to hit.");
			System.out.println("He draws a " + deckName[deckArray[deckCount]] + ".");
			
			dealerCardTotal = dealerCardTotal + newDeck[deckArray[deckCount]];
			System.out.println();
			System.out.println("His total is " + dealerCardTotal);
			deckCount++;
			
			// bust check - no need for blackjack check due to final win sequence
			if (dealerCardTotal > 21){
				System.out.println();
				System.out.println("Dealer Busted, You Win!");
				System.exit(0);
			}
			//If the Dealer is above 16 or below 21 total, Dealer forced to stay.
			if (dealerCardTotal < 21 && dealerCardTotal > 16){
				System.out.println();
				System.out.println("Dealer Stays.");
			}
		}

		//This is the final Win Sequence
		System.out.println();
		System.out.println("Dealer total is " + dealerCardTotal);
		System.out.println(name + "'s total is " + playerCardTotal);
		System.out.println();

		if (dealerCardTotal > playerCardTotal && dealerCardTotal <= 21) {
			System.out.println("Dealer Wins.");
		} 
		if (dealerCardTotal == playerCardTotal) {
			System.out.println("You both Draw.");
		}
		if (dealerCardTotal < playerCardTotal && playerCardTotal <= 21) {
			System.out.println(name + " Wins!");
		} 
	}
}
