#include <jni.h>
#include <stdio.h>
#include "HectorASM.h"
 
JNIEXPORT jint JNICALL Java_HectorASM_add
          (JNIEnv *env, jobject thisObj, jint n1, jint n2) {
    jint result;
    __asm__("add %%ebx, %%eax;" : "=a"(result) : "a"(n1), "b"(n2));
    return result;
}

JNIEXPORT jint JNICALL Java_HectorASM_sub
          (JNIEnv *env, jobject thisObj, jint n1, jint n2) {
    jint result;
    __asm__("sub %%ebx, %%eax;" : "=a"(result) : "a"(n1), "b"(n2));
    return result;
}

JNIEXPORT jint JNICALL Java_HectorASM_mul
          (JNIEnv *env, jobject thisObj, jint n1, jint n2) {
    jint result;
    __asm__("imul %%ebx, %%eax" : "=a"(result) : "a"(n1), "b"(n2));
    return result;
}

JNIEXPORT jint JNICALL Java_HectorASM_div
          (JNIEnv *env, jobject thisObj, jint n1, jint n2) {
    jint result;
    jint extra;
    __asm__("movl $0x0, %%edx;"
        "movl %2, %%eax;"
        "movl %3, %%ebx;"
        "idivl %%ebx;" : "=a" (result), "=d" (extra) : "g" (n1), "g" (n2));
    return result;
}

JNIEXPORT jint JNICALL Java_HectorASM_inc
          (JNIEnv *env, jobject thisObj, jint n1) {
    __asm__("inc %%eax" : "=a"(n1) : "a"(n1));
    return n1;
}

JNIEXPORT jint JNICALL Java_HectorASM_dec
          (JNIEnv *env, jobject thisObj, jint n1) {
    __asm__("dec %%eax" : "=a"(n1) : "a"(n1));
    return n1;
}