package Game;

import Game.Modelos.Fase;

import javax.swing.*;

public class Janela extends JFrame {

    public Janela(){
        add(new Fase());
        setTitle("Space shooter");
        setSize(900,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        Janela jogo=new Janela();
    }
}
