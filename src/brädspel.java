import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class brädspel extends JFrame {
private ArrayList<Integer> nummer = new ArrayList<Integer>();
private JButton[][] knappar = new JButton[4][4];


        public brädspel() {
            //JFrame
            setTitle("Andys 15-pussel spel");
            setSize(500, 500);
            setLayout(new GridLayout(4, 4));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            läggTillNummer();
            //System.out.println("innehållet: " + nummer );

            läggTillKnappar();

            setVisible(true);
        }


        //Skapa en array med nummer och en 0.
        private void läggTillNummer() {

            for (int i = 1;
                 i <= 15; i++) {
                nummer.add(i);
            }
            nummer.add(0);

            //System.out.println("innehållet: " + nummer );


            //Slumpa siffrorna/knapparna i arrayn
            Random slumpa = new Random();
            for (int i = nummer.size() - 1; i > 0; i--) {
                int j = slumpa.nextInt(i + 1);
                int temp = nummer.get(i);
                nummer.set(i, nummer.get(j));
                nummer.set(j, temp);
            }
            //System.out.println("innehållet: " + nummer );

        }


        //Skapa siffrorna/knapparna och lägg till i brädan
        private void läggTillKnappar() {
            int index = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int nr = nummer.get(index++);

                    JButton knapp = skapaKnapp(nr);
                    knappar[i][j] = knapp;

                    add(knapp);
                }
            }
        }


        //Gör siffrorna till sträng(visar) och 0 till tom knapp
        private JButton skapaKnapp(int nr) {
            JButton knapp = new JButton(nr == 0 ? "" : String.valueOf(nr));

            if (nr == 0){
                knapp.setEnabled(false);
            }
            return knapp;
        }







        public static void main(String[] args) {
            brädspel brädspel = new brädspel();
        }

}