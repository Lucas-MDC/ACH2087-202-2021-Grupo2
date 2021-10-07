import java.util.ArrayList;

public class Lexico
{
    ArrayList<Token>    tabela_simbolos;
    ArrayList<Object[]> tabela_lexemes;

    static final int NREF    = -2;
    static final int NOVO    = -1;
    static final int POS_PTR = 0;
    static final int POS_TOK = 1;

    Lexico()
    {
        tabela_simbolos  = new ArrayList<Token>();
        tabela_lexemes = new ArrayList<Object[]>();
    }

    int busca(Token tok)
    {
        int pos = NOVO;

        // Procura na tabela_simbolos
        for(int i = 0; i < tabela_simbolos.size(); i++)
        {
            Token tok_test = tabela_simbolos.get(i);

            if(tok_test.comparaTokens(tok))
            {
                pos = i;
                break;
            }
        }

        return pos;
    }

    void adiciona(Token tok, int pos)
    {
        // Se for novo, adiciona na tabela de tokens
        if(pos == NOVO)
        {
            tabela_simbolos.add(tok);
            pos = tabela_simbolos.size()-1;
        }

        // Adiciona na tabela de lexemes encontrados, entrada unica por posicao
        tabela_lexemes.add(new Object[]{pos, tok});
    }

    void atualiza(Token tok)
    {
        if(tok == null)
            return;

        int pos = 0;

        if(tok.referenciavel())
            pos = busca(tok);
        else
            pos = NREF;

        adiciona(tok, pos);
    }

    void printaTokens()
    {
        System.out.println("* * * * * * * Símbolos * * * * * * *");
        for(int i = 0; i < tabela_simbolos.size(); i++)
            System.out.println(i + " - " + tabela_simbolos.get(i).lexeme);
    }

    void printaLexemes()
    {
        System.out.println("* * * * * * * Lexemes * * * * * * *");
        for(int i = 0; i < tabela_lexemes.size(); i++)
        {
            Object[] lex = tabela_lexemes.get(i);
            Token tok = (Token)lex[POS_TOK];

            String atributo;

            if(tok.referenciavel())
                 atributo =  "[" + (int)lex[POS_PTR] + "]"; 
            else
                atributo = tok.atributo;

            System.out.println(i + " - " + tok.linha + ":" + tok.coluna + " - l: \"" + tok.lexeme + "\", t: " + tok.tipo + ", a: " + atributo);
        }
    }
}