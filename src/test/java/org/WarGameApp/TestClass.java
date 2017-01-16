package org.WarGameApp;

import junit.framework.*;
import java.util.List;

public class TestClass extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestClass( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestClass.class );
    }


    public void testcardInit()
    {
        Card newCard = new Card(2, "Hearts");
        Assert.assertTrue(newCard.getCardRank()==2);
        Assert.assertTrue(newCard.toString().equals("2 of Hearts"));
    }

    public void testcardSet()
    {
        Card newCard = new Card(2, "Hearts");
        newCard.setCard(5,"Clubs");
        Assert.assertTrue(newCard.getCardRank()==5);
        Assert.assertTrue(newCard.toString().equals("5 of Clubs"));
    }

    public void testfaceCard()
    {
        Card newCard = new Card(11, "Hearts");
        Assert.assertTrue(newCard.toString().equals("Jack of Hearts"));
        newCard.setCard(12,"Clubs");
        Assert.assertTrue(newCard.toString().equals("Queen of Clubs"));
        newCard.setCard(13,"Diamonds");
        Assert.assertTrue(newCard.toString().equals("King of Diamonds"));
        newCard.setCard(14,"Spades");
        Assert.assertTrue(newCard.toString().equals("Ace of Spades"));
    }

    public void testdeckTest()
    {
        Deck deck  = new Deck();
        List<Card> initializedDeck = deck.getNewDeck();
        Assert.assertTrue(initializedDeck.size()==52);
        int [] suits = new int[4];
        int [] cards= new int[13];

        for (Card deckCard: initializedDeck)
        {
            if (deckCard.getCardSuit().equals("Hearts"))
                suits[0]++;
            else if (deckCard.getCardSuit().equals("Spades"))
                suits[1]++;
            else if (deckCard.getCardSuit().equals("Diamonds"))
                suits[2]++;
            else if (deckCard.getCardSuit().equals("Clubs"))
                suits[3]++;

            cards[deckCard.getCardRank()-2]++;
        }

        Assert.assertTrue(suits[0]==13 && suits[1]==13&&suits[2]==13&&suits[3]==13);

        for(int y=0; y<13; y++)
        {
            Assert.assertTrue(cards[y]==4);
        }
    }
}