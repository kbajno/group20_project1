package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import spark.Request;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.util.Random;                    //For random placement of AI ships

public class Main {

    public static void main(String[] args) {
        //This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");

        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req));
    }

    //This function should return a new model

    /* Modified by aoleson on 1/30/17.
     * aoleson (1/30/17): This function is called when the page loads. It creates a new model of the game,
     * randomly places the AI's ships on the board, and  stringifies the model with GSON and sends it back.
     */
    static String newModel() {

        //Instantiate model, GSON and random objects
        BattleshipModel modelObj = new BattleshipModel();
        Gson gson = new Gson();
        Random rand = new Random();
        int grid_max = 9;
        int grid_min = 0;

        //Proof of concept: Randomly place aircraftCarrier
        int across = rand.nextInt((5 - grid_min) + 1) + grid_min;
        int down = rand.nextInt((5 - grid_min) + 1) + grid_min;
        //System.out.println(across);
        //System.out.println(down);
        int orientation = rand.nextInt((1 - 0) + 1) + 0;
        //System.out.println(orientation);
        modelObj.computer_aircraftCarrier.start.Across = across;
        modelObj.computer_aircraftCarrier.start.Down = down;
        if (orientation == 0) {         //0 is horizontal
            modelObj.computer_aircraftCarrier.end.Across = across + 4;
            modelObj.computer_aircraftCarrier.end.Down = down;
        } else {          //It was 1, which means vertical
            modelObj.computer_aircraftCarrier.end.Across = across;
            modelObj.computer_aircraftCarrier.end.Down = down + 4;
        }


        //Convert to JSON object and to string
        String model = new String(gson.toJson(modelObj));

        //Confirm working (will comment out later)
        //System.out.println(model);

        //Return the model in string format (GSON needed)
        return model;

        //return "MODEL";
    }

    //This function should accept an HTTP request and deseralize it into an actual Java object.
    private static BattleshipModel getModelFromReq(Request req) {
        return null;
    }

    //This controller should take a json object from the front end, and place the ship as requested, and then return the object.
    private static String placeShip(Request req) {
        return "SHIP";
    }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req)
    {
        //code creates turns and uses the different methods to fire
        int win = 0;
        int turn = 0;
        int horizontal = 0;
        int vertical = 0;
        Random num = new Random();
        //while loop that will determine who's turn it is and what to do during their turn
        while(win == 0){
            if (turn == 0){ //this is the user's turn

                turn++;
            } else{ //this is the AI's turn
                horizontal = (num.nextInt(10)) + 1;
                vertical = (num.nextInt(10)) + 1;

                turn--;
            }
            win = 1;
        }
        return null;
    }


}