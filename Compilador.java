import java.io.FileInputStream;

public class Compilador
{
    static void println(Object o)
    {
        System.out.println(o);
    }
    
    Lexico lex(String path)
    {
        Lexico lexico = new Lexico();

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

        Yylex parser = new Yylex(fis);

        try
        {
            while(true)
            {
                Token tok = parser.next_token();

                //lexico.printaTokens();
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