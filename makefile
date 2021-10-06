clean:
	-rm *.class
	clear

make lex:
	jlex cmenos.jlex

all:
	jlex cmenos.jlex
	javac Main.java Compilador.java cmenos.jlex.java Lexico.java
	clear
	java Main
	-rm *.class