package application.jeuxDeLaVie;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private static boolean execution;
    private static boolean cliqueDroit;

    public static Timeline actualisation;
    public static int[][] infoPlateau;
    
    public static int taille = 50;

    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            //taille scene TODO
            Scene scene = new Scene(root,950,950);

            //fond:
            root.setBackground(new Background(new BackgroundFill(Color.rgb(25, 58, 77), null, null)));

            //plateau:
            GridPane plateau = new GridPane();
            plateau.setVgap(1);
            plateau.setHgap(1);
            // h  x  b  g
            plateau.setPadding(new Insets(10,10,0,10));
            root.getChildren().add(plateau);
            
            


            Case[][] plateauTab = new Case[taille][taille];
            infoPlateau = new int[taille][taille];

            for(int i = 0 ; i < taille ; i++){
                for(int j = 0 ; j < taille ; j++){
                    plateauTab[i][j] = new Case(i, j);
                    plateau.add(plateauTab[i][j], i, j);
                    plateauTab[i][j].HProperty().bind(scene.heightProperty().subtract((taille + 1) + 100).divide(taille));
                }
            }






            //gestion des règlages du jeu:
            Text texte1 = new Text("Nombre de voisins pour survivre:");
            Text texte2 = new Text("Nombre de voisins pour naître:");
            texte2.setFill(Color.rgb(255,255,255));
            texte1.setFill(Color.rgb(255,255,255));
            HBox boite1 = new HBox();
            boite1.getChildren().addAll(texte1);
            boite1.setPrefHeight(20);
            boite1.setSpacing(30);
            boite1.setPadding(new Insets(0,10,0,10));
            root.getChildren().add(boite1);

            HBox boite2 = new HBox();
            boite2.setPrefHeight(30);
            boite2.setSpacing(30);
            FlowPane boite21 = new FlowPane();
            FlowPane boite22 = new FlowPane();
            boite2.getChildren().addAll(texte2, boite22);
            boite1.getChildren().addAll(boite21);
            boite2.setPadding(new Insets(0,10,0,10));
            root.getChildren().add(boite2);

            //stockage des valeurs:
            CheckBox[] tabsurvie = new CheckBox[9];
            for(int i=0; i < 9 ; i++){
                tabsurvie[i] = new CheckBox(String.valueOf(i));
                tabsurvie[i].setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 14));
                tabsurvie[i].setTextFill(Color.WHITE);
                boite21.getChildren().add(tabsurvie[i]);
            }
            tabsurvie[2].setSelected(true);
            tabsurvie[3].setSelected(true);


            CheckBox[] tabnaissance = new CheckBox[9];
            for(int i=0; i < 9 ; i++){
                tabnaissance[i] = new CheckBox(String.valueOf(i));
                tabnaissance[i].setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 14));
                tabnaissance[i].setTextFill(Color.WHITE);
                boite22.getChildren().add(tabnaissance[i]);
            }
            tabnaissance[3].setSelected(true);


            HBox boite3 = new HBox();
            //bouton play pause:
            
            File play = new File("E:/icon/play.png");
            String localUrlPlay = play.toURI().toURL().toString();
            Image imagePlay = new Image(localUrlPlay);
            final ImageView iconPlay = new ImageView(imagePlay);
            
            File pause = new File("E:/icon/pause.png");
            String localUrlPause = pause.toURI().toURL().toString();
            Image imagePause = new Image(localUrlPause);
            final ImageView iconPause = new ImageView(imagePause);

            Button bouton = new Button();
            //final Image image = new Image(getClass().getResource("Ok.png").toExternalForm());  
            bouton.setGraphic(iconPlay);
            bouton.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), null, null)));
            boite3.getChildren().add(bouton);
            root.getChildren().addAll(boite3);
            bouton.setPrefSize(50, 50);

            execution = false;

            bouton.setOnAction(e ->{
                if(!execution){
                    //lancement animation
                	bouton.setGraphic(iconPause);
                    execution = true;

                }else{
                    //arrêt animation
                	bouton.setGraphic(iconPlay);
                    execution = false;

                }
                
            });


            //bouton clear:
            Button boutonEff = new Button("Effacer");
            boite3.getChildren().add(0,boutonEff);
            boutonEff.setPrefSize(200, 50);
            boutonEff.setOnAction(e ->{
                for(int i = 0 ; i < taille ; i++){
                    for(int j = 0 ; j < taille ; j++){
                        infoPlateau[i][j] = 0;
                        plateauTab[i][j].deces();
                    }
                }
            });



            //slider vitesse:
            Slider slider = new Slider();
            slider.setMin(1);
            slider.setMax(459);
            boite3.getChildren().add(slider);
            slider.setPrefSize(300, 50);
            slider.setValue(400);







            //gestion layout:
            primaryStage.heightProperty().addListener(e -> {
                primaryStage.setWidth(primaryStage.getHeight() - 100);
            });

            primaryStage.widthProperty().addListener(e -> {
                primaryStage.setHeight(primaryStage.getWidth() + 100);
                texte1.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 50));
                texte2.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 50));
                bouton.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 50));
                boutonEff.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 55));
            });





            //rafraichissement de l'image:

            Calculateur calculs = new Calculateur(infoPlateau, plateauTab, tabsurvie, tabnaissance);
            calculs.start();

            actualisation = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>(){
                public void handle(ActionEvent arg0) {



                    //rafraichissement:
//					System.out.println("refresh");
                    for(int i = 0 ; i < taille ; i++){
                        for(int j = 0 ; j < taille ; j++){
                            if(infoPlateau[i][j] == 1 && !plateauTab[i][j].isOccupee()){
                                plateauTab[i][j].naissance();
                            }
                            if(infoPlateau[i][j] == 0 && plateauTab[i][j].isOccupee()){
                                plateauTab[i][j].deces();
                            }
                        }
                    }


                    synchronized(calculs){
                        calculs.notify();
                    }


                }
            }));
            actualisation.setCycleCount(Timeline.INDEFINITE);
            actualisation.play();



            slider.valueProperty().addListener(e->{
                actualisation.stop();

                KeyFrame key = new KeyFrame(Duration.millis(500-slider.getValue()), new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent arg0) {



                        //rafraichissement:
//						System.out.println("refresh");
                        for(int i = 0 ; i < taille ; i++){
                            for(int j = 0 ; j < taille ; j++){
                                if(infoPlateau[i][j] == 1 && !plateauTab[i][j].isOccupee()){
                                    plateauTab[i][j].naissance();
                                }
                                if(infoPlateau[i][j] == 0 && plateauTab[i][j].isOccupee()){
                                    plateauTab[i][j].deces();
                                }
                            }
                        }


                        synchronized(calculs){
                            calculs.notify();
                        }


                    }
                });

                actualisation.getKeyFrames().setAll(key);
                actualisation.play();

            });




            primaryStage.setTitle("Sae jeu de la vie");
            primaryStage.setScene(scene);
            primaryStage.show();


            primaryStage.setOnCloseRequest(e ->{
                calculs.interrupt();
                Platform.exit();

            });




            //souris:
            cliqueDroit = false;
            scene.setOnMouseClicked(e ->{
                if(e.getButton().equals(MouseButton.SECONDARY)){
                    if(cliqueDroit)
                        cliqueDroit = false;
                    else
                        cliqueDroit = true;
                }
            });


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static boolean isInExecution() {
        return execution;
    }

    public static boolean cliqueDroit() {
        return cliqueDroit;
    }
    
    }
