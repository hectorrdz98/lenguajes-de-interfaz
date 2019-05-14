extern _printf
global say_hello

section .data
msg: db "Hello, world!",10,0

section .text
say_hello:
    push msg
    call _printf
    add esp,4   
    ret