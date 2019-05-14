import pygame,sys, random

#initializes video
pygame.init()

#sets the screen size
screen = pygame.display.set_mode((520, 350))

while (1):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
    color = (255, 255, 255)
    screen.fill(color)

    #draws a rectangle on screen with color (r,g,b) at loc (x,y) of width w, height h.

    '''
    numberOfRows = 20
    numberOfColumns = 13
    top = 32
    bottom = 42
    for y in range(0, numberOfRows * top, bottom):
        for x in range(0, numberOfColumns * top, bottom):
            pygame.draw.rect(screen, (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)), pygame.Rect(x, y, 40, 30))
    ''' 
    #first row
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(0, 0, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(40, 0, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(80, 0, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(120, 0, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(160, 0, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(200, 0, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(240, 0, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(280, 0, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(320, 0, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(360, 0, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(400, 0, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(440, 0, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(480, 0, 40, 30))

    #second row
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(0, 30, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(40, 30, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(80, 30, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(120, 30, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(160, 30, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(200, 30, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(240, 30, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(280, 30, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(320, 30, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(360, 30, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(400, 30, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(440, 30, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(480, 30, 40, 30))

    #third row
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(0, 60, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(40, 60, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(80, 60, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(120, 60, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(160, 60, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(200, 60, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(240, 60, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(280, 60, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(320, 60, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(360, 60, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(400, 60, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(440, 60, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(480, 60, 40, 30))

    #fourth row
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(0, 90, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(40, 90, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(80, 90, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(120, 90, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(160, 90, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(200, 90, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(240, 90, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(280, 90, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(320, 90, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(360, 90, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(400, 90, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(440, 90, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(480, 90, 40, 30))

    #fifth row
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(0, 120, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(40, 120, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(80, 120, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(120, 120, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(160, 120, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(200, 120, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(240, 120, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(280, 120, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(320, 120, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(360, 120, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(400, 120, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(440, 120, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(480, 120, 40, 30))

    #sixth row
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(0, 150, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(40, 150, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(80, 150, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(120, 150, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(160, 150, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(200, 150, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(240, 150, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(280, 150, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(320, 150, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(360, 150, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(400, 150, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(440, 150, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(480, 150, 40, 30))

    #seventh row
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(0, 180, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(40, 180, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(80, 180, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(120, 180, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(160, 180, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(200, 180, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(240, 180, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(280, 180, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(320, 180, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(360, 180, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(400, 180, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(440, 180, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(480, 180, 40, 30))

    #eighth row
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(0, 210, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(40, 210, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(80, 210, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(120, 210, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(160, 210, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(200, 210, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(240, 210, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(280, 210, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(320, 210, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(360, 210, 40, 30))
    pygame.draw.rect(screen, (255, 0, 0), pygame.Rect(400, 210, 40, 30))
    pygame.draw.rect(screen, (0, 255, 0), pygame.Rect(440, 210, 40, 30))
    pygame.draw.rect(screen, (0, 0, 255), pygame.Rect(480, 210, 40, 30))

    pygame.display.flip()