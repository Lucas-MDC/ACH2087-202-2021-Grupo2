import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

public class Compilador
{
    
    static void println(Object o)
    {
        System.out.println(o);
    }
    
    Lexico lex(String path, String opath) throws Exception
    {
        FileInputStream  fis;
        FileOutputStream fos;

        try
        {
            fis = new FileInputStream(path);
            fos = new FileOutputStream(opath);
        }
        catch(Exception ex)
        {
            System.out.println("Arquivo não pode ser encontrado");
            throw ex;
        }

        return lex(fis, fos);
    }

    Lexico lex(InputStream is, OutputStream os) throws Exception
    {
        Lexico lexico = new Lexico();

        Yylex parser = new Yylex(is);

        try
        {
            while(true)
            {
                Token tok = parser.next_token();

                if(tok != null)
                    lexico.atualiza(tok);
                else
                    break; 
            }

            //lexico.printaTokens();
            //lexico.printaLexemes();
        }
        catch(Exception ex)
        {
            throw ex;
        }

        DataOutputStream dos = new DataOutputStream(os);

        for(int i = 0; i < lexico.tabela_lexemes.size(); i++)
        {
            Object[] lex = lexico.tabela_lexemes.get(i);
            Token    tok = (Token)lex[Lexico.POS_TOK];

            String atributo;

            if(tok.referenciavel())
                atributo = lexico.tabela_simbolos.get((int)lex[lexico.POS_PTR]).lexeme;
            else
                atributo = tok.atributo;

            dos.writeChars(tok.linha + " " + tok.tipo + " " + atributo + "\n");

            //tok.tipo
            //String atributo;
        }

        return lexico;
    }

}