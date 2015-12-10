/*
 * Prototype client
 * Currently in phase between text based and GUI
 * Lots of placeholders and stuff is bork. Need to link to GUI
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientMain {
    
      // IO streams
    
    static DataOutputStream toServer;
    static DataInputStream fromServer;
    
    static int gameID, plantType;
    static String name, plantName, playerAction;
    Game game;

    public static void main(String[] args)
    {
        
        int choice, gameNum, temp;
        String ipA = "";
          
        gameID = 0;
        Scanner in = new Scanner(System.in);
        
        try {
            Socket socket = new Socket(ipA, 8000);

            fromServer = new DataInputStream(socket.getInputStream());

            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
        }
 
        while(true){
            printMenu();
            System.out.print("Enter Choice: ");
            choice = in.nextInt();

            switch(choice)
            {
                // cases for each menu option
                case 1: displayGames();
                        System.out.print("Which game to enter?(pick number): ");
                        gameNum = in.nextInt();
                        temp = joinGame(gameNum);
                        if(temp > 0)
                        {
                            gameID = temp;
                            //startGame(); placeholder
                        }
            }
        }
    }
    
    static void printMenu()
    {
        System.out.println("\n1. Enter Game");
    }
    
    static void displayGames()
    {
        String gameList[] = getGames().split("|");
        
        System.out.print("\n\n");
        System.out.println("Game List");
        System.out.println("---------\n");
        
        for(int i = 0; i < gameList.length; i ++)
            System.out.print(i + 1 + ". " + gameList[i]);
    }
    
    static String getGames()
    {
        // query server for game list
        return "";
    }
    
    static int joinGame(int gameNum) //make thread
    {
        // attempts to join game on server
        // return 0 if not possible
        return 0;
    }
    
    static void exitGame()
    {
        
    }
    
    static String buildPlayerInfo()
    {
        return gameID + "|" + plantName + "|" + plantType;
    }
    
    static String buildTurnInfo()
    {
        return gameID + "|" + playerAction;
    }
    
    static String[][] parseTurnInfo(String turnInfo)
    {
        // 2nd cell consists of plant name, plant size and plant growth
        // can send resource info as well if need be
        // player specific info is delimited by a new line, palnt specifics by   "|"
        String[][]playerTurnInfo = new String[4][3];
        
        String[] playerTurnRawInfo = turnInfo.split("\n");

        for(int i = 0; i < 4; i++)
        {
           playerTurnInfo[i] = playerTurnRawInfo[i].split("|");
        }
        
        return playerTurnInfo;
    }
    
    private class GetInfo implements Runnable {
        GetInfo(){}
        
        public void run() {
        game.getGameInfo();
        }
    }
    
    private class Game implements Runnable{ 
        
        String[][] turnInfo;
        String plantInfo;
        Thread infoThread;

        Game()
        {
            plantInfo = "";
            infoThread = new Thread(new GetInfo());
        }// pass parent to game
        
        @Override
        public void run()
        {
            // switch window
            
            // create player
            createPlayer();

            // get game info
                      // Create and start a new thread for the connection
            infoThread .start();    
            // display/update info
            
            // not needed for gui
            while(true) // make bool gameOver
            {   
                // make move
                buildPlayerInfo();
                
                // get game info - thread
                
                // display/update info
                
                // increment round counter
                
                // check for completion
            }
        }
        
        void createPlayer()
        {
            // go to page for character creation
            // sets up name, plant name and plant type
            // pulls data from text fields
            buildPlayerInfo();
        }

        public void getGameInfo() {
            
            String temp = "";
            while(true)
            {
                try {
                    temp = fromServer.readUTF();
                    turnInfo = parseTurnInfo(temp);
                    }
                catch (IOException ex) {
                }
            }
            
            // parse string
            // go to next section
        }
    }    
}




