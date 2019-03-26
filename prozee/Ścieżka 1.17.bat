@ECHO OFF
set path="C:\Program Files\Java\jdk-11\bin";
TITLE sciezka
javac -d class ./src/playerpaczka/*.java


set CLASSPATH=.\class
java playerpaczka.Main

PAUSE