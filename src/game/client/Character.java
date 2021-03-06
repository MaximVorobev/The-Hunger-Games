package src.game.client;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class Character extends Pane{
    private double hp = 100;
    private int score = 0;
    private double pos_x;
    private double pos_y;
    private int width = 57;
    private int height = 53;
    private Image img;
    private ImageView imageView;

    public Character(double pos_x, double pos_y, Image img){
        this.pos_x = pos_x;
        this.pos_y = pos_y;  
        this.img = img;
        setLayoutX(this.pos_x);
        setLayoutY(this.pos_y);
        this.imageView = new ImageView(this.img);
        this.imageView.setViewport(new Rectangle2D(0, 0, this.width, this.height));
        getChildren().addAll(this.imageView);
    }

    public double getPosX(){
        return this.pos_x;
    }

    public double getPosY(){
        return this.pos_y;
    }

    public int getCharacterWidth(){
        return this.width;
    }

    public int getCharacterHeight(){
        return this.height;
    }

    public double getHp(){
        return this.hp;
    }

    public int getScore(){
        return this.score;
    }

    public void setPosX(double pos_x){
        this.pos_x = pos_x;
    }

    public void setPosY(double pos_y){
        this.pos_y = pos_y;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setHp(double hp){
        this.hp = hp;
    }

    public boolean isAlive(){
        if (this.hp <= 0){
            return false;
        }
        return true;
    }

    public void get_damage(){
        if (this.hp > 0){
            this.hp -= 0.05;
        }
        String hp = String.format("%.2f", this.hp).replace(",", ".");
        this.hp = Double.parseDouble(hp);  
    }

    public void moveX(Double x){
        boolean duration_x = x>0 ? true:false;
        if (duration_x) this.setTranslateX(this.getTranslateX() + Math.abs(x));
        else this.setTranslateX(this.getTranslateX() - Math.abs(x));
        this.pos_x += x;
    }

    public void moveY(Double y){
        boolean duration_y = y>0 ? true:false;
        if (duration_y) this.setTranslateY(this.getTranslateY() + Math.abs(y));
        else this.setTranslateY(this.getTranslateY() - Math.abs(y));
        this.pos_y += y;
    }
}
