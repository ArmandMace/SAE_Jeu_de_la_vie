package application.jeuxDeLaVie;

import javafx.scene.control.CheckBox;

public class Calculateur extends Thread{

    private boolean partieEnCours;
    private int[][] tabcalc;
    private Case[][] tab;
    private CheckBox[] tabsurvie;
    private CheckBox[] tabnaissance;

    public Calculateur(int[][] infoplateau, Case[][] plateau, CheckBox[] tabsurv, CheckBox[] tabnaiss){
        partieEnCours = true;
        tabcalc = infoplateau;
        tab = plateau;
        tabsurvie = tabsurv;
        tabnaissance = tabnaiss;
    }

    public synchronized void run(){

        while(partieEnCours){

            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Interruption");
                partieEnCours = false;
                return;
            }

            if(Main.isInExecution()){



//			System.out.println("calculs");

                //calculs:
                for(int i = 0 ; i < 40 ; i++){
                    for (int j = 0 ; j < 40 ; j++){

                        int voisins = 0;

                        if(tab[(i-1+40)%40][(j-1+40)%40].isOccupee())
                            voisins++;

                        if(tab[i][(j-1+40)%40].isOccupee())
                            voisins++;

                        if(tab[(i+1)%40][(j-1+40)%40].isOccupee())
                            voisins++;

                        if(tab[(i+1)%40][j].isOccupee())
                            voisins++;

                        if(tab[(i+1)%40][(j+1)%40].isOccupee())
                            voisins++;

                        if(tab[i][(j+1)%40].isOccupee())
                            voisins++;

                        if(tab[(i-1+40)%40][(j+1)%40].isOccupee())
                            voisins++;

                        if(tab[(i-1+40)%40][j].isOccupee())
                            voisins++;

                        if(tab[i][j].isOccupee()){
                            //conditions de décès:
                            if(!tabsurvie[voisins].isSelected()){
                                tabcalc[i][j] = 0;
                            }


                        }else{
                            //conditions de naissance:
                            if(tabnaissance[voisins].isSelected()){
                                tabcalc[i][j] = 1;
                            }


                        }


                    }
                }






            }
        }
        System.out.println("fermeture pile");
    }

}