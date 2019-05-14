from p5 import *
import hectorASM as asm

class Paddel:
    def __init__(self, height, pos, vel):
        self.width = 50
        self.heigth = height
        self.pos = pos
        self.vel = vel
        self.moving = 0

ball = {
    'radius' : 50,
    'pos' : [50, 50],
    'color' : [255, 255, 255],
    'vel' : [10, 10]
}

leftPaddel = None
rightPaddle = None

borde = 0

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
        pos = {'x': asm.SUB(width, 50), 'y' : 50}, 
        vel = 10
    )

    ball['pos'] = [ width/2, height/2 ]

    # Correctly position the paddles
    rightPaddle.pos['x'] = asm.SUB(rightPaddle.pos['x'], rightPaddle.width)
    leftPaddel.pos['y'] = asm.SUB(int(height / 2), int(leftPaddel.heigth / 2))
    rightPaddle.pos['y'] = asm.SUB(int(height / 2), int(rightPaddle.heigth / 2))
    


def draw():
    global leftPaddel, rightPaddle

    background(0)

    # Ball
    no_stroke()
    fill(ball['color'][0], ball['color'][1], ball['color'][2])
    circle((ball['pos'][0], ball['pos'][1]), ball['radius'])
    
    # Border colitions
    if asm.ADD(int(ball['pos'][1]), int(ball['radius'] / 2)) > height or asm.SUB(int(ball['pos'][1]), int(ball['radius'] / 2)) < 0:
        ball['vel'][1] = asm.MUL(ball['vel'][1], -1)

    # Right paddle colitions
    if asm.ADD(int(ball['pos'][0]), int(ball['radius'] / 2)) >= rightPaddle.pos['x']:
        if asm.ADD(int(ball['pos'][1]), int(ball['radius'] / 2)) >= rightPaddle.pos['y'] and asm.SUB(int(ball['pos'][1]), int(ball['radius'] / 2)) <= asm.ADD(int(rightPaddle.pos['y']), rightPaddle.heigth):
            ball['vel'][0] = asm.MUL(ball['vel'][0], -1)  

    # Left paddle colitions
    if asm.ADD(int(ball['pos'][0]), int(ball['radius'] / 2)) <= asm.ADD(int(leftPaddel.pos['x']), asm.MUL(leftPaddel.width, 2)):
        if asm.ADD(int(ball['pos'][1]), int(ball['radius'] / 2)) >= leftPaddel.pos['y'] and asm.SUB(int(ball['pos'][1]), int(ball['radius'] / 2)) <= asm.ADD(int(leftPaddel.pos['y']), leftPaddel.heigth):
            ball['vel'][0] = asm.MUL(ball['vel'][0], -1)  

    # Update ball position
    ball['pos'][1] = asm.ADD(int(ball['pos'][1]), ball['vel'][1])
    ball['pos'][0] = asm.ADD(int(ball['pos'][0]), ball['vel'][0])

    # Paddles
    no_stroke()
    fill(255)
    rect(( leftPaddel.pos['x'], leftPaddel.pos['y'] ), leftPaddel.width, leftPaddel.heigth)
    rect(( rightPaddle.pos['x'], rightPaddle.pos['y'] ), rightPaddle.width, rightPaddle.heigth)

    # Check move
    if leftPaddel.moving != 0:
        if leftPaddel.moving == 1:
            if asm.ADD(asm.ADD(leftPaddel.pos['y'], leftPaddel.heigth), borde) < height:
                leftPaddel.pos['y'] = asm.ADD(leftPaddel.pos['y'], asm.MUL(leftPaddel.vel, leftPaddel.moving))
        else:
            if asm.ADD(leftPaddel.pos['y'], borde) > 0:
                leftPaddel.pos['y'] = asm.ADD(leftPaddel.pos['y'], asm.MUL(leftPaddel.vel, leftPaddel.moving))
    if rightPaddle.moving != 0:
        if rightPaddle.moving == 1:
            if asm.ADD(asm.ADD(rightPaddle.pos['y'], rightPaddle.heigth), borde) < height:
                rightPaddle.pos['y'] = asm.ADD(rightPaddle.pos['y'], asm.MUL(rightPaddle.vel, rightPaddle.moving))
        else:
            if asm.ADD(rightPaddle.pos['y'], borde) > 0:
                rightPaddle.pos['y'] = asm.ADD(rightPaddle.pos['y'], asm.MUL(rightPaddle.vel, rightPaddle.moving))

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