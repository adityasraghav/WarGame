package org.WarGameApp;

import java.util.*;

/**
 * @author Aditya Raghav <adityaraghav@ufl.edu>
 */
class Card
{
    /**
    * Rank of the card (2,3,4,5,6...Jack,King,Ace)
    */
    private int rank;

    /**
     * Suit of the card (spades, hearts, clubs and diamonds)
     * Not important for this implementation since the suits have
     * no affect on the outcome of a play. Just used to display.
     */
    private String suit;

    /**
     * Constructor of the card object
     * @param rank rank of the card
     * @param suit suit of the card
     */
    Card(int rank, String suit)
    {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Getter method for rank
     * @return the card rank
     */
    int getCardRank()
    {
        return this.rank;
    }

    /**
     * Getter method for suit
     * @return the card suit
     */
    String getCardSuit()
    {
        return this.suit;
    }

    /**
     * Setter method
     * @param rank rank of the card
     * @param suit suit of the card
     */
    void setCard(int rank, String suit)
    {
        this.rank = rank;
        this.suit =suit;
    }

    /**
     * @return The card as String eg. Ace of Spades
     */
    @Override
    public String toString()
    {
        StringBuilder cardString = new StringBuilder();

        switch(rank)
        {
            case 14:
                cardString.append("Ace");
                break;
            case 13:
                cardString.append("King");
                break;
            case 12:
                cardString.append("Queen");
                break;
            case 11:
                cardString.append("Jack");
                break;
            default:
                cardString.append(rank);
                break;
        }
        cardString.append(" of ").append(this.suit);
        return cardString.toString();
    }
}

class Deck
{
    /**
    * Object for the deck of cards
    */
    private List<Card> newDeck = new ArrayList<Card>();

    /**
    * Intialize a standard deck
    */
    Deck()
    {
        for(int x=0; x<4; x++)
        {
            String s = null;
            switch(x)
            {
                case 0:
                    s = "Hearts";
                    break;
                case 1:
                    s = "Spades";
                    break;
                case 2:
                    s = "Diamonds";
                    break;
                case 3:
                    s = "Clubs";
                    break;
            }
            for(int y=2; y<15; y++)
            {
                newDeck.add(new Card(y,s));
            }
        }

        /**
          Shuffle the Deck
         */
        Collections.shuffle(newDeck, new Random());
    }

    List<Card> getNewDeck() {
        return newDeck;
    }
}

public class WarGame
{
    public static void main(String args[])
    {
        long start = System.currentTimeMillis();
        Deck deck  = new Deck();

        LinkedList<Card> Player1 = new LinkedList<Card>();
        LinkedList<Card> Player2 = new LinkedList<Card>();

        Player1.addAll(deck.getNewDeck().subList(0, 26));
        Player2.addAll(deck.getNewDeck().subList(26, 52));

        LinkedList<Card> war1 = new LinkedList<Card>();
        LinkedList<Card> war2 = new LinkedList<Card>();

        while(true)
        {
            if(Player1.size()==0 && Player2.size()==0)
            {
                System.out.println("The Game ends in a draw");
                break;
            }
            else if(Player1.size() == 0 ){
                System.out.println("Game over the Second player has won the game");
                break;
            }
            else if(Player2.size() == 0){
                System.out.println("Game over the First player has won the game");
                break;
            }

            Card p1 = Player1.remove();
            Card p2 = Player2.remove();

            System.out.println("Card played by first player " + p1.toString());
            System.out.println("Card played by second player " + p2.toString());

            if(p1.getCardRank() > p2.getCardRank())
            {
                Player1.add(p1);
                Player1.add(p2);
                System.out.println("This hand was won by first player");
            }

            else if(p2.getCardRank() > p1.getCardRank())
            {
                Player2.add(p1);
                Player2.add(p2);
                System.out.println("This hand was won by second player");
            }
            else
            {
                System.out.println("War");

                war1.add(p1);
                war2.add(p2);
                boolean war = true;
                while(war)
                {
                    if(Player1.size()==0 || Player2.size()==0)
                    {
                        war = false;
                    }

                    else if(Player1.size()==1 && Player2.size()==1)
                    {
                        System.out.println("Both players have only one card left");
                        System.out.println("War card for first player is " + Player1.get(0).toString());
                        System.out.println("War card for second player is " + Player2.get(0).toString());

                        if(Player1.peekFirst().getCardRank() > Player2.peekFirst().getCardRank())
                        {
                            Player1.add(Player2.get(0));
                            Player2.remove();
                            System.out.println("First player wins the war");
                            war = false;
                        }
                        else if(Player1.peekFirst().getCardRank() < Player2.peekFirst().getCardRank())
                        {
                        Player2.add(Player1.get(0));
                        Player1.remove();
                        System.out.println("Second player wins the war");
                            war = false;
                        }
                        else
                        {
                            Player1.remove();
                            Player2.remove();
                            war = false;
                        }
                    }
                    else if(Player1.size()==1 && Player2.size()>1)
                    {
                        System.out.println("War card for first player is " + Player1.get(0).toString());
                        System.out.println("Second player puts face down card");
                        war2.add(Player2.remove());
                        System.out.println("Face Up second player is " + Player2.get(0).toString());
                        war2.add(Player2.remove());

                        if(Player1.peekFirst().getCardRank() > war2.peekLast().getCardRank())
                        {
                            System.out.println("First player wins the war");
                            Player1.addAll(war2);
                            war2.clear();
                            war = false;
                        }
                        else if(Player1.peekFirst().getCardRank() < war2.peekLast().getCardRank())
                            {
                            Player1.remove();
                            System.out.println("Second player wins the war");
                            war = false;
                        }
                        else
                        {
                            System.out.println("Cards are equal war continues");
                        }
                    }
                    else if(Player2.size()==1 && Player1.size()>1)
                    {
                        System.out.println("First player puts face down card");
                        war1.add(Player1.remove());
                        System.out.println("Face Up first player is " + Player1.get(0).toString());
                        war1.add(Player1.remove());
                        System.out.println("War card for second player is " + Player2.get(0).toString());

                        if(Player2.peekFirst().getCardRank() > war1.peekLast().getCardRank())
                        {
                            System.out.println("Second player wins the war");
                            Player2.addAll(war1);
                            war1.clear();
                            war = false;
                        }
                        else if(Player2.peekFirst().getCardRank() < war1.peekLast().getCardRank())
                        {
                            Player2.remove();
                            System.out.println("First player wins the war");
                            war = false;
                        }
                        else
                        {
                            System.out.println("Cards are equal war continues");
                        }
                    }
                    else {
                        System.out.println("First player puts face down card");
                        war1.add(Player1.remove());
                        System.out.println("Face Up first player is " + Player1.get(0).toString());
                        war1.add(Player1.remove());
                        System.out.println("Second player puts face down card");
                        war2.add(Player2.remove());
                        System.out.println("Face Up first player is " + Player2.get(0).toString());
                        war2.add(Player2.remove());

                        if (war2.peekLast().getCardRank() > war1.peekLast().getCardRank()) {
                            System.out.println("Second player wins the war");
                            Player2.addAll(war2);
                            Player2.addAll(war1);
                            war1.clear();
                            war2.clear();
                            war = false;
                        } else if (war2.peekLast().getCardRank() < war1.peekLast().getCardRank()) {
                            System.out.println("First player wins the war");
                            Player1.addAll(war2);
                            Player1.addAll(war1);
                            war1.clear();
                            war2.clear();
                            war = false;
                        } else {
                            System.out.println("Cards are equal war continues");
                        }
                    }
                }
            }
            if(Math.max(1, System.currentTimeMillis() - start)>10000)
            {
                System.out.println("This game took too long, hence the result is a draw");
                return;
            }
        }
    }
}
