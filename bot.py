import pyautogui as py
import time

fileName = 'u1/ex.txt'
delay = 3

file = open(fileName, 'r')

for n in range(delay):
    time.sleep(1)
    print('Ejecutando en',delay-n,'s.')

py.typewrite('debug\n')

extrasPart = False

for line in file:
    if not extrasPart:

        commands = line.split(' ')
        time.sleep(0.2)

        #print(commands)

        for command in commands:
            for char in command:
                py.typewrite(char)
            if command == 'g\n':
                extrasPart = not extrasPart
                print("\nFinalizada la carga de datos...\
                    \nInicializando envio de variables...\n")
                time.sleep(0.2)
            else:
                    py.typewrite(' ')

    else:
        if not line == '\n':
            print("Mando un:", [line])
            time.sleep(0.1)
            py.typewrite(line)