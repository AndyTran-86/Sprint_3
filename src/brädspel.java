import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class brädspel extends JFrame {

    private ArrayList<Integer> nummer = new ArrayList<Integer>();
    private JButton[][] knappar = new JButton[4][4];

    private JPanel panel = new JPanel(new BorderLayout());
    private JPanel mitten = new JPanel(new GridLayout(4, 4));
    private JPanel söder = new JPanel(new GridLayout(1, 3));

    private JButton köraOmKnapp = new JButton("Köra om");
    private JButton avslutaKnapp = new JButton("Avsluta");
    private JButton ingetKnapp = new JButton(" ");

    private int nollRad;
    private int nollKolumn;

    //private int senasteRad;
    //private int senasteKolumn;

    public brädspel() {
        //JFrame
        setTitle("Andys 15-pussel spel");
        setSize(500, 500);
        //setLayout(new GridLayout(4, 4));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        söder.add(köraOmKnapp);
        söder.add(avslutaKnapp);
        söder.add(ingetKnapp);

        panel.add(mitten, BorderLayout.CENTER);
        panel.add(söder, BorderLayout.SOUTH);

        köraOmKnapp.setBackground(Color.lightGray);
        avslutaKnapp.setBackground(Color.lightGray);
        ingetKnapp.setBackground(Color.lightGray);


        läggTillNummer();
        //System.out.println("innehållet: " + nummer );

        läggTillKnappar();


        köraOmKnapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        avslutaKnapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ingetKnapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nästanRätt();
            }
        });


        add(panel);
        setVisible(true);
    }

    ////////////////////////////////////////////////////
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


    //Skapa siffrorna/knapparna och lägger till det i brädan
    private void läggTillKnappar() {
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int nr = nummer.get(index++);

                JButton knapp = skapaKnapp(nr);
                knappar[i][j] = knapp;

                //hitta 0 knappens position
                if (nr == 0) {
                    nollRad = i;
                    nollKolumn = j;

                    //System.out.println(nollRad + " " + nollKolumn);
                }

                int senasteRad = i;
                int senasteKolumn = j;


                //lägger till actionListener till knapparna
                knapp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //System.out.println("Knapp nummer " + nr + " är på plats " + senasteRad + ", " + senasteKolumn );

                        //loopar och ser ifall att 0 knappen är bredvid den tryckta knappen
                        if ((senasteRad == nollRad - 1 && senasteKolumn == nollKolumn) ||
                                (senasteRad == nollRad + 1 && senasteKolumn == nollKolumn) ||
                                (senasteRad == nollRad && senasteKolumn == nollKolumn - 1) ||
                                (senasteRad == nollRad && senasteKolumn == nollKolumn + 1)) {

                            knappar[nollRad][nollKolumn].setText(knapp.getText());
                            knappar[nollRad][nollKolumn].setEnabled(true);

                            knapp.setText("");
                            knapp.setEnabled(false);

                            nollRad = senasteRad;
                            nollKolumn = senasteKolumn;


                            //anropar metoden kontrolleraVinst för att se om man har vunnit efter varje loop/flytt
                            kontrolleraVinst();


                        }
                    }
                });

                mitten.add(knapp);
            }
        }
    }

////////////////////////////////////////////////////

    private void resetGame() {
        nummer.clear();
        läggTillNummer();

        mitten.removeAll();
        läggTillKnappar();

        mitten.revalidate();
        mitten.repaint();
    }


    private void nästanRätt() {
        nummer.clear();

        for (int i = 1; i <= 15; i++) {
            nummer.add(i);
        }
        nummer.add(0);

        //byter plats med de två sista pos 14 & 15
        int temp = nummer.get(14);
        nummer.set(14, nummer.get(15));
        nummer.set(15, temp);

        //uppdatera panelerna
        mitten.removeAll();
        läggTillKnappar();

        mitten.revalidate();
        mitten.repaint();

    }

    //metod för att kontrollera vinst
    private void kontrolleraVinst() {
        int vinstNummer = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    continue;
                }


                if (!knappar[i][j].getText().equals(String.valueOf(vinstNummer))) {
                    return;
                }
                vinstNummer++;
            }
            }

                if (knappar[3][3].getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Grattis! Ni har Vunnit!");

                    //anropar metoden resetGame när man trycker ok
                    resetGame();
                }
            }

////////////////////////////////////////////////////

        //Gör siffrorna till sträng(visar) och 0 till tom knapp
        private JButton skapaKnapp(int nr) {
            JButton knapp = new JButton(nr == 0 ? "" : String.valueOf(nr));

            if (nr == 0){
                knapp.setEnabled(false);
            }
            return knapp;
        }

////////////////////////////////////////////////////

        public static void main(String[] args) {

            brädspel brädspel = new brädspel();
        }
}

