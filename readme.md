## Refs

* JLEX Manual: http://pages.cs.wisc.edu/~fischer/cs536.s05/course.hold/html/NOTES/manual.html#SECTION2.1
* JLEX: http://pages.cs.wisc.edu/~fischer/cs536.s05/course.hold/html/NOTES/2a.JLEX.html
* JLEX ubuntu: sudo apt-get install jlex
* JLEX windows: *emoji da carinha pensando*

## ?


(/\*) { return new Token(yytext(), "com" , "COMOPEN", yyline); }
(\*/) { return new Token(yytext(), "com" , "COMCLOSE", yyline); }

DIGIT   = [0-9]
LETTER  = [a-zA-ZçÇãÃé]
WS      = [ \t\n\f\r]     
SPECIAL = [\]\[\{\}*+/;-<=>,]
WS2     = [ \t\f\r]     

NUM       = {DIGIT}{DIGIT}*
ID        = {LETTER}{LETTER}*
BANIDOS   = [.`&?:!~]
EXCLUIDOS = [^A-Za-z0-9]
ANY       = {LETTER}|{DIGIT}|{WS}|{BANIDOS}|{SPECIAL}|\-
ANY2      = {LETTER}|{DIGIT}|{WS2}|{BANIDOS}|{SPECIAL}|\-