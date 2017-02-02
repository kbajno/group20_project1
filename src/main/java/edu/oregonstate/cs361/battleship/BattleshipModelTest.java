package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by annie on 1/28/17.
 */
class BattleshipModelTest {
    // instantiate a fresh game object for use in this test suite
    BattleshipModel game = new BattleshipModel();

    @Test
    public void test_constructor() {
        /* Tests that the game object's ships are already populated with the
        expected data */

        //Check the user ships
        assertEquals(game.aircraftCarrier.name, "AircraftCarrier");
        assertEquals(game.aircraftCarrier.length, 5);
        assertEquals(game.battleship.name, "Battleship");
        assertEquals(game.battleship.length, 4);
        assertEquals(game.cruiser.name, "Cruiser");
        assertEquals(game.cruiser.length, 3);
        assertEquals(game.destroyer.name, "Destroyer");
        assertEquals(game.destroyer.length, 2);
        assertEquals(game.submarine.name, "Submarine");
        assertEquals(game.submarine.length, 2);

        //Check the computer ships
        assertEquals(game.computer_aircraftCarrier.name, "Computer_AircraftCarrier");
        assertEquals(game.computer_aircraftCarrier.length, 5);
        assertEquals(game.computer_battleship.name, "Computer_Battleship");
        assertEquals(game.computer_battleship.length, 4);
        assertEquals(game.computer_cruiser.name, "Computer_Cruiser");
        assertEquals(game.computer_cruiser.length, 3);
        assertEquals(game.computer_destroyer.name, "Computer_Destroyer");
        assertEquals(game.computer_destroyer.length, 2);
        assertEquals(game.computer_submarine.name, "Computer_Submarine");
        assertEquals(game.computer_submarine.length, 2);

        //Check the hit and miss arrays
        assertEquals(game.playerHits.length, 100);
        assertEquals(game.playerMisses.length, 100);
        assertEquals(game.computerHits.length, 100);
        assertEquals(game.computerMisses.length, 100);
    }

    /*
    @Test
    public void test_ships() {
        // Test that ships are accessible and editable through the game object
    }

    @Test
    public void test_squares() {
        // Test that squares are accessible and editable through the game object.
    }

    @Test
    public void test_arrays() {
        // Test that arrays are accessible and editable through the game object.
    }

    */
}
