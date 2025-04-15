import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents the table in a game of Blackjack, where the main gameplay occurs. Handles player interaction, betting
 * logic, and the main game loop control. This class manages console input for placing bets and determining if the user
 * wants to play additional rounds. Logging is used to track player actions and errors.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class BlackJackTable implements CardGame
{
    private BlackJackDealer dealer;
    private BlackJackPlayer player;
    private Scanner scanner;
    private int bet;
    private int dealerBet;
    private int pot;
    private LogHelper logHelper;
    private int roundTracker;
    private double playerWins;
    private double playerLosses;

    /**
     * Constructs a new Table with input handling and logging initialized.
     */
    public BlackJackTable()
    {
        player = new BlackJackPlayer("Player", 500);
        dealer = new BlackJackDealer(2500);
        dealerBet = 0;
        bet = 0;
        pot = 0;
        roundTracker = 0;
        scanner = new Scanner(System.in);
        logHelper = new LogHelper(BlackJackTable.class);
    }

    /**
     * Returns the dealer for the Blackjack game.
     *
     * @return the BlackJackDealer instance
     */
    public BlackJackDealer getDealer()
    {
        return dealer;
    }

    /**
     * Returns the player in the Blackjack game.
     *
     * @return the BlackJackPlayer instance
     */
    public BlackJackPlayer getPlayer()
    {
        return player;
    }

    /**
     * Returns the current bet placed by the player.
     *
     * @return the player's bet amount
     */
    public int getBet()
    {
        return bet;
    }

    /**
     * Returns the current total pot for the round.
     *
     * @return the pot value
     */
    public int getPot()
    {
        return pot;
    }

    /**
     * Sets the dealer for the Blackjack game.
     *
     * @param dealer the dealer to set
     * @throws IllegalStateException if dealer is null or does not have 2500 in stash
     */
    public void setDealer(BlackJackDealer dealer)
    {
        if (dealer != null && dealer.getStash() == 2500)
        {
            this.dealer = dealer;
        }
        else if (dealer == null)
        {
            throw new IllegalStateException("Dealer cannot be null");
        }
        else if (dealer.getStash() != 2500)
        {
            throw new IllegalStateException("Dealer must start with 2500 in their stash");
        }
    }

    /**
     * Sets the player for the Blackjack game.
     *
     * @param player the player to set
     * @throws IllegalStateException if player is null or does not have 500 in stash
     */
    public void setPlayer(BlackJackPlayer player)
    {
        if (player != null && player.getStash() == 500)
        {
            this.player = player;
        }
        else if (player == null)
        {
            throw new IllegalStateException("Player cannot be null");
        }
        else if (player.getStash() != 500)
        {
            throw new IllegalStateException("Player must start with 500 in their stash");
        }
    }

    /**
     * Sets the player's bet for the round.
     *
     * @param bet the amount to bet
     * @throws IllegalBetException if the bet is invalid
     */
    public void setBet(int bet) throws IllegalBetException
    {
        if (bet > 0 && bet <= player.getStash())
        {
            this.bet = bet;
        }
        else if (bet < 0)
        {
            throw new IllegalBetException("Bet cannot be a negative number");
        }
        else if (bet > player.getStash())
        {
            throw new IllegalBetException("Bet cannot be a number greater than the current amount in the player's stash");
        }
    }

    /**
     * Sets the dealer's contribution to the pot.
     *
     * @param dealerBet the amount the dealer is betting
     */
    public void setDealerBet(int dealerBet) throws IllegalBetException
    {
        this.dealerBet = dealerBet;
    }

    /**
     * Sets the total pot amount for the round.
     *
     * @param pot the pot value
     * @throws IllegalArgumentException if the pot is negative
     */
    public void setPot(int pot)
    {
        if (pot >= 0)
        {
            this.pot = pot;
        }
        else
        {
            throw new IllegalStateException("The pot cannot contain a negative number");
        }
    }

    /**
     * Handles the betting process for a round of Blackjack.
     */
    public void takeBets()
    {
        boolean running = true;
        System.out.println("Place your bets!");

        while (running)
        {
            try
            {
                setBet(scanner.nextInt());
                scanner.nextLine();

                player.setStash(player.getStash() - bet);
                System.out.println("You bet $" + bet);
                logHelper.logInfoMessage("Player bet $" + bet);

                if (dealer.getStash() >= bet)
                {
                    System.out.println("The dealer matches your bet");
                    logHelper.logInfoMessage("Dealer matches player's bet of $" + bet);

                    dealerBet = bet;
                    pot = bet + dealerBet;
                    dealer.setStash(dealer.getStash() - bet);
                }
                else
                {
                    System.out.println("The dealer goes all in!");
                    logHelper.logInfoMessage("Dealer goes all in with $" + dealerBet);

                    dealerBet = dealer.getStash();
                    pot = bet + dealerBet;
                    dealer.setStash(0);
                }

                System.out.println("The total pot is now $" + pot + "\n");
                logHelper.logInfoMessage("User made a bet");
                running = false;
            }
            catch (IllegalBetException e)
            {
                System.err.println(e.getMessage());
                logHelper.logWarningMessage(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.err.println("Please enter a whole number without any characters");
                logHelper.logWarningMessage(e.getMessage());
                scanner.nextLine();
                continue;
            }
            catch (Exception e)
            {
                System.err.println("An unexpected error occurred");
                logHelper.logSevereMessage(e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Starts the main game loop for Blackjack.
     * Welcomes the player, takes bets, and repeatedly prompts the user
     * to play additional rounds or exit. Uses logging to track game flow and user input errors.
     */

    @Override
    public void play()
    {
        boolean running = true;
        String userInput = null;

        printMainMenu();

        do
        {
            boolean roundResolved = false;
            takeBets();
            roundStartDeal();
            printPlayers();

            if(player.hasBlackJack() || dealer.hasBlackJack())
            {
                roundResolved = true;
                scoreGame();
            }

            System.out.println("Please enter one of the following:");
            System.out.println("* Stay");
            System.out.println("* Hit");
            System.out.println("* Surrender");
            System.out.println("* Double Down");

            while(true)
            {
                Set<String> validInputs = Set.of("stay", "hit", "surrender", "double down");
                userInput = scanner.nextLine().toLowerCase().trim();

                if(!validInputs.contains(userInput))
                {
                    System.err.println("Please enter a valid option");
                }
                else
                {
                    break;
                }
            }


            if(userInput.equals("double down"))
            {
                try
                {
                    int previousBet = getBet();

                    player.setStash(player.getStash() - previousBet);
                    setBet(getBet() * 2);
                    setPot(getPot() + previousBet);

                    System.out.println("Player doubles down!");

                    player.receiveCard(dealer.deal(), true);
                    printPlayers();

                    if(player.scoreHand() > 21)
                    {
                        roundResolved = true;
                        scoreGame();
                    }

                    userInput = "stay";
                }
                catch (IllegalBetException e)
                {
                    System.err.println("You don't have enough money to double down!");
                    System.out.println("Would you like to hit, stay or surrender instead?");
                    userInput = scanner.nextLine().toLowerCase().trim();
                }
            }
            if (userInput.equals("hit"))
            {
                boolean isHit = true;

                while (isHit)
                {
                    player.receiveCard(dealer.deal(), true);
                    logHelper.logInfoMessage("Player chooses to hit");
                    printPlayers();

                    if (player.scoreHand() > 21)
                    {
                        roundResolved = true;
                        isHit = false;
                        scoreGame();
                    }
                    else
                    {
                        System.out.println("Hit, stay or surrender?");
                        userInput = scanner.nextLine().toLowerCase();

                        if(!userInput.contains("hit"))
                        {
                            isHit = false;
                        }
                    }
                }
            }
            if(userInput.equals("surrender"))
            {
                int playerRefund = getBet() / 2;
                int dealerWinnings = getPot() - playerRefund;

                System.out.println("Player surrenders");
                System.out.println("Dealer wins $" + dealerWinnings);
                System.out.println("Player receives a refund of $" + playerRefund + "\n");

                dealer.setStash(dealer.getStash() + dealerWinnings);
                player.setStash(player.getStash() + playerRefund);
                setPot(0);

                roundResolved = true;
                playerLosses++;
                startNextRound();
            }
            if(userInput.equals("stay"))
            {
                System.out.println("Dealer's turn...\n");
                dealer.showAllCards();

                while(dealer.scoreHand() < 17)
                {
                    Card card = dealer.deal();
                    dealer.receiveCard(card, true);
                    logHelper.logInfoMessage("Dealer draws a " + dealer.getHand().getLast().toString());
                    System.out.println("Dealer draws a " + dealer.getHand().getLast().toString());
                }

                if(dealer.scoreHand() > 21)
                {
                    roundResolved = true;
                    scoreGame();
                }
            }
            if(!roundResolved)
            {
                scoreGame();
            }
        }
        while (running);
    }

    /**
     * Prints the current hands and information for both the player and dealer.
     */
    public void printPlayers()
    {
        System.out.println(player.toString());
        System.out.println(dealer.toString());
    }

    /**
     * Deals two cards each to the player and dealer to begin a round.
     * The dealer's first card is hidden.
     */
    public void roundStartDeal()
    {
        player.receiveCard(dealer.deal(), true);
        player.receiveCard(dealer.deal(), true);

        dealer.receiveCard(dealer.deal(), false);
        dealer.receiveCard(dealer.deal(), true);
    }

    /**
     * Compares player and dealer scores and determines the winner.
     * Distributes the pot accordingly and handles tie conditions.
     */
    public void scoreGame()
    {
        if(player.hasBlackJack())
        {
            System.out.println("Player has a Blackjack!");
            logHelper.logInfoMessage("Player receives the pot of $" + getPot());
            System.out.println("Player received $" + getPot() + "\n");
            player.setStash(player.getStash() + pot);
            setPot(0);
            playerWins++;
        }
        else if(dealer.hasBlackJack())
        {
            System.out.println("Dealer has a Blackjack!");
            logHelper.logInfoMessage("Dealer receives the pot of $" + getBet() + "\n");
            dealer.showAllCards();
            dealer.setStash(dealer.getStash() + getBet());
            setPot(0);
            playerLosses++;
        }
        // Returns the amount both player bet, versus the option of splitting the pot
        else if(player.scoreHand() == dealer.scoreHand() || player.hasBlackJack() && dealer.hasBlackJack())
        {
            System.out.println("Push! Both the player and the dealer have " + player.scoreHand() + " points!");
            logHelper.logInfoMessage("Push: both player and dealer have " + player.scoreHand());
            System.out.println("Returning bets... \n");

            System.out.println("Player receives $" + getBet());
            logHelper.logInfoMessage("Refunding bet of $" + getBet() + " to player and $" + dealerBet + " to dealer.");
            player.setStash(player.getStash() + getBet());

            System.out.println("Dealer receives $" + dealerBet + "\n");
            dealer.setStash(dealer.getStash() + dealerBet);

            setPot(0);
        }
        else if(player.scoreHand() > 21)
        {
            System.out.println("Player busts!");
            System.out.println("Player Score: " + player.scoreHand());
            System.out.println("Dealer receives the pot of $" + getPot() + "\n");
            logHelper.logInfoMessage("Player busts with score " + player.scoreHand());
            dealer.setStash(dealer.getStash() + getPot());
            setPot(0);
            playerLosses++;
        }
        else if(dealer.scoreHand() > 21)
        {
            System.out.println("Dealer busts!");
            logHelper.logInfoMessage("Dealer score: " + dealer.scoreHand());
            System.out.println("Player receives the pot of $" + getPot() + "\n");
            player.setStash(player.getStash() + pot);
            setPot(0);
            playerWins++;
        }
        else if(player.scoreHand() > dealer.scoreHand())
        {
            System.out.println("Player wins with " + player.scoreHand() + " points!");
            System.out.println("Player receives the pot of $" + getPot() + "\n");
            logHelper.logInfoMessage("Player wins with " + player.scoreHand() + " vs dealer " + dealer.scoreHand());
            player.setStash(player.getStash() + pot);
            setPot(0);
            playerWins++;
        }
        else if(dealer.scoreHand() > player.scoreHand())
        {
            System.out.println("Dealer wins with " + dealer.scoreHand() + " points!");
            System.out.println("Dealer receives the pot of $" + getPot() + "\n");
            logHelper.logInfoMessage("Dealer wins with " + dealer.scoreHand() + " vs player " + player.scoreHand());
            dealer.setStash(dealer.getStash() + getPot());
            setPot(0);
            playerLosses++;
        }

        startNextRound();
    }

    /**
     * Prompts the player to start another round or exit the game.
     * Resets hands and deck if the player chooses to continue.
     * This should be followed by a continue statement in the main game loop, in order to restart the loop.
     * If the player says no, it shuts down the game.
     */
    public void startNextRound()
    {
        if(player.getStash() == 0)
        {
            System.out.println("Player has no money remaining to continue. Game over!");
            System.exit(0);
        }
        if(dealer.getStash() == 0)
        {
            System.out.println("Dealer has no money remaining to continue. You win!");
            System.exit(0);
        }

        roundTracker++;
        printMainMenu();

        System.out.println("Would you like to play another round?");

        try
        {
            String response = scanner.nextLine().toLowerCase().trim();

            if(response.equals("yes"))
            {
                System.out.println("Starting next round...\n");
                logHelper.logInfoMessage("Player chooses to continue.");

                player.clearHand();
                dealer.clearHand();
                dealer.resetDeck();
            }
            else if(response.equals("no"))
            {
                System.out.println("Thanks for playing!");
                logHelper.logInfoMessage("Player chooses to exit the game.");
                System.exit(1);
            }
            else
            {
                System.err.println("Please enter a valid option.");
                logHelper.logWarningMessage("Invalid input during next round prompt.");
            }
        }
        catch(Exception e)
        {
            System.err.println("An unexpected error occurred.");
            logHelper.logSevereMessage(e.getMessage());
        }
    }

    public void printMainMenu()
    {
        if(roundTracker == 0)
        {
            System.out.println("Welcome to the game!");
        }
        else
        {
            System.out.println("Round over!");
        }

        System.out.println("Rounds played: " + roundTracker);

        if(playerLosses == 0)
        {
            System.out.println("W/L Ratio: " + playerWins);
        }
        else
        {
            System.out.printf("W/L Ratio: %.2f%n", playerWins / playerLosses);
        }

        System.out.println("Money remaining: $" + player.getStash() + "\n");
    }
}
