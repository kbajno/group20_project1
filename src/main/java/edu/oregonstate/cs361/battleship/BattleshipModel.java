package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Skeleton created by michaelhilton on 1/26/17.
 * Fleshed out by leian7 on 1/28/17.
 */
public class BattleshipModel {
    
    public class Board {
        GridSquare[][] board;
        List<Ship> playerShips; 
        List<Ship> aiShips;
        
        public Board(){
            this.board = new GridSquare[20][10];
            this.playerShips = new ArrayList<>();
            this.aiShips = new ArrayList<>();
            
            for(int y = 0; y < 20; y++){
                for(int x = 0; x < 10; x++){
                    board[y][x].Across = y;
                    board[y][x].Down = x;
                }
            }
        }
        
        public Boolean placeShip(ShipType shipType, int startX, int startY, ShipOrientation dir, Owner owner){
            int length = shipType.length;
            if(owner == Owner.AI){
                startY += 10;
            }
            //check if there is something in the starting location
            if(isSquareUsed(startX, startY))
                return false;
            
            GridSquare start = new GridSquare(startX, startY);
            //next check the length of the ship
            Ship ship = new Ship(shipType, length, start, dir);
            if(doesShipColide(ship))
                return false;
            
            //all checks good, place ship on board
            if(ship.direction == ShipOrientation.HORIZONTAL){
                //loop hoz
                for(int i = ship.start.Across; i < ship.start.Across + ship.length; i++){
                    board[ship.start.Down][i].shipType = ship.type;
                    board[ship.start.Down][i].state = GridSquareState.SHIP;
                }
            } else {
                //loop vert
                for(int i = ship.start.Down; i < ship.start.Down + ship.length; i++){
                    board[i][ship.start.Across].shipType = ship.type;
                    board[i][ship.start.Across].state = GridSquareState.SHIP;
                }
            }
            
            if(owner == Owner.PLAYER)
                this.playerShips.add(ship);
            else
                this.aiShips.add(ship);
            
            //the ship was placed
            return true;
        }
        
        private Boolean doesShipColide(Ship ship){
            if(ship.direction == ShipOrientation.HORIZONTAL){
                //loop hoz
                for(int i = ship.start.Across; i < ship.start.Across + ship.length; i++){
                    if(isSquareUsed(i, ship.start.Down))
                        return true;
                }
            } else {
                //loop vert
                for(int i = ship.start.Down; i < ship.start.Down + ship.length; i++){
                    if(isSquareUsed(ship.start.Across, i))
                        return true;
                }
            }
            return false;
        }
        
        private Boolean isSquareUsed(int x, int y){
            return this.board[y][x].state == GridSquareState.EMPTY;
        }
    }
   
    // Represents a specific square on the game board.
    public class GridSquare {
        int Across;
        int Down;
        GridSquareState state;
        ShipType shipType;
        
        public GridSquare(){
            this.Across = 0;
            this.Down = 0;
            this.state = GridSquareState.EMPTY;
            this.shipType = ShipType.NONE;
        }
        
        public GridSquare(int x, int y){
            this.Across = x;
            this.Down = y;
            this.state = GridSquareState.EMPTY;
            this.shipType = ShipType.NONE;
        }
    }

    public class Ship {
        String name;
        int length;
        GridSquare start;
        GridSquare end;
        ShipOrientation direction;
        ShipType type;

        // Constructor: initializes name and length
        Ship(String name, int length) {
            this.name = name;
            this.length = length;
            this.start = new GridSquare();  // ship head
            this.end = new GridSquare();    // ship butt
            this.type = ShipType.NONE;
        }
        
        Ship(ShipType type, int length, GridSquare start, ShipOrientation dir){
            this.type = type;
            this.name = type.toString();
            this.length = length;
            this.start = start;
            this.direction = dir;
            //calcualte end
            if(dir == ShipOrientation.HORIZONTAL){
                this.end = new GridSquare(start.Across, start.Down + length);
            } else {
                this.end = new GridSquare(start.Across + length, start.Down);
            }
            
        }
    }
    
    public enum GridSquareState {
        AIHIT, USERHIT, SHIP, EMPTY
    }
    
    public enum ShipType {
        AIRCRAFTCARRIER("AircraftCarrier", 5), BATTLESHIP("Battleship", 4), CRUISER("Cruiser", 3),
            DESTROYER("Destroyer", 2), SUBMARINE("Submarine", 2), NONE("None", 0);
            private final String name;
            private final int length;
            private ShipType(String name, int length){
                this.name = name;
                this.length = length;
            }
            
            
            @Override
            public String toString(){
                return name;
            }
    }
    
    public enum ShipOrientation {
        HORIZONTAL, VERTICAL
    }
    
    public enum Owner {
        PLAYER, AI
    }

    // User's ships:
    Ship aircraftCarrier;
    Ship battleship;
    Ship cruiser;
    Ship destroyer;
    Ship submarine;

    // AI's ships:
    Ship computer_aircraftCarrier;
    Ship computer_battleship;
    Ship computer_cruiser;
    Ship computer_destroyer;
    Ship computer_submarine;

    /* Game stats: Can be populated as 2D array like {{x, y}, ...}, or can
    simply hold a list of relevant GridSquare objects */
    int[] playerHits;
    int[] playerMisses;
    int[] computerHits;
    int[] computerMisses;
    
    Board board;

    // Constructor: Creates each ship and initializes their names and lengths
    BattleshipModel() {
        this.aircraftCarrier = new Ship("AircraftCarrier", 5);
        this.battleship = new Ship("Battleship", 4);
        this.cruiser = new Ship("Cruiser", 3);
        this.destroyer = new Ship("Destroyer", 2);
        this.submarine = new Ship("Submarine", 2);

        this.computer_aircraftCarrier = new Ship("Computer_AircraftCarrier", 5);
        this.computer_battleship = new Ship("Computer_Battleship", 4);
        this.computer_cruiser = new Ship("Computer_Cruiser", 3);
        this.computer_destroyer = new Ship("Computer_Destroyer", 2);
        this.computer_submarine = new Ship("Computer_Submarine", 2);

        this.playerHits = new int[100];
        this.playerMisses = new int[100];
        this.computerHits = new int[100];
        this.computerMisses = new int[100];
        
        this.board = new Board();
    }
    
    public Board getBoard(){
        return this.board;
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
}
