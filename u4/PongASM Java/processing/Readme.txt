
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
