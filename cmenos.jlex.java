class Token
{
    static final String TYPE_ID  = "id";
    static final String TYPE_NUM = "num";
    String lexeme;
    String tipo;
    String atributo;
    int    linha;
    int    coluna;
    Token(String lexeme, String tipo, String atributo, int linha)
    {
        this.lexeme   = lexeme;
        this.tipo     = tipo;
        this.atributo = atributo;
        this.linha    = linha;
        this.coluna   = coluna;
    }
    boolean referenciavel()
    {
        return (this.tipo.equals(TYPE_ID) || this.tipo.equals(TYPE_NUM));
    }
    boolean comparaTokens(Token b)
    {
        return (this.tipo.equals(b.tipo) && this.lexeme.equals(b.lexeme));
    }
    String string()
    {
        return "l: \"" + this.lexeme + "\", t: " + this.tipo + ", a: "  + this.atributo;
    }
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NOT_ACCEPT,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"0:9,1:2,0,1:2,0:18,1,30,0:4,20,0,34,35,19,28,21,22,20,18,17:10,20,31,23,29," +
"27,20,0,16:4,24,26,16:8,25,16:11,36,0,37,0:2,20,16:3,15,6,3,16,5,2,16:2,8,1" +
"6,7,14,16:2,11,9,4,12,13,10,16:3,32,0,33,20,0:68,16,0:3,16,0:27,16,0:3,16,0" +
":65304,38:2")[0];

	private int yy_rmap[] = unpackFromString(1,73,
"0,1,2,3,4,5,6:3,7,8,6,9,6:8,10,11,6:5,10:6,12,11,10,13,14,15,12,16,17,18,19" +
",20,21,22,23,24,25,26,12,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43" +
",44,45,46")[0];

	private int yy_nxt[][] = unpackFromString(47,39,
"-1,1,2,36,59,36,61,36:3,67,70,36,63,36:3,3,4,5,6,7,8,9,36:3,10,11,12,37,13," +
"14,15,16,17,18,19,20,-1,1,-1:39,36,21,36:3,39,36:9,-1:7,36:3,-1:29,3,-1:40," +
"22,-1:37,23,-1:88,24,-1:38,25,-1:38,26,-1:11,36:15,-1:7,36:3,-1:13,35:17,58" +
",60,35:3,72,35:8,-1:8,52:17,40,38,40:3,51,52:3,40:5,-1:36,27,-1:10,52:17,34" +
",38,40:3,51,52:3,40:5,-1:9,36:2,28,36:12,-1:7,36:3,-1:14,36:5,29,36:9,-1:7," +
"36:3,-1:13,52:17,40,38,40:3,42,44,52:2,40:5,-1:9,36:4,30,36:10,-1:7,36:3,-1" +
":13,52:17,40,38,40:3,51,52,46,52,40:5,-1:9,36:13,31,36,-1:7,36:3,-1:13,52:1" +
"7,40,38,40:3,51,52:2,40:6,-1:9,36:4,32,36:10,-1:7,36:3,-1:14,36:5,33,36:9,-" +
"1:7,36:3,-1:13,52:18,38,40:3,51,52:3,40:5,-1:9,36:4,41,36:10,-1:7,36:3,-1:1" +
"3,52:17,40,38,40:3,42,52:3,40:5,-1:9,36:7,43,36:7,-1:7,36:3,-1:13,35:17,58," +
"60,35:3,72,35:3,40,35:4,-1:9,45,36:14,-1:7,36:3,-1:14,36:6,47,36:8,-1:7,36:" +
"3,-1:14,36:9,48,36:5,-1:7,36:3,-1:13,35:17,58,49,35:3,72,35:8,-1:9,36:3,50," +
"36:11,-1:7,36:3,-1:13,35:17,52,60,35:3,72,35:8,-1:9,36:6,53,36:8,-1:7,36:3," +
"-1:13,35:17,58,60,35:3,72,35:3,54,35:4,-1:9,36:12,55,36:2,-1:7,36:3,-1:14,5" +
"6,36:14,-1:7,36:3,-1:14,36:10,57,36:4,-1:7,36:3,-1:13,35:17,58,60,35:3,72,3" +
"5:2,62,35:5,-1:9,36:3,64,36:11,-1:7,36:3,-1:14,36:2,65,36:12,-1:7,36:3,-1:1" +
"3,35:17,58,60,35:3,72,35,66,35:6,-1:9,36:4,68,36:10,-1:7,36:3,-1:13,35:17,5" +
"8,60,35:3,71,69,35:7,-1:8,35:17,58,60,35:3,71,35:8,-1:7");

	public Token next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{ }
					case -2:
						break;
					case 2:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -3:
						break;
					case 3:
						{ return new Token(yytext(), Token.TYPE_NUM, "", yyline); }
					case -4:
						break;
					case 4:
						{ return new Token(yytext(), "mul", "DIV", yyline);  }
					case -5:
						break;
					case 5:
						{ return new Token(yytext(), "mul", "MUL", yyline);  }
					case -6:
						break;
					case 6:
						{ return new Token(yytext(), "ERROR", "", yyline); }
					case -7:
						break;
					case 7:
						{ return new Token(yytext(), "sep", "", yyline); }
					case -8:
						break;
					case 8:
						{ return new Token(yytext(), "soma", "SUB", yyline); }
					case -9:
						break;
					case 9:
						{ return new Token(yytext(), "relop", "LT", yyline); }
					case -10:
						break;
					case 10:
						{ return new Token(yytext(), "relop", "GT", yyline); }
					case -11:
						break;
					case 11:
						{ return new Token(yytext(), "soma", "SUM", yyline); }
					case -12:
						break;
					case 12:
						{ return new Token(yytext(), "att", "", yyline); }
					case -13:
						break;
					case 13:
						{ return new Token(yytext(), "end", "", yyline); }
					case -14:
						break;
					case 14:
						{ return new Token(yytext(), "scope", "SCOPEOPEN", yyline); }
					case -15:
						break;
					case 15:
						{ return new Token(yytext(), "scope", "SCOPECLOSE", yyline); }
					case -16:
						break;
					case 16:
						{ return new Token(yytext(), "scope", "ARGOPEN", yyline); }
					case -17:
						break;
					case 17:
						{ return new Token(yytext(), "scope", "ARGCLOSE", yyline); }
					case -18:
						break;
					case 18:
						{ return new Token(yytext(), "scope", "LISTOPEN", yyline); }
					case -19:
						break;
					case 19:
						{ return new Token(yytext(), "scope", "LISTCLOSE", yyline); }
					case -20:
						break;
					case 20:
						
					case -21:
						break;
					case 21:
						{ return new Token(yytext(), "if"    , "", yyline); }
					case -22:
						break;
					case 22:
						{ return new Token(yytext(), "com" , "COMOPEN", yyline); }
					case -23:
						break;
					case 23:
						{ return new Token(yytext(), "com" , "COMCLOSE", yyline); }
					case -24:
						break;
					case 24:
						{ return new Token(yytext(), "relop", "LE", yyline); }
					case -25:
						break;
					case 25:
						{ return new Token(yytext(), "relop", "GE", yyline); }
					case -26:
						break;
					case 26:
						{ return new Token(yytext(), "relop", "EQ", yyline); }
					case -27:
						break;
					case 27:
						{ return new Token(yytext(), "relop", "NQ", yyline); }
					case -28:
						break;
					case 28:
						{ return new Token(yytext(), "int"   , "", yyline); }
					case -29:
						break;
					case 29:
						{ return new Token(yytext(), "then"  , "", yyline); }
					case -30:
						break;
					case 30:
						{ return new Token(yytext(), "else"  , "", yyline); }
					case -31:
						break;
					case 31:
						{ return new Token(yytext(), "void"  , "", yyline); }
					case -32:
						break;
					case 32:
						{ return new Token(yytext(), "while" , "", yyline); }
					case -33:
						break;
					case 33:
						{ return new Token(yytext(), "return", "", yyline); }
					case -34:
						break;
					case 34:
						{ return new Token(yytext(), "ERROR" , "COM", yyline); }
					case -35:
						break;
					case 36:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -36:
						break;
					case 37:
						{ return new Token(yytext(), "ERROR", "", yyline); }
					case -37:
						break;
					case 39:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -38:
						break;
					case 41:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -39:
						break;
					case 43:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -40:
						break;
					case 45:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -41:
						break;
					case 47:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -42:
						break;
					case 48:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -43:
						break;
					case 50:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -44:
						break;
					case 53:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -45:
						break;
					case 55:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -46:
						break;
					case 56:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -47:
						break;
					case 57:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -48:
						break;
					case 59:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -49:
						break;
					case 61:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -50:
						break;
					case 63:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -51:
						break;
					case 64:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -52:
						break;
					case 65:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -53:
						break;
					case 67:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -54:
						break;
					case 68:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -55:
						break;
					case 70:
						{ return new Token(yytext(), Token.TYPE_ID , "", yyline); }
					case -56:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
