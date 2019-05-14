import time

num = 0
t0 = time.time()

import hectorASM as asm

for n in range(10000000):
    num = asm.ADD(num, 1)

t1 = time.time()

total = t1-t0
print('Prueba con hectorASM:')
print('Num: {}'.format(num))
print('Total time: {}'.format(total))

num = 0
t0 = time.time()

import hectorASM as asm

for n in range(10000000):
    num += 1

t1 = time.time()

total = t1-t0
print('\nPrueba normal:')
print('Num: {}'.format(num))
print('Total time: {}'.format(total))