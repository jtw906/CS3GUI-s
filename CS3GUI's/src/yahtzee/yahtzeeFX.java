package yahtzee;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class yahtzeeFX extends Application {
    private int one = 0,two =0,three =0, four = 0,five = 0, six = 0,threeof=0,fourof=0,full=0,small=0,large=0,yah=0,c=0,rolls=3,uppertotal=0,lowertotal=0;
    private boolean bone = false,btwo = false,bthree = false, bfour = false,bfive = false, bsix  = false,bthreeof = false,bfourof = false,bfull = false,bsmall = false,blarge = false,byah = false,bc = false;
    private Button ones, twos,threes,fours,fives,sixes,toaf,foaf,fh,ss,ls,yahtzee,chance;
    private StackPane epic;
    public void start(Stage primaryStage){
        epic = new StackPane();
        DiceButton die1 = new DiceButton(1);
        DiceButton die2 = new DiceButton(2);
        DiceButton die3 = new DiceButton(3);
        DiceButton die4 = new DiceButton(4);
        DiceButton die5 = new DiceButton(5);
        die1.setTranslateY(-400);
        die2.setTranslateY(-200);
        die4.setTranslateY(200);
        die5.setTranslateY(400);
        die1.setTranslateX(-600);
        die2.setTranslateX(-600);
        die3.setTranslateX(-600);
        die4.setTranslateX(-600);
        die5.setTranslateX(-600);

        Button roll = new Button("Roll "+rolls);
        roll.setTranslateX(-400);
        roll.setOnAction(event -> {
            Object[] temp = epic.getChildren().toArray();
            if(rolls>0) {
                for (int i = 0; i < 5; i++) {
                    if (((DiceButton) temp[i]).getReroll()) {
                        ((DiceButton) temp[i]).roll();
                        epic.getChildren().set(i, ((DiceButton) temp[i]));
                    }
                }
                rolls--;
                roll.setText("Roll "+ rolls);
            }
            if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc) {
                one = 0;
                ones.setText("ones " + one);
                bone=false;
                two = 0;
                twos.setText("twos " + two);
                btwo=false;
                three = 0;
                threes.setText("threes " + three);
                bthree=false;
                four = 0;
                fours.setText("fours " + four);
                bfour=false;
                five = 0;
                fives.setText("fives " + five);
                bfive=false;
                six = 0;
                sixes.setText("sixes " + six);
                bsix=false;
                threeof = 0;
                toaf.setText("three of " + threeof);
                bthreeof=false;
                fourof = 0;
                foaf.setText("four of " + fourof);
                bfourof=false;
                large = 0;
                ls.setText("large " + large);
                blarge=false;
                small = 0;
                ss.setText("small " + small);
                bsmall=false;
                full = 0;
                fh.setText("full " + full);
                bfull=false;
                yah = 0;
                yahtzee.setText("yahtzee " + yah);
                byah=false;
                c = 0;
                chance.setText("chance " + c);
                bc=false;
                uppertotal=0;
                lowertotal=0;
                for (int i = 0; i < 5; i++) {
                    if (((DiceButton) temp[i]).getReroll()) {
                        ((DiceButton) temp[i]).roll();
                        epic.getChildren().set(i, ((DiceButton) temp[i]));
                    }
                }
                for (int i = 6; i < 19; i++) {
                    ((Button) temp[i]).setDisable(false);
                }
                rolls=2;
                roll.setText("Roll "+ rolls);

            }
        });
        Button upper = new Button("Upper "+uppertotal);
        upper.setTranslateX(300);
        upper.setTranslateY(-50);

        Button lower = new Button("Lower "+lowertotal);
        lower.setTranslateX(300);
        lower.setTranslateY(350);

        ones = new Button("ones "+one);
        ones.setTranslateX(300);
        ones.setTranslateY(-350);
        ones.setOnAction(event -> {
            if(!bone) {
                bone = true;
                ones.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                uppertotal+=one;
                upper.setText("Upper "+uppertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        twos = new Button("twos "+two);
        twos.setTranslateX(300);
        twos.setTranslateY(-300);
        twos.setOnAction(event -> {
            if(!btwo) {
                btwo = true;
                twos.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i,((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                uppertotal+=two;
                upper.setText("Upper "+uppertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        threes = new Button("threes "+three);
        threes.setTranslateX(300);
        threes.setTranslateY(-250);
        threes.setOnAction(event -> {
            if(!bthree) {
                bthree = true;
                threes.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                uppertotal+=three;
                upper.setText("Upper "+uppertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        fours = new Button("fours "+four);
        fours.setTranslateX(300);
        fours.setTranslateY(-200);
        fours.setOnAction(event -> {
            if(!bfour){
                bfour = true;
                fours.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                uppertotal+=four;
                upper.setText("Upper "+uppertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        fives = new Button("fives "+five);
        fives.setTranslateX(300);
        fives.setTranslateY(-150);
        fives.setOnAction(event -> {
            if(!bfive) {
                bfive = true;
                fives.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                uppertotal+=five;
                upper.setText("Upper "+uppertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        sixes = new Button("sixes "+six);
        sixes.setTranslateX(300);
        sixes.setTranslateY(-100);
        sixes.setOnAction(event -> {
            if(!bsix) {
                bsix = true;
                sixes.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                uppertotal+=six;
                upper.setText("Upper "+uppertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        toaf = new Button("three of "+threeof);
        toaf.setTranslateX(300);
        toaf.setTranslateY(0);
        toaf.setOnAction(event -> {
            if(!bthreeof) {
                bthreeof = true;
                toaf.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=threeof;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        foaf = new Button("four of "+fourof);
        foaf.setTranslateX(300);
        foaf.setTranslateY(50);
        foaf.setOnAction(event -> {
            if(!bfourof) {
                bfourof = true;
                foaf.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=fourof;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        fh = new Button("full "+full);
        fh.setTranslateX(300);
        fh.setTranslateY(100);
        fh.setOnAction(event -> {
            if(!bfull) {
                bfull = true;
                fh.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=full;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        ss = new Button("small "+small);
        ss.setTranslateX(300);
        ss.setTranslateY(150);
        ss.setOnAction(event -> {
            if(!bsmall) {
                bsmall = true;
                ss.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=small;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        ls = new Button("large "+large);
        ls.setTranslateX(300);
        ls.setTranslateY(200);
        ls.setOnAction(event -> {
            if(!blarge) {
                blarge = true;
                ls.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=large;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        yahtzee = new Button("yahtzee "+yah);
        yahtzee.setTranslateX(300);
        yahtzee.setTranslateY(250);
        yahtzee.setOnAction(event -> {
            if(!byah) {
                byah = true;
                yahtzee.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=yah;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        chance = new Button("chance "+ c);
        chance.setTranslateX(300);
        chance.setTranslateY(300);
        chance.setOnAction(event -> {
            if(!bc) {
                bc = true;
                chance.setDisable(true);
                Object[] temp = epic.getChildren().toArray();
                for (int i = 0; i < 5; i++) {
                    ((DiceButton) temp[i]).setReroll(true);
                    ((DiceButton) temp[i]).setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                    ((DiceButton) temp[i]).roll();
                    epic.getChildren().set(i, ((DiceButton) temp[i]));
                    rolls = 2;
                    roll.setText("Roll " + rolls);
                }
                lowertotal+=c;
                lower.setText("Lower "+lowertotal);
                if(bone&&btwo&&bthree&&bfour&&bfive&&bsix&&bthreeof&&bfourof&&bfull&&bsmall&&blarge&&byah&&bc){
                    rolls = 0;
                    int bonus=0;
                    boolean bbonus = false;
                    if(uppertotal>=63){
                        bonus=35;
                        bbonus=true;
                    }
                    roll.setText("Final Score: "+ (bonus+uppertotal+lowertotal)+(bbonus?" Bonus was gained":" Bonus wasn't gained") + " Press to restart");
                }
            }
        });
        try {
            String str = "Hello";
            BufferedWriter writer = new BufferedWriter(new FileWriter("yahtzeeHighScores.txt"));
            writer.write(str);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        epic.getChildren().addAll(die1,die2,die3,die4,die5,roll,ones,twos,threes,fours,fives,sixes,toaf,foaf,fh,ss,ls,yahtzee,chance,upper,lower);
        InputStream input = getClass().getResourceAsStream("/Images/casino.jpg");
        BackgroundImage myBI= new BackgroundImage(new Image(input), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(1740,980,false,false,false,false));
        epic.setBackground(new Background(myBI));
        primaryStage.setScene(new Scene(epic, 1740, 980));
        primaryStage.setTitle("yahtzee");
        primaryStage.show();
    }
    public class DiceButton extends ToggleButton{
        private int num;
        private boolean reroll;
        public DiceButton(int num){
            super();
            this.num = num;
            this.reroll = true;
            this.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
            setPic(num);
            this.setPadding(Insets.EMPTY);
            this.setOnAction(event -> {
                if (this.isSelected()) {
                    reroll = true;
                    this.setStyle("-fx-border-color: #FFFFFF; -fx-border-width: 5px");
                } else {
                    reroll = false;
                    this.setStyle("-fx-border-color: #000000; -fx-border-width: 5px");
                }
            });
        }
        public void setNum(int n){
            this.num = n;
            setPic(num);
        }
        public int getNum(){
            return num;
        }
        public boolean getReroll(){
            return reroll;
        }
        public void setReroll(boolean b){
            reroll = b;
        }
        public void setPic(int num){
            InputStream input = getClass().getResourceAsStream("/Images/dice-"+ num + ".jpg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            this.setGraphic(imageView);
            this.setScaleX(.5);
            this.setScaleY(.5);
        }
        public void roll(){
            setNum((int)(Math.random()*6+1));
            scorecheck();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void scorecheck(){
        Object[] temp = epic.getChildren().toArray();
        int[] vals = new int[5];
        int tempone = 0,temptwo =0,tempthree =0, tempfour = 0,tempfive = 0, tempsix = 0,tempthreeof=0,tempfourof=0,tempfull=0,tempsmall=0,templarge=0,tempyah=0,tempc=0;
        for(int i = 0; i < 5; i++){
            vals[i]=((DiceButton)temp[i]).getNum();
        }
        Arrays.sort(vals);
        for(int i = 0; i < 5; i++){
            if(vals[i]==1){
                tempone++;
            }
            if(vals[i]==2){
                temptwo+=2;
            }
            if(vals[i]==3){
                tempthree+=3;
            }
            if(vals[i]==4){
                tempfour+=4;
            }
            if(vals[i]==5){
                tempfive+=5;
            }
            if(vals[i]==6){
                tempsix+=6;
            }
            tempc+=vals[i];
        }
        if (tempone>=3||temptwo>=6||tempthree>=9||tempfour>=12||tempfive>=15||tempsix>=18){
            tempthreeof = tempc;
        }
        if (tempone>=4||temptwo>=8||tempthree>=12||tempfour>=16||tempfive>=20||tempsix>=24){
            tempfourof = tempc;
        }
        if (tempone==5||temptwo==10||tempthree==15||tempfour==20||tempfive==25||tempsix==30){
            tempyah = 50;
        }
        if(vals[0] == vals[1] && vals[1] == vals[2]){
            if(vals[3] == vals[4]&&vals[0]!=vals[4]){
                tempfull=25;
            }
        }
        if (vals[0] == vals[1]&&vals[0]!=vals[4]){
            if(vals[2] == vals[3] && vals[3] == vals[4]){
                tempfull=25;
            }
        }
        HashSet<Integer> straightcheck = new HashSet<Integer>();
        straightcheck.add(vals[0]);
        straightcheck.add(vals[1]);
        straightcheck.add(vals[2]);
        straightcheck.add(vals[3]);
        straightcheck.add(vals[4]);
        ArrayList<Integer> ls1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        ArrayList<Integer> ls2 = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6));
        ArrayList<Integer> ss1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
        ArrayList<Integer> ss2 = new ArrayList<Integer>(Arrays.asList(2,3,4,5));
        ArrayList<Integer> ss3 = new ArrayList<Integer>(Arrays.asList(3,4,5,6));
        if(straightcheck.containsAll(ls1)||straightcheck.containsAll(ls2)){
            templarge = 40;
        }
        if(straightcheck.containsAll(ss1)||straightcheck.containsAll(ss2)||straightcheck.containsAll(ss3)){
            tempsmall = 30;
        }


        if(!bone){
            one=tempone;
            ones.setText("ones "+one);
        }
        if(!btwo){
            two=temptwo;
            twos.setText("twos "+two);
        }
        if(!bthree){
            three=tempthree;
            threes.setText("threes "+three);
        }
        if(!bfour){
            four=tempfour;
            fours.setText("fours "+four);
        }
        if(!bfive){
            five=tempfive;
            fives.setText("fives "+five);
        }
        if(!bsix){
            six = tempsix;
            sixes.setText("sixes "+six);
        }
        if(!bthreeof){
            threeof=tempthreeof;
            toaf.setText("three of "+threeof);
        }
        if(!bfourof){
            fourof=tempfourof;
            foaf.setText("four of "+fourof);
        }
        if(!blarge){
            large=templarge;
            ls.setText("large "+large);
        }
        if(!bsmall){
            small=tempsmall;
            ss.setText("small "+small);
        }
        if(!bfull){
            full=tempfull;
            fh.setText("full "+full);
        }
        if(!byah){
            yah=tempyah;
            yahtzee.setText("yahtzee "+yah);
        }
        if(!bc){
            c=tempc;
            chance.setText("chance "+c);
        }
    }
}