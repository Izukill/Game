package Game.Modelos;

import javax.swing.*;
import java.awt.*;

public class Ataque {
    private Image imagem;
    private int x,y;
    private int altura,largura;
    private boolean isVisible;

    public Ataque(int x,int y){
        this.x=x;
        this.y=y;
        this.isVisible=true;
    }

    private static int velocidade=10;

    public void carregar(){
        ImageIcon referencia=new ImageIcon("src\\resource\\ataque.png");
        imagem= referencia.getImage();
        this.altura= imagem.getHeight(null);
        this.largura= imagem.getWidth(null);

    }

    public void atirar(){
        this.y-=velocidade;
    }

    //Hitbox dos ataques

    public Rectangle hitbox(){
        return new Rectangle(x,y,largura,altura);
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public static int getVelocidade() {
        return velocidade;
    }

    public static void setVelocidade(int velocidade) {
        Ataque.velocidade = velocidade;
    }

}
