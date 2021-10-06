import java.util.ArrayList;

public class Lexico
{
    ArrayList<Token> tabela_tokens;
    ArrayList<Object[]> tabela_lexemes;

    static final int NOVO       = -1;
    static final int POS_TOKEN  = 0;
    static final int POS_LINHA  = 1;
    static final int POS_COLUNA = 2;
    static final int POS_LEXEME = 3;

    Lexico()
    {
        tabela_tokens  = new ArrayList<Token>();
        tabela_lexemes = new ArrayList<Object[]>();
    }

    int busca(Token tok)
    {
        int pos = NOVO;

        // Procura na tabela_tokens
        for(int i = 0; i < tabela_tokens.size(); i++)
        {
            Token tok_test = tabela_tokens.get(i);

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
            tabela_tokens.add(tok);
            pos = tabela_tokens.size()-1;
        }

        // Adiciona na tabela de lexemes encontrados, entrada unica por posicao
        tabela_lexemes.add(new Object[]{pos, tok.linha, tok.coluna, tok.lexeme});
    }

    void atualiza(Token tok)
    {
        if(tok == null)
            return;

        adiciona(tok, busca(tok));
    }

    void printaTokens()
    {
        System.out.println("* * * * * * * Tokens * * * * * * *");
        for(int i = 0; i < tabela_tokens.size(); i++)
            System.out.println(i + " : " + tabela_tokens.get(i).string());
    }

    void printaLexemes()
    {
        System.out.println("* * * * * * * Lexemes * * * * * * *");
        for(int i = 0; i < tabela_lexemes.size(); i++)
        {
            Object[] lex = tabela_lexemes.get(i);
            Token tok = tabela_tokens.get((int)lex[POS_TOKEN]);

            System.out.println(i + " : " + lex[POS_LINHA] + ":" + lex[POS_COLUNA] + " - l: \"" + lex[POS_LEXEME] + "\", t: " + tok.token + ", a: " + tok.atributo);
        }
    }
}