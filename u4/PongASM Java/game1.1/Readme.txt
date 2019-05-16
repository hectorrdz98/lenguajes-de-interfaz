
To create and extern library using Java and C

1.- Create HelloJNI.java

public class HelloJNI {  // Save as HelloJNI.java
   static {
      System.loadLibrary("hello"); // Load native library hello.dll (Windows) or libhello.so (Unixes)
                                   //  at runtime
                                   // This library contains a native method called sayHello()
   }
 
   // Declare an instance native method sayHello() which receives no parameter and returns void
   private native void sayHello();
 
   // Test Driver
   public static void main(String[] args) {
      new HelloJNI().sayHello();  // Create an instance and invoke the native method
   }
}

2.- javac -h . HelloJNI.java

That generates de .h and .class

3.- Create a HelloJNI.c

// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "HelloJNI.h"   // Generated
 
// Implementation of the native method sayHello()
JNIEXPORT void JNICALL Java_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}

4.- x86_64-w64-mingw32-gcc -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -shared -o hello.dll HelloJNI.c

That compiles... The steps are:

// Compile-only "HelloJNI.c" with -c flag. Output is "HElloJNI.o"
4.1.- x86_64-w64-mingw32-gcc -c -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" HelloJNI.c

// Link "HelloJNO.o" into shared library "hello.dll"
4.2.- x86_64-w64-mingw32-gcc -shared -o hello.dll HelloJNI.o

Now you can run the program with

5.- java HelloJNI





To compile using an extern jar:

1.- import processing.core.Class
2.- javac -cp .;processing.core.jar mysource.java
3.- java -cp .;processing.core.jar mysource


Building jar

4.- jar -cvfm test.jar MANIFEST.MF -C ./ .         -> Note: With a breakline on MANIFEST.MF
5.- java -jar test.jar


MANIFEST.MF

Manifest-Version: 1.0
Created-By: 1.8.0_191 (Oracle Corporation)
Class-Path: processing.core.jar
Main-Class: Prueba
\n
