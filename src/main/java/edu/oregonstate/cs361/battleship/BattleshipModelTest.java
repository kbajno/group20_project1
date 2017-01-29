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
        assertEquals(game.aircraftCarrier.name, "AircraftCarrier");
        assertEquals(game.aircraftCarrier.length, 5);
        // ...etc.
    }

    /*
    @Test
    public void test_ships() {
        // Test that ships are accessible and editable through the game object
    }

    @Test
    public void test_squares() {
        // Test that squares are accessible and editable through the game object.
    } */
}
