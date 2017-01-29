package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by annie on 1/28/17.
 */
class BattleshipModelTest {
    BattleshipModel game = new BattleshipModel();

    @Test
    public void test_constructor() {
        assertEquals(game.aircraftCarrier.name, "AircraftCarrier");
    }

    @Test
    public void test_squares() {
        game.aircraftCarrier.start.Down = 2;
        assertEquals(game.aircraftCarrier.start.Down, game.aircraftCarrier.start.get_down());
    }
}