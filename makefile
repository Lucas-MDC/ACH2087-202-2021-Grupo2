clean:
	-rm *.class
	clear

all:
	jlex cmenos.jlex
	javac Main.java Parser.java cmenos.jlex.java
	clear
	java Main