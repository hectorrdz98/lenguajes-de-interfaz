#include <Python.h>
#include <stdio.h>

static PyObject *hectorASM_ADD(PyObject *self, PyObject *args) {
	int num1, num2;
	if (!PyArg_ParseTuple(args, "ii", &num1, &num2)) {
		return NULL;
	}
	int res = 0;
	__asm__("add %%ebx, %%eax;" : "=a"(res) : "a"(num1), "b"(num2));
	return Py_BuildValue("i", res);
}

static PyObject *hectorASM_SUB(PyObject *self, PyObject *args) {
	int num1, num2;
	if (!PyArg_ParseTuple(args, "ii", &num1, &num2)) {
		return NULL;
	}
	int res = 0;
	__asm__("sub %%ebx, %%eax" : "=a"(res) : "a"(num1), "b"(num2));
	return Py_BuildValue("i", res);
}

static PyObject *hectorASM_INC(PyObject *self, PyObject *args) {
	int num;
	if (!PyArg_ParseTuple(args, "i", &num)) {
		return NULL;
	}
	__asm__("inc %%eax" : "=a"(num) : "a"(num));
	return Py_BuildValue("i", num);
}

static PyObject *hectorASM_DEC(PyObject *self, PyObject *args) {
	int num;
	if (!PyArg_ParseTuple(args, "i", &num)) {
		return NULL;
	}
	__asm__("dec %%eax" : "=a"(num) : "a"(num));
	return Py_BuildValue("i", num);
}

static PyObject *hectorASM_MUL(PyObject *self, PyObject *args) {
	int num1, num2;
	if (!PyArg_ParseTuple(args, "ii", &num1, &num2)) {
		return NULL;
	}
	int res = 0;
	__asm__("imul %%ebx, %%eax" : "=a"(res) : "a"(num1), "b"(num2));
	return Py_BuildValue("i", res);
}

static PyObject *hectorASM_DIV(PyObject *self, PyObject *args) {
	int num1, num2;
	if (!PyArg_ParseTuple(args, "ii", &num1, &num2)) {
		return NULL;
	}
	int res = 0;
	__asm__("movl $0x0, %edx;");
    __asm__("movl %0, %%eax;" : "=a"(num1) : "g" (num1));
    __asm__("movl %0, %%ebx;" : "=b"(num2) : "g" (num2));
    __asm__("idivl %%ebx;" : "=a"(res));
	return Py_BuildValue("i", res);
}

static PyMethodDef hectorASM_methods[] = {
	// "PythonName"		C-function Name		argument presentation		description
	{"ADD", hectorASM_ADD, METH_VARARGS, "Add two integers"},
	{"SUB", hectorASM_SUB, METH_VARARGS, "Substract two integers"},
	{"INC", hectorASM_INC, METH_VARARGS, "Increment by 1 an integer"},
	{"DEC", hectorASM_DEC, METH_VARARGS, "Decrement by 1 an integer"},
	{"MUL", hectorASM_MUL, METH_VARARGS, "Multiply two integers"},
	{"DIV", hectorASM_DIV, METH_VARARGS, "Divide two integers"},
	{NULL, NULL, 0, NULL}	/* Sentinel */

};

static PyModuleDef hectorASM_module = {
    PyModuleDef_HEAD_INIT,
    "hectorASM",                       
    "My own ASM functions for python",
    0,
    hectorASM_methods                 
};

PyMODINIT_FUNC PyInit_hectorASM() {
    return PyModule_Create(&hectorASM_module);
}
