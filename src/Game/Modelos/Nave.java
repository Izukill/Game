package Game.Modelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Nave {
    private int x,y;
    private int dx,dy;
    private Image imagem;
    private int altura,largura;
    private boolean isVisivel;

    //É feito um Array porque não se sabe a quantidade de tiros que o jogador pode dar em uma fase
    private List<Ataque> ataques;


    public Nave(){
        this.x=350;
        this.y=500;
        this.isVisivel=true;

        ataques=new ArrayList<Ataque>();
    }

    //Método para carregar a nave na Janela principal
    public void carregar(){
        ImageIcon referencia= new ImageIcon("src\\resource\\nave.png");
        imagem= referencia.getImage();
        altura= imagem.getHeight(null);
        largura= imagem.getWidth(null);
    }


    //Método para atualizar a posição da nave conforme os comandos
    public void atualizar(){
        x+=dx;
        y+=dy;
    }

    //A classe Rectangle vai servir como uma hitbox em volta da nave

    public Rectangle hitbox(){
        return new Rectangle(x,y,largura,altura);
    }

    public void Tiro(){
        this.ataques.add(new Ataque(x+20,y));
        //o +20 no x serve para alinhar a imagem do ataque com a nave

    }



    //Métodos para pegar os comenados do teclado através do KeyEvent


    //Este é para ele se mover quando estiver pressionado somando ou subtraindo o dx e o dy
    public void ComandoPressionado(KeyEvent tecla){
        int comando= tecla.getKeyCode();

        if(comando == KeyEvent.VK_UP){
            this.dy=-6;
        }
        if(comando == KeyEvent.VK_DOWN){
            this.dy=6;
        }
        if(comando == KeyEvent.VK_RIGHT){
            this.dx=6;
        }
        if(comando == KeyEvent.VK_LEFT){
            this.dx=-6;
        }
    }



    //Este para ele parar de se mover quando a tecla for solta
    public void ComandoSolto(KeyEvent tecla){
        int comando= tecla.getKeyCode();

        if(comando == KeyEvent.VK_UP){
            this.dy=0;
        }
        if(comando == KeyEvent.VK_DOWN){
            this.dy=0;
        }
        if(comando == KeyEvent.VK_RIGHT){
            this.dx=0;
        }
        if(comando == KeyEvent.VK_LEFT){
            this.dx=0;
        }
        if(comando == KeyEvent.VK_Z){
            Tiro();
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }
}
