import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class brädspel extends JFrame {
private ArrayList<Integer> nummer = new ArrayList<Integer>();
private JButton[][] knappar = new JButton[4][4];


        public brädspel(){
            for(int i = 1;
                i <=15;i++) {
                nummer.add(i);
            }
            nummer.add(0);


            Random slumpa = new Random();
            for (int i = nummer.size() - 1; i > 0; i--){
                int j = slumpa.nextInt(i + 1);
                int temp = nummer.get(i);
                nummer.set(i, nummer.get(j));
                nummer.set(j, temp);

            }





            //JFrame
            setTitle("Andys 15-pussel spel");
            setSize(500,500);
            setLayout(new GridLayout(4,4));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }






        public static void main(String[] args) {
            brädspel brädspel = new brädspel();
        }

}