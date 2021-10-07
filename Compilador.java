import java.io.InputStream;
import java.io.FileInputStream;

public class Compilador
{
    static void println(Object o)
    {
        System.out.println(o);
    }
    
    Lexico lex(String path) throws Exception
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(path);
        }
        catch(Exception ex)
        {
            System.out.println("Arquivo não pode ser encontrado");
            throw ex;
        }

        return lex(fis);
    }

    Lexico lex(InputStream is) throws Exception
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

            lexico.printaTokens();
            lexico.printaLexemes();
        }
        catch(Exception ex)
        {
            throw ex;
        }

        return lexico;
    }

}