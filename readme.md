## Refs

* JLEX Manual: http://pages.cs.wisc.edu/~fischer/cs536.s05/course.hold/html/NOTES/manual.html#SECTION2.1
* JLEX: http://pages.cs.wisc.edu/~fischer/cs536.s05/course.hold/html/NOTES/2a.JLEX.html
* JLEX ubuntu: sudo apt-get install jlex
* JLEX windows: *emoji da carinha pensando*

## ?


(/\*) { return new Token(yytext(), "com" , "COMOPEN", yyline); }
(\*/) { return new Token(yytext(), "com" , "COMCLOSE", yyline); }