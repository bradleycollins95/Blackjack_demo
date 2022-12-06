import java.util.Scanner;

/**
 * Console application to play a game of Blackjack
 *
 * @author 20108508
 */
public class BlackJackGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int score = 0;
        String name;
        String input;

        //greets the player, defines the rules
        System.out.println("Welcome to BlackJack!\n");

        System.out.println("""
                Rules:
                
                - Blackjack hands are scored by their point total.\s
                - The hand with the highest total wins as long as it doesn't exceed 21.\s
                - A hand with a higher total than 21 is said to bust.\s
                - Cards 2 through 10 are worth their face value, and face cards (jack, queen, king) are also worth 10.
                """);

        System.out.println("Please enter your name to begin:"); //grabs user name for reference
        input = scanner.nextLine();                             //grabs user name for reference
        name = input;                                           //grabs user name for reference

        //main loop
        do {
            //new game message
            int balance = 100;
            System.out.println("\nA new game has begun.");
            System.out.printf("\nYour current balance is: $%d\n", balance); //display player balance
            System.out.println("Please enter an amount to bet (in $dollars):"); //ask user for bet amount
            input = scanner.nextLine();
            int wager = Integer.parseInt(input); //store the user bet amount to calculate from starting balance

            //initialize player, dealer, and game state
            Player player = new Player(name, balance);
            Player dealer = new Player("Dealer", balance);
            Deck deck = new Deck();
            deck.shuffle(); //shuffles deck before game begins
            boolean gameOver = false;

            //give cards to the player
            player.addCard(deck.draw()); //draws card to player's hand
            player.addCard(deck.draw()); //draws card to player's hand
            System.out.println(player.getHandAsString(false)); //displays both cards drawn

            //give cards to the dealer
            dealer.addCard(deck.draw()); //draws card to dealer's hand
            dealer.addCard(deck.draw()); //draws card to dealer's hand
            System.out.println();
            System.out.println(dealer.getHandAsString(true)); //hides the initial drawn card

            //player's turn
            do {
                System.out.println("Would " + player.getName() + " like to hit or stay? (H or S)");
                do {
                    input = scanner.nextLine();
                } while (!input.equalsIgnoreCase("H") && !input.equalsIgnoreCase("S"));

                //BUST
                if (input.equalsIgnoreCase("H")) { //if player enters "H" or "h"
                    player.addCard(deck.draw());
                    System.out.println(player.getName() + " drew a card.\n");
                    System.out.println(player.getHandAsString(false));
                    //if the user's total value in their hand is above 21, don't update the score, display score
                    //remove their initial bet from their balance and display it
                    if (player.getHandSum() > 21) {
                        System.out.println("You busted and got a total of " + player.getHandSum() + ". Dealer wins this time!");
                        System.out.printf("Your score is: %d\n", score);
                        System.out.printf("Your current balance is: $%d\n", balance - wager);
                        gameOver = true; //game ends, prompt through to play again
                    }
                }
                //STAY: If user stays, add current value of the player hand and run through the dealer's actions
                if (input.equalsIgnoreCase("S")) {
                    System.out.println("You have chosen to stay. Your hand: " + player.getHandSum());
                }

            } while (input.equalsIgnoreCase("H") && !gameOver);

            // dealer's turn
            if (!gameOver) {
                System.out.println("\nDealers turn:\n");
                System.out.println(dealer.getHandAsString(false));
            }

            while (!gameOver) { //as long as the game is running

                //if the dealer has a hand sum of 15 or less, it will gamble and draw again
                if (dealer.getHandSum() <= 15) {
                    // DRAW CARD
                    dealer.addCard(deck.draw());
                    System.out.println(dealer.getName() + " drew another card\n");
                    System.out.println(dealer.getHandAsString(false)); //doesn't hide dealers drawn cards, only the initial one at the start of the game
                    //if the dealer hits 15, they win instantly
                    if (dealer.getHandSum() == 15) {
                        System.out.println("Blackjack! Dealer won.");
                        System.out.printf("Your score is: %d\n", score);
                        System.out.printf("Your current balance is: $%d\n", balance - wager);
                        gameOver = true; //end game
                    }
                    //if the dealer hits over 21 card value, they bust and the player wins.
                    if (dealer.getHandSum() > 21) {
                        System.out.println("Dealer busted and got a total of " + dealer.getHandSum() + ". " + player.getName() + " wins this time!");
                        score++;
                        System.out.printf("Your score is: %d\n", score);
                        System.out.printf("Your current balance is: $%d\n", balance + wager);
                        gameOver = true;
                    }

                } else {
                    //STAY: if the dealer stays, display both player and dealer hand values and calculate winner
                    //if both player and dealer are at or under 21 here, determine winner by highest combined face value
                    System.out.println("Dealer has chosen to stay!\n");
                    int totalDealerSum = dealer.getHandSum();
                    int totalPlayerSum = player.getHandSum();

                    if (totalDealerSum > totalPlayerSum) {
                        System.out.println("Both players has decided to stay. The winner is " + dealer.getName() + " with a total of " + totalDealerSum);
                        System.out.printf("Your score is: %d\n", score);
                        System.out.printf("Your current balance is $%d\n", balance - wager);
                    } else {
                        System.out.println("Both players has decided to stay. The winner is " + player.getName() + " with a total of " + totalPlayerSum);
                        score++;
                        System.out.printf("Your score is: %d\n", score);
                        System.out.printf("Your current balance is: $%d\n", balance + wager);
                    }
                    gameOver = true;
                }

            }

            //ask for new game
            System.out.println();
            System.out.println("Would you like to start a new game?  'Yes/No' :");
            do {
                input = scanner.nextLine();
            } while (!input.equalsIgnoreCase("Yes") && !input.equalsIgnoreCase("No"));

        } while (input.equalsIgnoreCase("Yes"));

        scanner.close();
    }
}