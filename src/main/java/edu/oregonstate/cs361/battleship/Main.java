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

        //Proof of concept: place aircraft carrier on the board.
        modelObj.computer_aircraftCarrier.start.Across = 0;
        modelObj.computer_aircraftCarrier.start.Down= 0;
        modelObj.computer_aircraftCarrier.end.Across = 4;
        modelObj.computer_aircraftCarrier.end.Down = 0;


        //Convert to JSON object and to string
        String model = new String(gson.toJson(modelObj));

        //Return the model in string format (GSON needed)
        return model;

    }

    //This function should accept an HTTP request and deseralize it into an actual Java object.
    private static BattleshipModel getModelFromReq(Request req){
        return null;
    }

    //This controller should take a json object from the front end, and place the ship as requested, and then return the object.
    private static String placeShip(Request req) {
        return "SHIP";
    }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {
        return null;
    }

}