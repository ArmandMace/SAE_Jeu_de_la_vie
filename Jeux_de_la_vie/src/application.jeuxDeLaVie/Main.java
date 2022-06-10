package application.jeuxDeLaVie;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
	public static int numero = 1;

	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = new HBox();
			VBox regle = new VBox();
			VBox exemple = new VBox();
			root.setAlignment(Pos.CENTER);
			regle.setAlignment(Pos.CENTER);
			exemple.setAlignment(Pos.CENTER);
			//taille scene TODO
			Scene scene = new Scene(root,1200,860);
			Scene sceneRegle = new Scene(regle,1200,860);
			Scene sceneEx = new Scene(exemple,1200,860);

			//fond:
			root.setBackground(new Background(new BackgroundFill(Color.rgb(25, 58, 77), null, null)));
			regle.setBackground(new Background(new BackgroundFill(Color.rgb(25, 58, 77), null, null)));
			exemple.setBackground(new Background(new BackgroundFill(Color.rgb(25, 58, 77), null, null)));
			
			
			//Regle
			
			HBox RT = new HBox();
			Text texteRT = new Text("Règles du jeu \r\n");
			
			HBox R1 = new HBox();
			Text texteR1 = new Text("Le jeu se joue sur une grille de\r\n"
					                + " taille variable composée de cases \r\n"
					                + "(cellules)\r\n" 
					                +"Dans le jeu de la vie, les cellules possèdent 2\r\n"
					                + " états, “vivante” ou “morte”.\r\n"
					                + "(Chaque cellule possède 8 voisins)\r\n");
			R1.setAlignment(Pos.CENTER);
			HBox R2 = new HBox();
			Text texteR2 = new Text("A chaque itération l’état d’une\r\n"
					                + " cellule dépend du nombre de\r\n"
					                + "voisins vivants\r\n");
			R2.setAlignment(Pos.CENTER);
			HBox R3 = new HBox();
			VBox RT3 = new VBox();
			Text texteR3 = new Text("Si une cellule morte possède exactement 3\r\n"
					                + " voisins vivants, elle devient vivante\r\n"
					                +"si une cellule vivante possède 2 ou 3 voisins,\r\n"
					                + " elle reste vivante, sinon elle meurt\r\n");
			Text texteR32 = new Text("Le nombre de cellules nécessaires peut être\r\n"
					                 + " modifié dans les options ainsi que la taille de\r\n"
					                 + " la grille");
			
			File IR1 = new File("E:/icon/R1.png");
			String UIR1 = IR1.toURI().toURL().toString();
			Image imageR1 = new Image(UIR1);
			final ImageView ImgR1 = new ImageView(imageR1);
			
			File IR2 = new File("E:/icon/R2.png");
			String UIR2 = IR2.toURI().toURL().toString();
			Image imageR2 = new Image(UIR2);
			final ImageView ImgR2 = new ImageView(imageR2);
			
			File IR3 = new File("E:/icon/R3.png");
			String UIR3 = IR3.toURI().toURL().toString();
			Image imageR3 = new Image(UIR3);
			final ImageView ImgR3 = new ImageView(imageR3);
			
			RT3.setAlignment(Pos.CENTER);
			R3.setAlignment(Pos.CENTER);
			RT.setAlignment(Pos.CENTER);
			
			texteR1.setStyle("-fx-font: 24 arial;");
			texteR1.setFill(Color.rgb(255,255,255));
			texteR2.setStyle("-fx-font: 24 arial;");
			texteR2.setFill(Color.rgb(255,255,255));
			texteR3.setStyle("-fx-font: 24 arial;");
			texteR3.setFill(Color.rgb(255,255,255));
			texteR32.setStyle("-fx-font: 24 arial;");
			texteR32.setFill(Color.rgb(255,255,255));
			texteRT.setStyle("-fx-font: 30 arial;");
			texteRT.setFill(Color.rgb(255,255,255));
			
			R1.getChildren().addAll(texteR1, ImgR1);
			R1.setSpacing(20);
			R2.getChildren().addAll(texteR2, ImgR2);
			R2.setSpacing(190);
			RT3.getChildren().addAll(texteR3, texteR32);
			R3.setSpacing(20);
			RT.getChildren().addAll(texteRT);
			R3.getChildren().addAll(RT3, ImgR3);
			
			regle.setSpacing(30);
			
			regle.getChildren().addAll(RT,R1,R2,R3);
			
			
			//Exemples
			
			HBox ET = new HBox();
			HBox E1 = new HBox();
			HBox E2 = new HBox();
			HBox E3 = new HBox();
			
			Text texteET = new Text("exemple de configuration\r\n");
			
			Text texteE1 = new Text("clignotant →\r\n");
			Text texteE2 = new Text("glisseur    →\r\n");
			Text texteE3 = new Text("canon       →\r\n");
			
			File IE1 = new File("E:/icon/E1.png");
			String UIE1 = IE1.toURI().toURL().toString();
			Image imageE1 = new Image(UIE1);
			final ImageView ImgE1 = new ImageView(imageE1);
			
			File IE2 = new File("E:/icon/E2.png");
			String UIE2 = IE2.toURI().toURL().toString();
			Image imageE2 = new Image(UIE2);
			final ImageView ImgE2 = new ImageView(imageE2);
			
			File IE3 = new File("E:/icon/E3.png");
			String UIE3 = IE3.toURI().toURL().toString();
			Image imageE3 = new Image(UIE3);
			final ImageView ImgE3 = new ImageView(imageE3);
			
			ET.getChildren().addAll(texteET);
			E1.getChildren().addAll(texteE1, ImgE1);
			E2.getChildren().addAll(texteE2, ImgE2);
			E3.getChildren().addAll(texteE3, ImgE3);
			
			texteE1.setStyle("-fx-font: 34 arial;");
			texteE1.setFill(Color.rgb(255,255,255));
			texteE2.setStyle("-fx-font: 34 arial;");
			texteE2.setFill(Color.rgb(255,255,255));
			texteE3.setStyle("-fx-font: 34 arial;");
			texteE3.setFill(Color.rgb(255,255,255));
			texteET.setStyle("-fx-font: 40 arial;");
			texteET.setFill(Color.rgb(255,255,255));
			
			ET.setAlignment(Pos.CENTER);
			E1.setAlignment(Pos.CENTER);
			E2.setAlignment(Pos.CENTER);
			E3.setAlignment(Pos.CENTER);
			
			E1.setSpacing(20);
			E2.setSpacing(20);
			E3.setSpacing(20);
			
			exemple.setSpacing(30);
			
			exemple.getChildren().addAll(ET,E1,E2,E3);
			
			
			
			
			
			
			
			
			
			


			//gestion des règlages du jeu:
			Text texte1 = new Text("Surpopulation à partir de");
			Text texte2 = new Text("Naissance à partir de");
			texte2.setFill(Color.rgb(255,255,255));
			texte1.setFill(Color.rgb(255,255,255));
			texte1.setStyle("-fx-font: 24 arial;");
			texte2.setStyle("-fx-font: 24 arial;");
			
			Font font = new Font(24);
			
			MenuButton m = new MenuButton("taille");
			MenuItem m40 = new MenuItem("40x40");
	        MenuItem m50 = new MenuItem("50x50");
	        MenuItem m30 = new MenuItem("30x30");
	        m.getItems().add(m30);
	        m.getItems().add(m40);
	        m.getItems().add(m50);
	        m.setPrefSize(300, 50);
	        m.setTextFill(Color.WHITE);
			m.setStyle("-fx-background-radius: 30px; "
					   + "-fx-background-color : #193A4D");
			m.setFont(font);
	        	        

			VBox boite1 = new VBox();

			boite1.getChildren().addAll(texte1);
			boite1.setPrefHeight(20);
			boite1.setSpacing(10);
			boite1.setPadding(new Insets(0,10,0,10));
			//root.getChildren().add(boite1);

			VBox boite2 = new VBox();

			boite2.setPrefHeight(30);
			boite2.setSpacing(10);

			FlowPane boite21 = new FlowPane();
			FlowPane boite22 = new FlowPane();

			boite2.getChildren().addAll(texte2, boite22);
			boite1.getChildren().addAll(boite21);

			boite2.setPadding(new Insets(0,10,0,10));



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




			//VBox boite3 = new VBox();
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
			bouton.setGraphic(iconPlay);
			//boite3.getChildren().add(bouton);
			bouton.setPrefSize(300, 50);
            bouton.setStyle("-fx-background-radius: 30px; "
            		        + "-fx-background-color : #AAE0FE");

//			Button bouton2 = new Button();
//			bouton2.setGraphic(iconPlay);
//			bouton2.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), null, null)));
//			//boite3.getChildren().add(bouton2);
//			//root.getChildren().addAll(boite3);
//			bouton2.setPrefSize(50, 50);
//			bouton2.setGraphic(iconPlay);
			
			Button boutonRetour = new Button("Retour");
			boutonRetour.setTextFill(Color.WHITE);
			boutonRetour.setPrefSize(300, 50);
			boutonRetour.setStyle("-fx-background-radius: 30px; "
					              + "-fx-background-color : #193A4D");
			boutonRetour.setFont(font);
			
            boutonRetour.setOnAction(e ->{
				
				primaryStage.setScene(scene);
				
			});
			
			Button boutonRetour2 = new Button("Retour");
			boutonRetour2.setTextFill(Color.WHITE);
			boutonRetour2.setPrefSize(300, 50);
			boutonRetour2.setStyle("-fx-background-radius: 30px; "
					               + "-fx-background-color : #193A4D");
			boutonRetour2.setFont(font);
			
            boutonRetour2.setOnAction(e ->{
				
				primaryStage.setScene(scene);
				
			});
			
			Button boutonRegle = new Button("Regles");
			boutonRegle.setTextFill(Color.WHITE);
			boutonRegle.setPrefSize(300, 50);
			regle.getChildren().add(boutonRetour);
			boutonRegle.setStyle("-fx-background-radius: 30px; "
				                 + "-fx-background-color : #193A4D");
			
			boutonRegle.setFont(font);
			
			boutonRegle.setOnAction(e ->{
	            
				primaryStage.setScene(sceneRegle);
				
			});
			
			Button boutonEx = new Button("Exemples");
			boutonEx.setTextFill(Color.WHITE);
			boutonEx.setPrefSize(300, 50);
			exemple.getChildren().add(boutonRetour2);
			boutonEx.setStyle("-fx-background-radius: 30px; "
					          + "-fx-background-color : #193A4D" );
			boutonEx.setFont(font);
			
            boutonEx.setOnAction(e ->{
	            
				primaryStage.setScene(sceneEx);
				
			});

			VBox boite4 = new VBox();
			boite4.getChildren().addAll(boutonRegle,boutonEx, boite1, boite2,m,bouton);
			boite4.setBackground(new Background(new BackgroundFill(Color.rgb(170, 224, 254), null, null)));
			boite4.setSpacing(30);
			// top  right  bottom  left
			boite4.setPadding(new Insets(30,30,30,30));
			root.getChildren().add(boite4);

			//plateau:
			GridPane plateau = new GridPane();
			plateau.setVgap(1);
			plateau.setHgap(1);
			// top  right  bottom  left
			plateau.setPadding(new Insets(30,30,30,30));
			root.getChildren().add(plateau);
			
			GridPane plateau2 = new GridPane();
			plateau2.setVgap(1);
			plateau2.setHgap(1);
			// h  x  b  g
			plateau2.setPadding(new Insets(30,30,30,30));
			
			GridPane plateau3 = new GridPane();
			plateau3.setVgap(1);
			plateau3.setHgap(1);
			// h  x  b  g
			plateau3.setPadding(new Insets(10,10,0,10));


			//root2.getChildren().addAll(boite3);


			Case[][] plateauTab = new Case[taille][taille];
			infoPlateau = new int[taille][taille];

			for(int i = 0 ; i < taille ; i++){
				for(int j = 0 ; j < taille ; j++){
					plateauTab[i][j] = new Case(i, j);
					plateau.add(plateauTab[i][j], i, j);
					plateauTab[i][j].HProperty().bind(scene.heightProperty().subtract((taille + 1) + 100).divide(taille));
				}
			}
			
			
			m40.setOnAction(event -> {
	            taille = 40;
	            
	            for(int i = 0 ; i < taille ; i++){
					for(int j = 0 ; j < taille ; j++){
						plateauTab[i][j] = new Case(i, j);
						plateau2.add(plateauTab[i][j], i, j);
						plateauTab[i][j].HProperty().bind(scene.heightProperty().subtract((taille + 1) + 100).divide(taille));
					}
				}
	            
	            if (numero == 1) {
	            	root.getChildren().remove(plateau);
	            } else if (numero == 3) {
	            	root.getChildren().remove(plateau3);
	            }
	            root.getChildren().add(plateau2);
	            
	            numero = 2;
	        });
			
	        m50.setOnAction(event -> {
	        	taille = 50;
	        	
	        	for(int i = 0 ; i < taille ; i++){
					for(int j = 0 ; j < taille ; j++){
						plateauTab[i][j] = new Case(i, j);
						plateau.add(plateauTab[i][j], i, j);
						plateauTab[i][j].HProperty().bind(scene.heightProperty().subtract((taille + 1) + 100).divide(taille));
					}
				}
	        	
	        	if (numero == 2) {
	        		root.getChildren().remove(plateau2);	            	
	            } else if (numero == 3) {
	            	root.getChildren().remove(plateau3);
	            }
	        	
	            root.getChildren().add(plateau);
	            
	            numero = 1;
	        	
	        });
	        
	        m30.setOnAction(event -> {
	        	taille = 30;
	        	
	        	for(int i = 0 ; i < taille ; i++){
					for(int j = 0 ; j < taille ; j++){
						plateauTab[i][j] = new Case(i, j);
						plateau3.add(plateauTab[i][j], i, j);
						plateauTab[i][j].HProperty().bind(scene.heightProperty().subtract((taille + 1) + 100).divide(taille));
					}
				}
	        	
	        	if (numero == 1) {
	        		root.getChildren().remove(plateau);
	            } else if (numero == 2) {
	            	root.getChildren().remove(plateau2);
	            }
	            root.getChildren().add(plateau3);
	            
	            numero = 3;

	            
	        });

			execution = false;
			
//			bouton2.setOnAction(a ->{
//				
//				root2.getChildren().add(plateau);
//				primaryStage.setScene(scene2);
//				bouton.setGraphic(iconPause);
//				execution = true;
//
//			});

			bouton.setOnAction(e ->{
				if(!execution){
					//lancement animation
					//root2.getChildren().add(plateau);
					//primaryStage.setScene(scene2);
					
					bouton.setGraphic(iconPause);

					execution = true;

				}else{
					//arrêt animation
					bouton.setGraphic(iconPlay);
					//primaryStage.setScene(scene);
					//root.getChildren().addAll(boite3);						
					//root.getChildren().add(plateau);						
					execution = false;

				}
			});


			//bouton clear:
			Button boutonEff = new Button("Effacer");
			boutonEff.setStyle("-fx-background-radius: 30px; "
					           + "-fx-background-color : #193A4D");
			boutonEff.setFont(font);
			boutonEff.setTextFill(Color.WHITE);
			boite4.getChildren().add(boutonEff);
			boutonEff.setPrefSize(300, 50);
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
			slider.setStyle("-fx-background-color: linear-gradient(to right, #2D819D START, #969696 END);");
			slider.setMin(1);
			slider.setMax(459);
			boite4.getChildren().add(slider);
			slider.setPrefSize(300, 50);
			slider.setValue(400);







			//gestion layout:
			//            primaryStage.heightProperty().addListener(e -> {
			//                primaryStage.setWidth(primaryStage.getHeight() - 100);
			//            });
			//
			//				primaryStage.widthProperty().addListener(e -> {
			//					primaryStage.setHeight(primaryStage.getWidth() + 100);
			//					texte1.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 50));
			//					texte2.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 50));
			//					bouton.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 50));
			//					boutonEff.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, primaryStage.getWidth() / 55));
			//				});





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
