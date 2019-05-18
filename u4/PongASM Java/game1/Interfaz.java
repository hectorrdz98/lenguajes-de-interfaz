
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
    int points = 0;

    int textSize = 0;
    int textSize2 = 0;
    int textOpacity = 0;
    int textCounter = 0;
    int textPos[];
    String text = "Este proyecto fue creado utilizando asm en C \npara todas las operaciones matem\u00e1ticas";

    int backgroundColor[];

    SoundFile file;
    SoundFile pong;
    boolean audioPlaying = false;
    boolean withAudio = false;

    boolean keysPressed[] = { false, false, false, false };

    Interfaz (HectorASM asm) {
        this.asm = asm;
    }

    public void settings() { 
        size(1280, 720);
    }

    public void setup() {
        // All in scene_setup()
        surface.setTitle("PongASM");
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
            // If not gameOver
            if (!gameOver) {
                // Check if ball isn't moving
                if (ball.vel[0] == 0 && ball.vel[1] == 0) {
                    int posBallX = (int) random(3, 5);
                    int posBallY = (int) random(3, 5);
                    if ((int) random(1, 2) == 1) {
                        posBallX = asm.mul(posBallX, -1);
                    }
                    if ((int) random(1, 2) == 1) {
                        posBallY = asm.mul(posBallY, -1);
                    }
                    ball.vel[0] = posBallX;
                    ball.vel[1] = posBallY;
                }

                // Left paddle move
                if (key == 'w') {
                    // leftPaddle.moving = -1;
                    keysPressed[0] = true;
                } else if (key == 's') {
                    // leftPaddle.moving = 1;
                    keysPressed[1] = true;
                }

                // Right paddle move
                if (keyCode == UP) {
                    // rightPaddle.moving = -1;
                    keysPressed[2] = true;
                } else if (keyCode == DOWN) {
                    // rightPaddle.moving = 1;
                    keysPressed[3] = true;
                }

            } else {
                if (key == 'r') {
                    gameOver = false;
                    counter = 0;
                    points = 0;
                    backgroundColor = new int[]{0, 0, 0};

                    // Paddle creation
                    leftPaddle = new Paddle( 100, new int[]{ 50, 50 }, 10 );
                    rightPaddle = new Paddle( 100, new int[]{ asm.sub(width, 50), 50 }, 10 );
                    keysPressed = new boolean[]{ false, false, false, false };

                    // Ball creation
                    ball = new Ball( new int[]{ asm.div(width, 2), asm.div(height, 2) }, new int[]{ 255, 255, 255 }, new int[]{ 0, 0 } );

                    // Correctly position of paddles
                    rightPaddle.pos[0] = asm.sub(rightPaddle.pos[0], rightPaddle.width);
                    leftPaddle.pos[1] = asm.sub(asm.div(height, 2), asm.div(leftPaddle.heigth, 2));
                    rightPaddle.pos[1] = asm.sub(asm.div(height, 2), asm.div(rightPaddle.heigth, 2));
                }   
            }
        }         
    }

    public void keyReleased() {
        if (scene.equals("game")) {
            // If not gameOver
            if (!gameOver) {
                // Left paddle move
                if (key == 'w') {
                    keysPressed[0] = false;
                } else if (key == 's') {
                    keysPressed[1] = false;
                }

                // Right paddle move
                if (keyCode == UP) {
                    keysPressed[2] = false;
                } else if (keyCode == DOWN) {
                    keysPressed[3] = false;
                }

            }
        }         
    }

    public void mousePressed() {

        String url = "";

        // First btn: 25, asm.sub(height, 75), 150, 50
        if (pmouseX >= 25 && pmouseX <= asm.add(25, 150) && 
            pmouseY >= asm.sub(height, 75) && pmouseY <= asm.add(asm.sub(height, 75), 50)) {
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
        if (pmouseX >= 225 && pmouseX <= asm.add(225, 150) && 
            pmouseY >= asm.sub(height, 75) && pmouseY <= asm.add(asm.sub(height, 75), 50)) {
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
        if (pmouseX >= asm.sub(width, 175) && pmouseX <= asm.add(asm.sub(width, 175), 150) && 
            pmouseY >= asm.sub(height, 75) && pmouseY <= asm.add(asm.sub(height, 75), 50)) {
            exit();
        }

        // Play btn: asm.sub(asm.div(width, 2), 150), asm.div(height, 2), 250, 75
        if (pmouseX >= asm.sub(asm.div(width, 2), 150) && pmouseX <= asm.add(asm.sub(asm.div(width, 2), 150), 250) && 
            pmouseY >= asm.div(height, 2) && pmouseY <= asm.add(asm.div(height, 2), 75)) {
            ball = new Ball( new int[]{ width/2, height/2 }, new int[]{ 255, 255, 255 }, new int[]{ 0, 0 } );
            scene = "game";
            backgroundColor = new int[]{0, 0, 0};
            cursor(ARROW);
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
        leftPaddle = new Paddle( 100, new int[]{ 50, 50 }, 10 );
        rightPaddle = new Paddle( 100, new int[]{ asm.sub(width, 50), 50 }, 10 );

        // Ball creation
        ball = new Ball( new int[]{ asm.div(width, 2), asm.div(height, 2) }, new int[]{ 255, 255, 255 }, new int[]{ 0, 0 } );

        // Correctly position of paddles
        rightPaddle.pos[0] = asm.sub(rightPaddle.pos[0], rightPaddle.width);
        leftPaddle.pos[1] = asm.sub(asm.div(height, 2), asm.div(leftPaddle.heigth, 2));
        rightPaddle.pos[1] = asm.sub(asm.div(height, 2), asm.div(rightPaddle.heigth, 2));

        // Load sound file
        if (withAudio)
            file = new SoundFile(this, "menu.mp3");
        pong = new SoundFile(this, "pong.mp3");
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
            if (textCounter == 1) {
                delay(5000);
            } else {
                delay(3000);
            }
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
            ball = new Ball( new int[]{ asm.div(width, 2), asm.div(height, 2) }, new int[]{ 255, 255, 255 }, new int[]{ posBallX, posBallY } );
        }
    }

    void scene_menu() {
        background(0);

        // Draw background ball
        if (textCounter == 2 && textPos[1] <= asm.sub(asm.div(height, 2), 100)) {
            // Ball
            noStroke();
            fill(ball.color[0], ball.color[1], ball.color[2]);
            circle(ball.pos[0], ball.pos[1], ball.radius);

            // Border colitions
            if (asm.add(ball.pos[1], asm.div(ball.radius, 2)) > height || asm.sub(ball.pos[1], asm.div(ball.radius, 2)) < 0) {
                ball.vel[1] = asm.mul(ball.vel[1], -1);
            }
            if (asm.add(ball.pos[0], asm.div(ball.radius, 2)) > width || asm.sub(ball.pos[0], asm.div(ball.radius, 2)) < 0) {
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
                text("ASM", asm.add(asm.add(asm.div(width, 2), textSize), 25), textPos[1]);
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
                text("Hector Rdz", asm.add(asm.add(asm.div(width, 2), textSize), 125), asm.add(textPos[1], 30));

                if (textSize2 > asm.sub(textSize, asm.div(textSize, 4))) 
                    textSize2 = asm.dec(textSize2);
                else
                    textSize2 = textSize;
                
                // Buttons

                int hovers = 0;

                strokeWeight(5);

                // Btn 1

                if (pmouseX >= 25 && pmouseX <= asm.add(25, 150) && 
                    pmouseY >= asm.sub(height, 75) && pmouseY <= asm.add(asm.sub(height, 75), 50)) {
                    fill(51, 51, 204);
                    stroke(36, 36, 143); 
                    hovers = asm.inc(hovers);
                } else {
                    fill(102, 102, 255);
                    stroke(51, 51, 204);
                }
                rect(25, asm.sub(height, 75), 150, 50);

                fill(255);
                textSize(20);
                textAlign(CENTER, CENTER);
                text("Web", 25, asm.sub(height, 75), 150, 50); 

                // Btn 2

                if (pmouseX >= 225 && pmouseX <= asm.add(225, 150) && 
                    pmouseY >= asm.sub(height, 75) && pmouseY <= asm.add(asm.sub(height, 75), 50)) {
                    fill(51, 51, 204);
                    stroke(36, 36, 143); 
                    hovers = asm.inc(hovers);
                } else {
                    fill(102, 102, 255);
                    stroke(51, 51, 204);
                }
                rect(225, asm.sub(height, 75), 150, 50);

                fill(255);
                textSize(20);
                textAlign(CENTER, CENTER);
                text("Github", 225, asm.sub(height, 75), 150, 50); 

                // Btn 3

                if (pmouseX >= asm.sub(width, 175) && pmouseX <= asm.add(asm.sub(width, 175), 150) && 
                    pmouseY >= asm.sub(height, 75) && pmouseY <= asm.add(asm.sub(height, 75), 50)) {
                    fill(153, 0, 51);
                    stroke(102, 0, 34); 
                    hovers = asm.inc(hovers);
                } else {
                    fill(204, 0, 102);
                    stroke(153, 0, 51);
                }
                rect(asm.sub(width, 175), asm.sub(height, 75), 150, 50);

                fill(255);
                textSize(20);
                textAlign(CENTER, CENTER);
                text("Salir", asm.sub(width, 175), asm.sub(height, 75), 150, 50); 

                // Play button

                if (pmouseX >= asm.sub(asm.div(width, 2), 150) && pmouseX <= asm.add(asm.sub(asm.div(width, 2), 150), 250) && 
                    pmouseY >= asm.div(height, 2) && pmouseY <= asm.add(asm.div(height, 2), 75)) {
                    fill(51, 51, 204);
                    stroke(36, 36, 143); 
                    hovers = asm.inc(hovers);
                } else {
                    fill(102, 102, 255);
                    stroke(51, 51, 204);
                }
                rect(asm.sub(asm.div(width, 2), 150), asm.div(height, 2), 250, 75);

                fill(255);
                textSize(40);
                textAlign(CENTER, CENTER);
                text("Jugar", asm.sub(asm.div(width, 2), 150), asm.div(height, 2), 250, 65); 

                // Put hand cursor if hover
                if (hovers > 0) {
                    cursor(HAND);
                } else {
                    cursor(ARROW);
                }

            }
        }
    }

    void scene_game() {
        noCursor();
        background(backgroundColor[0], backgroundColor[1], backgroundColor[2]);
        drawBall();         // Ball
        drawPaddles();      // Paddles
        drawUI();           // Texts
    }



    // Updatable methods call for drawing

    void drawBall() {
        // Ball
        noStroke();
        fill(ball.color[0], ball.color[1], ball.color[2]);
        circle(ball.pos[0], ball.pos[1], ball.radius);

        // Border colitions
        if (asm.add(ball.pos[1], asm.div(ball.radius, 2)) > height || asm.sub(ball.pos[1], asm.div(ball.radius, 2)) < 0) {
            ball.vel[1] = asm.mul(ball.vel[1], -1);
        }

        // If not game over 
        if (!gameOver) {
            // Right paddle colitions
            if (asm.add(ball.pos[0], asm.div(ball.radius, 2)) >= rightPaddle.pos[0]) {
                if (asm.add(ball.pos[1], asm.div(ball.radius, 2)) >= rightPaddle.pos[1] &&
                    asm.sub(ball.pos[1], asm.div(ball.radius, 2)) <= asm.add(rightPaddle.pos[1], rightPaddle.heigth)) {
                    ball.vel[0] = asm.mul(ball.vel[0], -1);
                    counter = asm.inc(counter);
                    points = asm.inc(points);
                    pong.play();
                }
            }

            // Left paddle colitions
            if (asm.sub(ball.pos[0], asm.div(ball.radius, 2)) <= asm.add(leftPaddle.pos[0], leftPaddle.width)) {
                if (asm.add(ball.pos[1], asm.div(ball.radius, 2)) >= leftPaddle.pos[1] &&
                    asm.sub(ball.pos[1], asm.div(ball.radius, 2)) <= asm.add(leftPaddle.pos[1], leftPaddle.heigth)) {
                    ball.vel[0] = asm.mul(ball.vel[0], -1);
                    counter = asm.inc(counter);
                    points = asm.inc(points);
                    pong.play();
                }    
            }
        }

        // Check if not game over
        if (asm.add(ball.pos[0], asm.div(ball.radius, 2)) > asm.add(rightPaddle.pos[0], rightPaddle.width) ||
            asm.sub(ball.pos[0], asm.div(ball.radius, 2)) < leftPaddle.pos[0] ) {
            gameOver = true;
            ball.vel[0] = 0;
            ball.vel[1] = 0;
        }
        
        // Update ball speed
        if (counter == 5) {
            if (ball.vel[0] > 0) {
                ball.vel[0] = asm.add(ball.vel[0], (int) random(1, 3));
                if ((int) random(0, 10) > 5)
                    ball.vel[1] = asm.add(ball.vel[1],(int) random(1, 2));
            } else if (ball.vel[0] < 0) {
                ball.vel[0] = asm.sub(ball.vel[0], (int) random(1, 3));
                if ((int) random(0, 10) > 5)
                    ball.vel[1] = asm.sub(ball.vel[1], (int) random(1, 2));
            }

            backgroundColor = new int[]{ (int) random(0, 100), (int) random(0, 100), (int) random(0, 100) };

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

        // if not gameOver
        if (!gameOver) {

            // Check move

            // W key is pressed
            if (keysPressed[0] == true) {
                if (leftPaddle.pos[1] > 0) {
                    leftPaddle.pos[1] = asm.add(leftPaddle.pos[1], asm.mul(leftPaddle.vel, -1));
                }      
            }
            
            // S key is pressed
            if (keysPressed[1]) {
                if (asm.add(leftPaddle.pos[1], leftPaddle.heigth) < height) {
                    leftPaddle.pos[1] = asm.add(leftPaddle.pos[1], leftPaddle.vel);
                }
            }

            // UP key is pressed    
            if (keysPressed[2]) {
                if (rightPaddle.pos[1] > 0) {
                    rightPaddle.pos[1] = asm.add(rightPaddle.pos[1], asm.mul(rightPaddle.vel, -1));
                }
            } 
            
            // DOWN key is pressed
            if (keysPressed[3]) {
                if (asm.add(rightPaddle.pos[1], rightPaddle.heigth) < height) {
                    rightPaddle.pos[1] = asm.add(rightPaddle.pos[1], rightPaddle.vel);
                }  
            }  

            /*
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
            */
        }
    }

    void drawUI() {
        fill(255);
        textSize(40);
        textAlign(CENTER);
        text(points, asm.div(width, 2), 50);

        // If gameOver
        if (gameOver) {
            fill(255);
            textSize(80);
            textAlign(CENTER);
            text("Fin del juego", asm.div(width, 2), asm.div(height, 2));
            textSize(25);
            textAlign(CENTER);
            text("Pulsa 'r' para reiniciar", asm.div(width, 2), asm.add(asm.div(height, 2), 50));
        }
    }
    
    
    
}