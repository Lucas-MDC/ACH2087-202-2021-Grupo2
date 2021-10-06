import java.io.InputStream;
import java.io.FileInputStream;

public class Compilador
{
    static void println(Object o)
    {
        System.out.println(o);
    }
    
    Lexico lex(String path)
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(path);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

        return lex(fis);
    }

    Lexico lex(InputStream is)
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
            ex.printStackTrace();
            return null;
        }

        return lexico;
    }

}