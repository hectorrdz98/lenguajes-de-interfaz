import pyautogui as py
import time

fileName = 'u1/ej20.txt'
delay = 3

file = open(fileName, 'r')

for n in range(delay):
    time.sleep(1)
    print('Ejecutando en',n+1,'s.')

py.typewrite('debug\n')

extrasPart = False

for line in file:
    if not extrasPart:

        commands = line.split(' ')
        time.sleep(0.1)
        py.typewrite(line)

        if commands[0] == 'g\n':
            extrasPart = not extrasPart
            print("\nFinalizada la carga de datos...\
                \nInicializando envio de variables...")
            time.sleep(0.2)
    else:
        if not line == '\n':
            time.sleep(0.1)
            py.typewrite(line)