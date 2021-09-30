import java.io.FileInputStream;

public class Parser
{
    Token[] tabela_tokens;

    static void println(Object o)
    {
        System.out.println(o);
    }
    
    Token[] parse(String path)
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream(path);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }

        Yylex parser = new Yylex(fis);

        try
        {
            while(true)
            {
                String str = parser.next_token();
                if(str != null)
                    System.out.println(str);
                else
                    break;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }

        return tabela_tokens;
    }

}