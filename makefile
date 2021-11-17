clean:
	-rm *.class
	clear

lex:
	jlex cmenos.jlex

buildlex:
	javac Main.java Compilador.java cmenos.jlex.java Lexico.java

buildparser:
	bison cmenos.bison --output=Calc.java 
	java Calc.java >> infix

teste1:
	java Main tests/prg4.c- output.lolcode

teste1zuado:
	java Main tests/program1_zuado.c output.lolcode