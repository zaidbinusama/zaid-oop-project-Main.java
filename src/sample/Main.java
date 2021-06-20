package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

import java.util.Random;
//ZAID BIN USAMA BHATTI 356844 BSCS 10C
//MAIN CLASS
public class Main extends Application {
    // start method
    @Override
    public void start(Stage primaryStage) throws Exception {

        // setting name for main stage
        primaryStage.setTitle("LOOPS");

        //setting up main pane
        Pane pane = new Pane();

        //initializing random index to place apple
        Random rand = new Random();
        int rockIndex = rand.nextInt(4) + 1;

        //initialising pane for selection of difficulty and entering of name
        FlowPane nameDifficulty = new FlowPane();
        nameDifficulty.setPadding(new Insets(11, 12, 13, 14));

        //Adding labels and text field
        nameDifficulty.getChildren().add(new Label("Enter your name, then click on any of the three difficulties too proceed"));
        TextField nameField = new TextField();

        //initializing buttons to select difficulty
        Button easy = new Button("EASY");
        Button medium = new Button("MEDIUM");
        Button difficult = new Button("DIFFICULT");


        //setting H and V gap
        nameDifficulty.setHgap(10);
        nameDifficulty.setVgap(20);

        //adding buttons to pane + initializing scene and stage for name panel
        nameDifficulty.getChildren().addAll(nameField, easy, medium, difficult);

        Scene nameScene = new Scene(nameDifficulty);

        Stage nameStage = new Stage();

        //setting scene
        nameStage.setScene(nameScene);

        //initializing arrays to contain value of the name of the player, difficulty, array for rock images and the index for the rock array
        String[] names = new String[10];
        String[] difficulties = new String[10];
        int[] rockIndexArray = new int[10];
        ImageView[] rocks = new ImageView[6];


        //initialising Image objects
        Image rock = new Image("output-onlinepngtools (2).png", 400, 200, false, false);
        Image apple = new Image("output-onlinepngtools (3).png", 100, 100, false, false);
        Image boy = new Image("output-onlinepngtools.png");
        Image water = new Image("output-onlinepngtools (1).png", 1920, 850, false, false);

        //initialising file writer, buffered writer and print writer objects
        FileWriter myWriter = new FileWriter("src\\highscores.txt", true);
        BufferedWriter BufferedWriter = new BufferedWriter(myWriter);
        PrintWriter pw = new PrintWriter(BufferedWriter);


        //initialising ImageView objects using Image objects
        ImageView waterImage = new ImageView(water);
        ImageView appleImage = new ImageView(apple);
        ImageView boyImage = new ImageView(boy);
        ImageView rock1 = new ImageView(rock);
        ImageView rock2 = new ImageView(rock);
        ImageView rock3 = new ImageView(rock);
        ImageView rock4 = new ImageView(rock);
        ImageView rock5 = new ImageView(rock);
        ImageView rock6 = new ImageView(rock);

        //adding rock images to array
        rocks[0] = rock1;
        rocks[1] = rock2;
        rocks[2] = rock3;
        rocks[3] = rock4;
        rocks[4] = rock5;
        rocks[5] = rock6;

        //setting coordinates for rock images
        rocks[0].setY(450);
        rocks[0].setX(0);

        rocks[1].setY(450);
        rocks[1].setX(250);

        rocks[2].setY(450);
        rocks[2].setX(500);

        rocks[3].setY(450);
        rocks[3].setX(750);

        rocks[4].setY(450);
        rocks[4].setX(1000);

        rocks[5].setY(450);
        rocks[5].setX(1250);

        // setting coordinates for apple image and water image
        appleImage.setX(rocks[rockIndex].getX() + 150);
        appleImage.setY(rocks[rockIndex].getY() - 70);
        waterImage.setY(10);


        // creating pane to enter the number of steps to reach the apple
        FlowPane td = new FlowPane();
        td.setPadding(new Insets(11, 12, 13, 14));
        td.getChildren().add(new Label("Enter the number of steps to reach the apple"));
        td.setHgap(15);
        td.setVgap(20);
        TextField input = new TextField();
        td.getChildren().add(input);

        //adding ok button to input dialogue
        Button ok = new Button("OK");
        td.getChildren().add(ok);

        //int for storing inputted number of steps
        int[] number = new int[1];


        // int and char arrays to store number of tries and the users grade
        int[] tries = new int[10];
        tries[0] = 1;
        char[] grades = new char[10];


        //Creating event handler for OK BUTTON in steps input pane
        EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //checks to see if the ok button is pressed by the user using a mouse
                if (mouseEvent.getSource() == ok) {

                    //value of text field stored in String variable
                    String userInput = String.valueOf(input.getCharacters());

                    //integer value of text field stored in as first value of number array
                    number[0] = Integer.valueOf(userInput);

                    //iterable initialised
                    int i = 0;

                    //LOOP to move the boyImage from rock to rock for the number of steps specified
                    for (; i < number[0] + 1; i++) {
                        boyImage.setX(rocks[i].getX());
                        primaryStage.show();


                    }
                    //checking difficulty
                    if (difficulties[0].equals("DIFFICULT")) {

                        //checking to see if the input number of steps towards the apple is correct
                        //if this condition is satisfied then the user wins and gets a grade
                        if (6 - i == rockIndexArray[0]) {

                            //removing apple image and setting the boy images x value to omit any possible glitches
                            pane.getChildren().remove(appleImage);
                            boyImage.setX(rocks[rockIndexArray[0]].getX());

                            //conditions on which to give grade
                            if (tries[0] == 1) {
                                grades[0] = 'A';
                            }
                            if (tries[0] == 2) {
                                grades[0] = 'B';
                            }
                            if (tries[0] == 3) {
                                grades[0] = 'C';
                            }

                            //text object for winning message
                            Text youWon = new Text("YOU WON! YOU GOT IT RIGHT IN " + tries[0] + " TRY(S)");

                            //Setting font to the text
                            youWon.setFont(new Font(40));

                            //Setting color to the text
                            youWon.setFill(Color.GOLD);

                            //text object for grade message
                            Text yourGrade = new Text("Your Grade Is " + " " + grades[0]);

                            //Setting font to the text
                            yourGrade.setFont(new Font(40));

                            //Setting color to the text
                            yourGrade.setFill(Color.BLUEVIOLET);

                            //pane for winning message
                            FlowPane win = new FlowPane();
                            win.setPadding(new Insets(11, 12, 13, 14));

                            //adding text objects to pane
                            win.getChildren().add(youWon);
                            win.getChildren().add(yourGrade);

                            //setting stage and scene from pane
                            Stage smallStage2 = new Stage();
                            smallStage2.setScene(new Scene(win));
                            smallStage2.show();

                            //using print writer to print names, grades, and difficulties AND store them in a file
                            pw.println(names[0] + "\t" + grades[0] + "\t" + difficulties[0]);
                            System.out.println("Data Successfully appended into file");
                            pw.flush();


                        }

                        //this code block will execute if the user does not win
                        else {
                            //code to check if number of tries is exhausted
                            if (tries[0] > 2) {

                                //text object to tell user has failed
                                Text fail = new Text("YOU FAILED");
                                //setting font
                                fail.setFont(new Font(40));
                                //setting text color
                                fail.setFill(Color.RED);

                                //setting grade value
                                grades[0] = 'F';

                                //text object to tell the user their grade
                                Text yourGrade = new Text("Your Grade Is " + " " + grades[0]);

                                //Setting font to the text
                                yourGrade.setFont(new Font(40));

                                //Setting color to the text
                                yourGrade.setFill(Color.BLUEVIOLET);

                                //using print writer to print names, grades, and difficulties AND store them in a file
                                pw.println(names[0] + "\t" + grades[0] + "\t" + difficulties[0]);
                                System.out.println("Data Successfully appended into file");
                                pw.flush();

                                //pane to which messgaes will be added
                                FlowPane failPane = new FlowPane();
                                failPane.setPadding(new Insets(11, 12, 13, 14));

                                //adding messages to pane
                                failPane.getChildren().addAll(fail, yourGrade);
                                primaryStage.setScene(new Scene(failPane));

                            }
                            //this block will execute if the player has not won but still has some tries left
                            else {
                                //value of tries[0] is incremented
                                tries[0] += 1;

                                //boyImages x value is reset
                                boyImage.setX(rocks[5].getX());

                                //text object to tell the user that their move was incorrect
                                Text wrongMove = new Text("WRONG MOVE!");

                                //Setting font to the text
                                wrongMove.setFont(new Font(40));

                                //Setting color to the text
                                wrongMove.setFill(Color.RED);

                                //new int variable
                                int triesRemaining = 3 - tries[0];

                                //new text object to tell the user about the number of tries they have remaining
                                Text triesLeft = new Text("You have " + (int) (triesRemaining + 1) + " tries remaining");

                                //Setting font to the text
                                triesLeft.setFont(new Font(40));

                                //Setting color to the text
                                triesLeft.setFill(Color.BLUEVIOLET);

                                //flow pane object to hold text objects
                                FlowPane wrongMovePane = new FlowPane();
                                wrongMovePane.setPadding(new Insets(11, 12, 13, 14));


                                wrongMovePane.getChildren().addAll(wrongMove, triesLeft);

                                Stage smallStage = new Stage();
                                smallStage.setScene(new Scene(wrongMovePane));
                                smallStage.show();
                            }

                        }
                    }
                    //checking if difficulty is medium
                    else if (difficulties[0].equals("MEDIUM")) {

                        //condition to check if number of steps input is correct
                        if (5 - i == rockIndexArray[0]) {
                            pane.getChildren().remove(appleImage);
                            boyImage.setX(rocks[rockIndexArray[0]].getX());


                            //condition for giving student grade based on number of tries
                            if (tries[0] == 1) {
                                grades[0] = 'A';
                            }
                            if (tries[0] == 2) {
                                grades[0] = 'B';
                            }
                            if (tries[0] == 3) {
                                grades[0] = 'C';
                            }

                            //text object to give winning messgae
                            Text youWon = new Text("YOU WON! YOU GOT IT RIGHT IN " + tries[0] + " TRY(S)");

                            //Setting font to the text
                            youWon.setFont(new Font(40));

                            //Setting color to the text
                            youWon.setFill(Color.GOLD);

                            //text object to give grade
                            Text yourGrade = new Text("Your Grade Is " + " " + grades[0]);

                            //Setting font to the text
                            yourGrade.setFont(new Font(40));

                            //Setting color to the text
                            yourGrade.setFill(Color.BLUEVIOLET);

                            //flow pane to hold text objects
                            FlowPane win = new FlowPane();
                            win.setPadding(new Insets(11, 12, 13, 14));


                            //adding text objects to flow pane
                            win.getChildren().add(youWon);
                            win.getChildren().add(yourGrade);

                            //initialising stage, scene and adding the flow pane to them
                            Stage smallStage2 = new Stage();
                            smallStage2.setScene(new Scene(win));
                            smallStage2.show();

                            //using print writer to append scores, names and difficulty to text file
                            pw.println(names[0] + "\t" + grades[0] + "\t" + difficulties[0]);
                            System.out.println("Data Successfully appended into file");
                            pw.flush();


                        }
                        // this will execute if the user has not won yet
                        else {
                            //this will check if the user has any tries left
                            if (tries[0] > 2) {

                                //text object for failure message along with setting its font and color
                                Text fail = new Text("YOU FAILED");
                                fail.setFont(new Font(40));
                                fail.setFill(Color.RED);

                                //setting grade
                                grades[0] = 'F';

                                //text object to give grade
                                Text yourGrade = new Text("Your Grade Is " + " " + grades[0]);

                                //Setting font to the text
                                yourGrade.setFont(new Font(40));

                                //Setting color to the text
                                yourGrade.setFill(Color.BLUEVIOLET);

                                // print writer to write name
                                pw.println(names[0] + "\t" + grades[0] + "\t" + difficulties[0]);
                                System.out.println("Data Successfully appended into file");
                                pw.flush();

                                // flow pane to hold text objects
                                FlowPane failPane = new FlowPane();
                                failPane.setPadding(new Insets(11, 12, 13, 14));

                                //adding text objects to pane
                                failPane.getChildren().addAll(fail, yourGrade);
                                primaryStage.setScene(new Scene(failPane));

                            }
                            //this will execute if the user has some tries remaining
                            else
                            {
                                //tries[0] is incremented
                                tries[0] += 1;

                                //boyImages' position is reset
                                boyImage.setX(rocks[4].getX());

                                //text object to tell the user that their move was wrong
                                Text wrongMove = new Text("WRONG MOVE!");

                                //Setting font to the text
                                wrongMove.setFont(new Font(40));

                                //Setting color to the text
                                wrongMove.setFill(Color.RED);
                                int triesRemaining = 3 - tries[0];

                                //text object to tell the user about the number of tries they have left
                                Text triesLeft = new Text("You have " + (int) (triesRemaining + 1) + " tries remaining");

                                //Setting font to the text
                                triesLeft.setFont(new Font(40));

                                //Setting color to the text
                                triesLeft.setFill(Color.BLUEVIOLET);

                                //flow pane for holding text objects
                                FlowPane wrongMovePane = new FlowPane();
                                wrongMovePane.setPadding(new Insets(11, 12, 13, 14));

                                //adding text objects to flow pane
                                wrongMovePane.getChildren().addAll(wrongMove, triesLeft);

                                Stage smallStage = new Stage();
                                smallStage.setScene(new Scene(wrongMovePane));
                                smallStage.show();
                            }

                        }
                    }
                    //check to see if difficulty is easy
                    else if (difficulties[0].equals("EASY")) {
                        //condition for winning
                        if (4 - i == rockIndexArray[0]) {
                            pane.getChildren().remove(appleImage);
                            boyImage.setX(rocks[rockIndexArray[0]].getX());

                            //grade values assigned
                            if (tries[0] == 1) {
                                grades[0] = 'A';
                            }
                            if (tries[0] == 2) {
                                grades[0] = 'B';
                            }
                            if (tries[0] == 3) {
                                grades[0] = 'C';
                            }

                            //text object for winning message
                            Text youWon = new Text("YOU WON! YOU GOT IT RIGHT IN " + tries[0] + " TRY(S)");

                            //Setting font to the text
                            youWon.setFont(new Font(40));

                            //Setting color to the text
                            youWon.setFill(Color.GOLD);

                            //text object gor grade message
                            Text yourGrade = new Text("Your Grade Is " + " " + grades[0]);

                            //Setting font to the text
                            yourGrade.setFont(new Font(40));

                            //Setting color to the text
                            yourGrade.setFill(Color.BLUEVIOLET);

                            //init flow pane to hold text objects
                            FlowPane win = new FlowPane();
                            win.setPadding(new Insets(11, 12, 13, 14));

                            //add text objects to pane
                            win.getChildren().add(youWon);
                            win.getChildren().add(yourGrade);

                            //init stage and scene to hold pane
                            Stage smallStage2 = new Stage();
                            smallStage2.setScene(new Scene(win));
                            smallStage2.show();

                            //using print writer to append file
                            pw.println(names[0] + "\t" + grades[0] + "\t" + difficulties[0]);
                            System.out.println("Data Successfully appended into file");
                            pw.flush();


                        }
                        //this will execute if the user does not win i.e. made the wrong move
                        else
                        {
                            //check to see if user has any tries left
                            if (tries[0] > 2) {
                                Text fail = new Text("YOU FAILED");
                                fail.setFont(new Font(40));
                                fail.setFill(Color.RED);

                                //set grade value
                                grades[0] = 'F';

                                //text object to to tell user their graves
                                Text yourGrade = new Text("Your Grade Is " + " " + grades[0]);

                                //Setting font to the text
                                yourGrade.setFont(new Font(40));

                                //Setting color to the text
                                yourGrade.setFill(Color.BLUEVIOLET);

                                //using print writer to append file
                                pw.println(names[0] + "\t" + grades[0] + "\t" + difficulties[0]);
                                System.out.println("Data Successfully appended into file");
                                pw.flush();


                                FlowPane failPane = new FlowPane();
                                failPane.setPadding(new Insets(11, 12, 13, 14));

                                failPane.getChildren().addAll(fail, yourGrade);
                                primaryStage.setScene(new Scene(failPane));

                            }
                            //this will execute if the user has tries left and did not make the right move
                            else {
                                tries[0] += 1;

                                //boy image position reset
                                boyImage.setX(rocks[3].getX());

                                //text object to tell user they made the wrong move
                                Text wrongMove = new Text("WRONG MOVE!");

                                //Setting font to the text
                                wrongMove.setFont(new Font(40));

                                //Setting color to the text
                                wrongMove.setFill(Color.RED);

                                int triesRemaining = 3 - tries[0];

                                //text object to tell user how many tries they have left
                                Text triesLeft = new Text("You have " + (int) (triesRemaining + 1) + " tries remaining");

                                //Setting font to the text
                                triesLeft.setFont(new Font(40));

                                //Setting color to the text
                                triesLeft.setFill(Color.BLUEVIOLET);

                                //flow pane to hold the text objects
                                FlowPane wrongMovePane = new FlowPane();
                                wrongMovePane.setPadding(new Insets(11, 12, 13, 14));

                                //adding text objects to flow pane
                                wrongMovePane.getChildren().addAll(wrongMove, triesLeft);


                                Stage smallStage = new Stage();
                                smallStage.setScene(new Scene(wrongMovePane));
                                smallStage.show();
                            }
                        }
                    }

                }
            }


        };
        //adding event filter to ok button in window in which user will input the no. of steps
        ok.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler);

        //event handler for user choosing difficulty and inputting name
        EventHandler<MouseEvent> mouseEventEventHandler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //checks to see if button pressed is the easy button
                if (mouseEvent.getSource() == easy) {

                    //getting name from text field and storing it in a variable
                    names[0] = String.valueOf(nameField.getCharacters());
                    // setting difficulty for appending to file
                    difficulties[0] = "EASY";

                    //decreasing range of random int rock index due to difficulty
                    int rockIndex = rand.nextInt(2) + 1;
                    rockIndexArray[0] = rockIndex;

                    //removing 2 rocks to decrease difficulty
                    pane.getChildren().removeAll(rocks[5], rocks[4]);

                    //setting new positions for boy and apple
                    boyImage.setX(rocks[3].getX());
                    appleImage.setX(rocks[rockIndex].getX() + 150);
                }
                //checks to see if button pressed is the medium button
                if (mouseEvent.getSource() == medium) {

                    //getting name from text field and storing it in a variable
                    names[0] = String.valueOf(nameField.getCharacters());

                    // setting difficulty for appending to file
                    difficulties[0] = "MEDIUM";

                    //decreasing range of random int rock index due to difficulty
                    int rockIndex = rand.nextInt(3) + 1;

                    //removing 1 rock to decrease difficulty
                    pane.getChildren().removeAll(rocks[5]);
                    rockIndexArray[0] = rockIndex;

                    //setting new positions for boy and apple
                    boyImage.setX(rocks[4].getX());
                    appleImage.setX(rocks[rockIndex].getX() + 150);
                }

                //checks to see if button pressed is the difficult button
                if (mouseEvent.getSource() == difficult) {

                    //getting name from text field and storing it in a variable
                    names[0] = String.valueOf(nameField.getCharacters());

                    // setting difficulty for appending to file
                    difficulties[0] = "DIFFICULT";

                    //new random position for apple, number of rocks and position of boy will remain the same
                    int rockIndex = rand.nextInt(4) + 1;
                    rockIndexArray[0] = rockIndex;
                    boyImage.setX(rocks[5].getX());
                    appleImage.setX(rocks[rockIndex].getX() + 150);
                }
                //name and difficulty stage will close
                nameStage.close();

            }
        };

        //event filters for easy, medium, and hard buttons
        easy.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler2);
        medium.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler2);
        difficult.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventEventHandler2);


        // setting initial coordinates for boy image
        boyImage.setX(1250);
        boyImage.setY(200);


        //adding nodes to main pane
        pane.getChildren().add(rocks[0]);
        pane.getChildren().add(rocks[1]);
        pane.getChildren().add(rocks[2]);
        pane.getChildren().add(rocks[3]);
        pane.getChildren().add(rocks[4]);
        pane.getChildren().add(rocks[5]);
        pane.getChildren().add(waterImage);
        pane.getChildren().add(boyImage);
        pane.getChildren().add(appleImage);

        //initializing scene and stage
        Scene scene = new Scene(pane);
        scene.setFill(Color.YELLOW);
        primaryStage.setScene(scene);

        //creating second stage and scene for number input
        Stage numbers = new Stage();
        numbers.setTitle("INPUT WINDOW");
        Scene num = new Scene(td);
        numbers.setScene(num);

        primaryStage.setMaximized(true);


        primaryStage.show();

        numbers.show();

        nameStage.show();
    }


}

