clean:
	-rm *.class
	clear

lex:
	jlex cmenos.jlex

build:
	jlex cmenos.jlex
	javac Main.java Compilador.java cmenos.jlex.java Lexico.java

test1:
	java Main tests/program1_safe.c

test2:
	java Main tests/program2_safe.c