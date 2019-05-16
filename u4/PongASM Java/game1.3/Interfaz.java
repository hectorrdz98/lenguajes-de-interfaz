
import processing.core.*;
import processing.sound.*;
import java.awt.Desktop;
import java.net.URI;

public class Interfaz extends PApplet {
    
    HectorASM asm;

    String scene = "loading";
    Paddle leftPaddle, rightPaddle;
    Ball ball;

    boolean gameOver = false;
    int counter = 0;

    int textSize = 0;
    int textSize2 = 0;
    int textOpacity = 0;
    int textCounter = 0;
    int textPos[];
    String text = "Este proyecto fue creado utilizando asm puro \npara todas las operaciones matem\u00e1ticas";

    SoundFile file;
    boolean audioPlaying = false;
    boolean withAudio = false;

    Interfaz (HectorASM asm) {
        this.asm = asm;
    }

    public void settings() { 
        size(1280, 720);
    }

    public void setup() {
        // All in scene_setup()
        frame.setTitle("PongASM");
        PImage icon = loadImage("icon.jpg");
        surface.setIcon(icon);
    }

    public void draw() {
        if (scene.equals("loading")) {
            clear();
            scene_loading();
        } else if (scene.equals("setup")) {
            scene_setup();
        } else if (scene.equals("init")) {
            clear();
            scene_init();
        } else if (scene.equals("menu")) {
            clear();
            scene_menu();
        } else if (scene.equals("game")) {
            clear();
            scene_game();
        }          
    }

    public void keyPressed() {
        if (scene.equals("game")) {
            // Check if ball isn't moving
            if (ball.vel[0] == 0 && ball.vel[1] == 0) {
                ball.vel[0] = (int) random(-10, 10);
                ball.vel[1] = (int) random(-10, 10);
            }

            // Left paddle move
            if (key == 'w') {
                leftPaddle.moving = -1;
            } else if (key == 's') {
                leftPaddle.moving = 1;
            }

            // Right paddle move
            if (keyCode == UP) {
                rightPaddle.moving = -1;
            } else if (keyCode == DOWN) {
                rightPaddle.moving = 1;
            }
        }         
    }

    public void mousePressed() {

        String url = "";

        // First btn: 25, asm.sub(height, 75), 150, 50
        if (pmouseX >= 25 && pmouseX <= 25 + 150 && 
            pmouseY >= asm.sub(height, 75) && pmouseY <= asm.sub(height, 75) + 50) {
            url = "http://sasukector.com";
            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Second btn:  225, asm.sub(height, 75), 150, 50
        if (pmouseX >= 225 && pmouseX <= 225 + 150 && 
            pmouseY >= asm.sub(height, 75) && pmouseY <= asm.sub(height, 75) + 50) {
            url = "https://github.com/hectorrdz98/";
            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Third btn:  asm.sub(width, 175), asm.sub(height, 75), 150, 50
        if (pmouseX >= asm.sub(width, 175) && pmouseX <= asm.sub(width, 175) + 150 && 
            pmouseY >= asm.sub(height, 75) && pmouseY <= asm.sub(height, 75) + 50) {
            exit();
        }
    }


    // Scenes

    void scene_loading() {
        // Text Pos setup
        textPos = new int[]{ asm.div(width, 2), asm.div(height, 2) };

        background(0);
        fill(255, 255, 255);
        textSize(30);
        textAlign(CENTER);
        text("CARGANDO...", textPos[0], textPos[1]);

        scene = "setup";
    }

    void scene_setup() {
        // Paddle creation
        leftPaddle = new Paddle( 150, new int[]{ 50, 50 }, 5 );
        rightPaddle = new Paddle( 150, new int[]{ asm.sub(width, 50), 50 }, 5 );

        // Ball creation
        ball = new Ball( new int[]{ width/2, height/2 }, new int[]{ 255, 255, 255 }, new int[]{ 0, 0 } );

        // Correctly position of paddles
        rightPaddle.pos[0] = asm.sub(rightPaddle.pos[0], rightPaddle.width);
        leftPaddle.pos[1] = asm.sub(height / 2, leftPaddle.heigth / 2);
        rightPaddle.pos[1] = asm.sub(height / 2, rightPaddle.heigth / 2);

        // Load sound file
        if (withAudio)
            file = new SoundFile(this, "sample.mp3");
        scene = "init";
    }

    void scene_init() {
        background(0);
        fill(255, 255, 255, textOpacity);
        textSize(textSize);
        textAlign(CENTER);
        text(text, textPos[0], textPos[1]);

        if (textSize < 30) {
            textSize = asm.inc(textSize);
        }

        if (textOpacity < 255) {
            textOpacity = asm.add(textOpacity, 10);
        }

        if (textSize >= 30 &&  textOpacity >= 255) {
            text = "Un juego desarrollado por Hector Rdz";
            textSize = 0;
            textOpacity = 0;
            textCounter = asm.inc(textCounter);
            delay(3000);
        }

        if (textCounter > 1) {
            scene = "menu";
            textSize = 0;
            textOpacity = 0;
            textCounter = 0;
            if (!audioPlaying) {
                if (withAudio)
                    file.loop();
                audioPlaying = true;
            }
            int posBallX = (int) random(-10, 10);
            int posBallY = (int) random(-10, 10);
            while (posBallX == 0) {
                posBallX = (int) random(-10, 10);
            }
            while (posBallY == 0) {
                posBallY = (int) random(-10, 10);
            }
            ball = new Ball( new int[]{ width/2, height/2 }, new int[]{ 255, 255, 255 }, new int[]{ posBallX, posBallY } );
        }
    }

    void scene_menu() {
        background(0);

        // Draw background ball
        if (textCounter == 2) {
            // Ball
            noStroke();
            fill(ball.color[0], ball.color[1], ball.color[2]);
            circle(ball.pos[0], ball.pos[1], ball.radius);

            // Border colitions
            if (asm.add(ball.pos[1], ball.radius / 2) > height || asm.sub(ball.pos[1], ball.radius / 2) < 0) {
                ball.vel[1] = asm.mul(ball.vel[1], -1);
            }
            if (asm.add(ball.pos[0], ball.radius / 2) > width || asm.sub(ball.pos[0], ball.radius / 2) < 0) {
                ball.vel[0] = asm.mul(ball.vel[0], -1);
            }

            // Update ball position
            ball.pos[1] = asm.add(ball.pos[1], ball.vel[1]);
            ball.pos[0] = asm.add(ball.pos[0], ball.vel[0]);
        }

        fill(255, 255, 255, textOpacity);
        textSize(textSize);
        textAlign(CENTER);

        text("Pong", textPos[0], textPos[1]);

        if (textSize < 100) {
            textSize = asm.add(textSize, 2);
            textSize2 = textSize;
        } else {
            if (textCounter == 0) {
                if (textPos[0] > asm.sub(asm.sub(asm.div(width, 2), textSize), 25)) {
                    textPos[0] = asm.sub(textPos[0], 5);
                } else {
                    textCounter = 1;
                }
            } else if (textCounter > 0) {
                fill(102, 102, 255, textOpacity);
                textSize(textSize2);
                text("ASM", asm.div(width, 2) + textSize + 25, textPos[1]);
                textCounter = 2;
            }
        }

        if (textOpacity < 255) {
            textOpacity = asm.add(textOpacity, 10);
        }

        if (textCounter == 2) {
            if (textPos[1] > asm.sub(asm.div(height, 2), 100)) {
                textPos[1] = asm.sub(textPos[1], 2);
            } else {
                fill(191, 191, 191);
                textAlign(CENTER, CENTER);
                textSize(25);
                text("Hector Rdz", asm.div(width, 2) + textSize + 125, textPos[1] + 30);

                if (textSize2 > asm.sub(textSize, asm.div(textSize, 4))) 
                    textSize2 = asm.dec(textSize2);
                else
                    textSize2 = textSize;
                
                // Buttons

                strokeWeight(5);

                fill(102, 102, 255);
                stroke(51, 51, 204);
                rect(25, asm.sub(height, 75), 150, 50);

                fill(255);
                textSize(20);
                textAlign(CENTER, CENTER);
                text("Web", 25, asm.sub(height, 75), 150, 50); 

                fill(102, 102, 255);
                stroke(51, 51, 204);
                rect(225, asm.sub(height, 75), 150, 50);

                fill(255);
                textSize(20);
                textAlign(CENTER, CENTER);
                text("Github", 225, asm.sub(height, 75), 150, 50); 

                fill(204, 0, 102);
                stroke(153, 0, 51);
                rect(asm.sub(width, 175), asm.sub(height, 75), 150, 50);

                fill(255);
                textSize(20);
                textAlign(CENTER, CENTER);
                text("Salir", asm.sub(width, 175), asm.sub(height, 75), 150, 50); 

            }
        }
    }

    void scene_game() {
        background(0);
        drawBall();         // Ball
        drawPaddles();      // Paddles
    }



    // Updatable methods call for drawing

    void drawBall() {
        // Ball
        noStroke();
        fill(ball.color[0], ball.color[1], ball.color[2]);
        circle(ball.pos[0], ball.pos[1], ball.radius);

        // Border colitions
        if (asm.add(ball.pos[1], ball.radius / 2) > height || asm.sub(ball.pos[1], ball.radius / 2) < 0) {
            ball.vel[1] = asm.mul(ball.vel[1], -1);
        }

        // If not game over 
        if (!gameOver) {
            // Right paddle colitions
            if (asm.add(ball.pos[0], ball.radius / 2) >= rightPaddle.pos[0]) {
                if (asm.add(ball.pos[1], ball.radius / 2) >= rightPaddle.pos[1] &&
                    asm.sub(ball.pos[1], ball.radius / 2) <= asm.add(rightPaddle.pos[1], rightPaddle.heigth)) {
                    ball.vel[0] = asm.mul(ball.vel[0], -1);
                    counter = asm.inc(counter);
                }
            }

            // Left paddle colitions
            if (asm.sub(ball.pos[0], ball.radius / 2) <= asm.add(leftPaddle.pos[0], leftPaddle.width)) {
                if (asm.add(ball.pos[1], ball.radius / 2) >= leftPaddle.pos[1] &&
                    asm.sub(ball.pos[1], ball.radius / 2) <= asm.add(leftPaddle.pos[1], leftPaddle.heigth)) {
                    ball.vel[0] = asm.mul(ball.vel[0], -1);
                    counter = asm.inc(counter);
                }    
            }
        }

        // Check if not game over
        if (asm.add(ball.pos[0], ball.radius / 2) > asm.add(rightPaddle.pos[0], rightPaddle.width) ||
            asm.sub(ball.pos[0], ball.radius / 2) < leftPaddle.pos[0] ) {
            gameOver = true;
        }
        
        // Update ball speed
        if (counter == 6) {
            ball.vel[0] = asm.inc(ball.vel[0]);
            ball.vel[1] = asm.inc(ball.vel[1]);
            counter = 0;
        }

        // Update ball position
        ball.pos[1] = asm.add(ball.pos[1], ball.vel[1]);
        ball.pos[0] = asm.add(ball.pos[0], ball.vel[0]);
    }

    void drawPaddles() {
        // Paddles
        noStroke();
        fill(255);
        rect(leftPaddle.pos[0], leftPaddle.pos[1], leftPaddle.width, leftPaddle.heigth);
        rect(rightPaddle.pos[0], rightPaddle.pos[1], rightPaddle.width, rightPaddle.heigth);

        // Check move
        if (leftPaddle.moving != 0) {
            if (leftPaddle.moving == 1) {
                if (asm.add(leftPaddle.pos[1], leftPaddle.heigth) < height) {
                    leftPaddle.pos[1] = asm.add(leftPaddle.pos[1], asm.mul(leftPaddle.vel, leftPaddle.moving));
                }      
            } else {
                if (leftPaddle.pos[1] > 0) {
                    leftPaddle.pos[1] = asm.add(leftPaddle.pos[1], asm.mul(leftPaddle.vel, leftPaddle.moving));
                }
            }
        }
            
        if (rightPaddle.moving != 0) {
            if (rightPaddle.moving == 1) {
                if (asm.add(rightPaddle.pos[1], rightPaddle.heigth) < height) {
                    rightPaddle.pos[1] = asm.add(rightPaddle.pos[1], asm.mul(rightPaddle.vel, rightPaddle.moving));
                }
            } else {
                if (rightPaddle.pos[1] > 0) {
                    rightPaddle.pos[1] = asm.add(rightPaddle.pos[1], asm.mul(rightPaddle.vel, rightPaddle.moving));
                }  
            }  
        }
    }
    
    
    
}