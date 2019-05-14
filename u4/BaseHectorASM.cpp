#include <iostream>

using namespace std;

int add(int n1, int n2) {
	int res = 0;
	asm("add %%ebx, %%eax" : "=a"(res) : "a"(n1), "b"(n2));
	return res;
}

int sub(int n1, int n2) {
	int res = 0;
	asm("sub %%ebx, %%eax" : "=a"(res) : "a"(n1), "b"(n2));
	return res;
}

int inc(int n) {
	asm("inc %%eax" : "=a"(n) : "a"(n));
	return n;
}

int dec(int n) {
	asm("dec %%eax" : "=a"(n) : "a"(n));
	return n;
}

int mul(int n1, int n2) {
	int res = 0;
	asm("imul %%ebx, %%eax" : "=a"(res) : "a"(n1), "b"(n2));
	return res;
}

int * div(int n1, int n2) {
	static int res[] = {0, 0};
	asm("movl $0x0, %%edx;"
        "movl %2, %%eax;"
        "movl %3, %%ebx;"
        "idivl %%ebx;" : "=a" (res[0]), "=d" (res[1]) : "g" (n1), "g" (n2));
	return res;
}

float addf(float n1, float n2) {
	float res = 0;
	asm("fadd %2, %0"
		: "=&t" (res) : "%0" (n1), "u" (n2));
	return res;
}

/*
float subf(float n1, float n2) {
	float res = 0;
	asm("fld %2;"
        "fld %1;"
        "fsub;"
        "fstp %0;" : "=g" (res) : "g" (n1), "g" (n2)) ;
	return res;
}

float incf(float n) {
	asm("fld $1;"
        "fld %1;"
        "fadd;"
        "fstp %0;" : "=g" (n) : "g" (n)) ;
	return n;
}

float decf(float n) {
	asm("fld %1;"
        "fld $1;"
        "fsub;"
        "fstp %0;" : "=g" (n) : "g" (n)) ;
	return n;
}

float mulf(float n1, float n2) {
	float res = 0;
	asm("fld %1;"
        "fld %2;"
        "fmul;"
        "fstp %0;" : "=g" (res) : "g" (n1), "g" (n2)) ;
	return res;
}

float divf(float n1, int n2) {
	float res = 0;
	asm("fld %2;"
        "fld %1;"
        "fdiv;"
        "fstp %0;" : "=g" (res) : "g" (n1), "g" (n2)) ;

	return res;
}
*/
int main() {
	int n1 = 5;
	int n2 = 2;
	
	cout << n1 << " + " << n2 << " = " << add(n1, n2) << endl;
	cout << n1 << " - " << n2 << " = " << sub(n1, n2) << endl;
	cout << n1 << " ++  = " << inc(n1) << endl;
	cout << n1 << " --  = " << dec(n1) << endl;
	cout << n1 << " x " << n2 << " = " << mul(n1, n2) << endl;
	cout << n1 << " / " << n2 << " = " << div(n1, n2)[0] << endl;
	cout << n1 << " % " << n2 << " = " << div(n1, n2)[1] << endl;
	
	float n3 = 12.4;
	float n4 = 2.3;
	
	cout << endl << "Ahora el flotante: " << endl;
	
	cout << n3 << " + " << n4 << " = " << addf(n3, n4) << endl;
	
	/*
	cout << n3 << " - " << n4 << " = " << subf(n3, n4) << endl;
	cout << n3 << " ++  = " << incf(n3) << endl;
	cout << n3 << " --  = " << decf(n3) << endl;
	cout << n3 << " x " << n4 << " = " << mulf(n3, n4) << endl;
	cout << n3 << " / " << n4 << " = " << divf(n3, n4) << endl;*/
	
	return 0;
}
