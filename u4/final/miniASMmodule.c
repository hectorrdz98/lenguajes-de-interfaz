#include <stdio.h>

int main() {
	
	int a = 3;
	int b = 5;
	int res = 0;
	
	asm("add %%ebx, %%eax;" : "=a"(res) : "a"(a), "b"(b));
	
	printf("%i + %i = %i", a, b, res);

	return res;
}
