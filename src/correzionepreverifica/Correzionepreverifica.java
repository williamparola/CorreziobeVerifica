package correzionepreverifica;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Correzionepreverifica extends JFrame {

    final int rig = 5;
    final int col = 5;
    final int tempo = 3000;

    int presi, prec;

    Label Presi = new Label("0");
    Label LPresi = new Label("Presi:");

    Label[][] L = new Label[col][rig];
    Button[][] B = new Button[col][rig];
    GestPulsB B1 = new GestPulsB();

    Button R = new Button("Reset");
    GestPulsR R1 = new GestPulsR();

    public Correzionepreverifica() {
        int i = 0, j = 0;

        setLayout(null);

        this.add(LPresi);
        LPresi.setBounds(120, 30, 50, 30);
        this.add(Presi);
        Presi.setBounds(170, 30, 50, 30);

        this.add(R);
        R.setBounds(220, 30, 80, 30);
        R.addActionListener(R1);

        for (j = 0; j < rig; j++) {
            for (i = 0; i < col; i++) {
                L[i][j] = new Label("");
                this.add(L[i][j]);
                L[i][j].setBounds(32 * (i + 1) + 50, 32 * (j + 1) + 50, 30, 30);

                B[i][j] = new Button("");
                this.add(B[i][j]);
                B[i][j].addActionListener(B1);
                B[i][j].setBounds(32 * (i + 1) + 50, 32 * (j + 1) + 50, 30, 30);
            }
        }

        partenza();

        this.setTitle("Progressivi");
        this.setLocation(200, 100);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public static void main(String argv[]) {
        new Correzionepreverifica();
    }

    public void partenza() {
        int k, i, j;
        int ai, aj, bi, bj;
        String t1, t2;

        prec = -1;
        presi = 0;
        Presi.setText(String.valueOf(presi));

        /*
        for (j = 0; j < rig; j++) {
            for (i = 0; i < col; i++) {
                L[i][j].setVisible(true);
                B[i][j].setVisible(false);
            }
        }
        */
        
        k = 0;
        for (j = 0; j < rig; j++) {
            for (i = 0; i < col; i++) {
                k++;
                L[i][j].setText(String.valueOf(k));
                L[i][j].setBackground(Color.YELLOW);
            }
        }

        for (k = 1; k < 100; k++) {
            ai = (int) Math.floor(Math.random() * col);
            aj = (int) Math.floor(Math.random() * rig);
            bi = (int) Math.floor(Math.random() * col);
            bj = (int) Math.floor(Math.random() * rig);

            t1 = L[ai][aj].getText();
            t2 = L[bi][bj].getText();
            L[ai][aj].setText(t2);
            L[bi][bj].setText(t1);
        }

        /*
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
        }
        */
        
        for (j = 0; j < rig; j++) {
            for (i = 0; i < col; i++) {
                L[i][j].setVisible(false);
                B[i][j].setVisible(true);
            }
        }
    }

    class GestPulsR implements ActionListener {

        public void actionPerformed(ActionEvent E) {
            partenza();
        }
    }

    class GestPulsB implements ActionListener {

        public void actionPerformed(ActionEvent E) {

            int i, j, cosa, basta;

            basta = 0;
            for (j = 0; j < rig; j++) {
                for (i = 0; i < col; i++) {
                    if (E.getSource() == B[i][j]) {
                        B[i][j].setVisible(false);
                        L[i][j].setVisible(true);
                        cosa = Integer.parseInt(L[i][j].getText());
                        if (cosa > prec) {
                            L[i][j].setBackground(Color.GREEN);
                            presi++;
                            Presi.setText(String.valueOf(presi));
                            prec = cosa;
                        } else {
                            L[i][j].setBackground(Color.RED);
                            basta = 1;
                        }
                    }
                }
            }

            if (basta == 1) {
                for (j = 0; j < rig; j++) {
                    for (i = 0; i < col; i++) {
                        B[i][j].setVisible(false);
                        L[i][j].setVisible(true);
                    }
                }
            }
        }
    }

}