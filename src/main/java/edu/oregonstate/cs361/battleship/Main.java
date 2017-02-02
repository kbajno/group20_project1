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

    /* Modified by aoleson on 2/1/17.
     * aoleson (1/30/17): This function is called when the page loads. It creates a new model of the game,
     * randomly places the AI's ships on the board, and  stringifies the model with GSON and sends it back.
     */
    static String newModel() {

        //Instantiate model, GSON and random objects plus needed vars
        BattleshipModel modelObj = new BattleshipModel();
        Gson gson = new Gson();
        Random rand = new Random();
        int grid_min = 1;
        int grid_max = 10;

        //Get random starting coordinates for each ship, making sure they're not the same
        int air_across = rand.nextInt((6 - grid_min) + 1) + grid_min;       //Get random int in [1,6] (inclusive)
        int air_down = rand.nextInt((10 - grid_min) + 1) + grid_min;

        int bat_across = rand.nextInt((7 - grid_min) + 1) + grid_min;
        int bat_down = rand.nextInt((10 - grid_min) + 1) + grid_min;
        while (bat_down == air_down)                                                //If it's the same as one of the others, reroll random int
            bat_down = rand.nextInt((10 - grid_min) + 1) + grid_min;

        int cru_across = rand.nextInt((8 - grid_min) + 1) + grid_min;
        int cru_down = rand.nextInt((10 - grid_min) + 1) + grid_min;
        while (cru_down == air_down || cru_down == bat_down)
            cru_down = rand.nextInt((10 - grid_min) + 1) + grid_min;

        int des_across = rand.nextInt((9 - grid_min) + 1) + grid_min;
        int des_down = rand.nextInt((10 - grid_min) + 1) + grid_min;
        while (des_down == air_down || des_down == bat_down || des_down == cru_down)
            des_down = rand.nextInt((10 - grid_min) + 1) + grid_min;

        int sub_across = rand.nextInt((9 - grid_min) + 1) + grid_min;
        int sub_down = rand.nextInt((10 - grid_min) + 1) + grid_min;
        while (sub_down == air_down || sub_down == bat_down || sub_down == cru_down || sub_down == des_down)
            sub_down = rand.nextInt((10 - grid_min) + 1) + grid_min;

        //Place aircraft carrier on the board. (length 5)
        modelObj.computer_aircraftCarrier.start.Across = air_across;
        modelObj.computer_aircraftCarrier.start.Down= air_down;
        modelObj.computer_aircraftCarrier.end.Across = (air_across + 4);
        modelObj.computer_aircraftCarrier.end.Down = air_down;

        //Place battleship on the board. (length 4)
        modelObj.computer_battleship.start.Across = bat_across;
        modelObj.computer_battleship.start.Down= bat_down;
        modelObj.computer_battleship.end.Across = (bat_across + 3);
        modelObj.computer_battleship.end.Down = bat_down;

        //Place cruiser on the board. (length 3)
        modelObj.computer_cruiser.start.Across = cru_across;
        modelObj.computer_cruiser.start.Down= cru_down;
        modelObj.computer_cruiser.end.Across = (cru_across + 2);
        modelObj.computer_cruiser.end.Down = cru_down;

        //Place destroyer on the board. (length 2)
        modelObj.computer_destroyer.start.Across = des_across;
        modelObj.computer_destroyer.start.Down= des_down;
        modelObj.computer_destroyer.end.Across = (des_across + 1);
        modelObj.computer_destroyer.end.Down = des_down;

        //Place aircraft carrier on the board. (length 2)
        modelObj.computer_submarine.start.Across = sub_across;
        modelObj.computer_submarine.start.Down= sub_down;
        modelObj.computer_submarine.end.Across = (sub_across + 1);
        modelObj.computer_submarine.end.Down = sub_down;

        //Convert to JSON object and to string
        String model = new String(gson.toJson(modelObj));

        //Return the model in string format (GSON needed)
        return model;

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
                horizontal = req.attribute("row");
                vertical = req.attribute("col");
                turn++;
            } else{ //this is the AI's turn
                horizontal = (num.nextInt(10)) + 1;
                vertical = (num.nextInt(10)) + 1;
                turn--;
            }
            
            win = 1;
        }
        return req.body();
    }


}
