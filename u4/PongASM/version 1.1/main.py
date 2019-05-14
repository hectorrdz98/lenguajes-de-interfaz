from p5 import *
import hectorASM as asm

class Paddel:
    def __init__(self, height, pos, vel):
        self.width = 50
        self.heigth = height
        self.pos = pos
        self.vel = vel
        self.moving = 0



leftPaddel = None
rightPaddle = None

keysPressing = []



def setup():
    global leftPaddel, rightPaddle

    title('PongASM')
    size(1280, 720)

    # Paddle creation
    leftPaddel = Paddel(
        height = 150,
        pos = {'x': 50, 'y' : 50}, 
        vel = 10
    )
    rightPaddle = Paddel(
        height = 150,
        pos = {'x': asm.ADD(width, -50), 'y' : 50}, 
        vel = 10
    )

    # Correctly position the paddles
    rightPaddle.pos['x'] = asm.ADD(rightPaddle.pos['x'], -rightPaddle.width)
    leftPaddel.pos['y'] = asm.ADD(int(height / 2), -int(leftPaddel.heigth / 2))
    rightPaddle.pos['y'] = asm.ADD(int(height / 2), -int(rightPaddle.heigth / 2))
    


def draw():
    global leftPaddel, rightPaddle

    background(0)

    # Paddles
    no_stroke()
    fill(255)
    rect(( leftPaddel.pos['x'], leftPaddel.pos['y'] ), leftPaddel.width, leftPaddel.heigth)
    rect(( rightPaddle.pos['x'], rightPaddle.pos['y'] ), rightPaddle.width, rightPaddle.heigth)

    # Check move
    if leftPaddel.moving != 0:
        leftPaddel.pos['y'] = asm.ADD(leftPaddel.pos['y'], leftPaddel.vel * leftPaddel.moving)
    if rightPaddle.moving != 0:
        rightPaddle.pos['y'] = asm.ADD(rightPaddle.pos['y'], rightPaddle.vel * rightPaddle.moving)

def key_pressed():
    # Left paddle move
    if key == 'W':
        leftPaddel.moving = -1
    elif key == 'S':
        leftPaddel.moving = 1
    
    # Right paddle move
    if key == 'UP':
        rightPaddle.moving = -1
    elif key == 'DOWN':
        rightPaddle.moving = 1

if __name__ == '__main__':
    run()