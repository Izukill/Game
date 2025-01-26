package Game.Modelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener{
    private final Image background;
    private final Nave nave;
    private Timer timer;
    private List<Alien> aliens;
    private boolean emJogo;


    //Construtor com o ImageIcon para manipular imagens atraves de uma referencia e colocar na variavel background
    public Fase() {

        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon reference = new ImageIcon("src\\resource\\SpaceBackground.png");
        background = reference.getImage();

        //Instanciando a nave dentro da fase
        nave = new Nave();
        nave.carregar();

        addKeyListener(new TecladoAdapter());

        Timer timer=new Timer(5,this);
        timer.start();

        iniciaAliens();
        emJogo=true;


    }


    //Método para "pintar" a tela sobre a janela utilizando a biblioteca Graphics
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if(emJogo) {
            graphics.drawImage(background, 0, 0, null);
            //É passado o this como parametro para o observer para não haver bugs de riscos na tela ao movimentar a nave caso ela não esteja completamente carregada
            graphics.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);


            List<Ataque> ataques = nave.getAtaques();
            for (int i = 0; i < ataques.size(); i++) {
                Ataque m = ataques.get(i);
                m.carregar();
                graphics.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int k = 0; k < aliens.size(); k++) {
                Alien z = aliens.get(k);
                z.carregar();
                graphics.drawImage(z.getImagem(), z.getX(), z.getY(), this);
            }
        }else{
            ImageIcon fimDeJogo=new ImageIcon("src\\resource\\gameover.png");
            graphics.drawImage(fimDeJogo.getImage(),0,0,null);
        }



        g.dispose();
    }

    public void iniciaAliens(){
        int spawn[]=new int[100];
        aliens=new ArrayList<Alien>();
        for(int i=0; i < spawn.length;i++){
            int x=(int)(Math.random()* 610 + 30);
            int y=(int)(Math.random()* -10000);
            aliens.add(new Alien(x,y));

        }

    }

    public void checarColisoes(){
        Rectangle formaNave= nave.hitbox();
        Rectangle formaAtaque;
        Rectangle formaAlien;

        //Esse for atribui uma hitbox para os inimigos da lista "aliens"
        for(int i=0; i< aliens.size();i++){
            Alien inimigo=aliens.get(i);
            formaAlien=inimigo.hitbox();
            if(formaNave.intersects(formaAlien)){
                nave.setVisivel(false);
                inimigo.setVisible(false);
                emJogo=false;
            }
        }

        List<Ataque> ataques=nave.getAtaques();
        for(int k=0; k < ataques.size();k++){

            Ataque tiro= ataques.get(k);
            formaAtaque=tiro.hitbox();

            for(int i=0; i< aliens.size();i++){
                Alien inimigo=aliens.get(i);
                formaAlien=inimigo.hitbox();

                if(formaAtaque.intersects(formaAlien)){
                    inimigo.setVisible(false);
                    tiro.setVisible(false);

                }

            }

        }
    }



    //Método da implementação ActionListener para atualizar a tela toda vez que um comando for feito
    @Override
    public void actionPerformed(ActionEvent e) {
        nave.atualizar();


        //É necessário um for para percorrer a lista para que caso os tiros já tenham sido usados eles saiam da lista
        List<Ataque> ataques= nave.getAtaques();
        for(int i=0; i< ataques.size() ;i++){
            Ataque m = ataques.get(i);
            if(m.isVisible()){
                m.atirar();
            }else{
                ataques.remove(i);
            }
        }

        for(int k=0; k < aliens.size();k++){
            Alien z= aliens.get(k);
            if (z.isVisible()){
                z.atirar();
            }else{
                aliens.remove(k);
            }

        }
        checarColisoes();
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            nave.ComandoPressionado(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            nave.ComandoSolto(e);
        }
    }
}


