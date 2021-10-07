public class Main
{
    public static void main(String[] args) 
    {
        if(args.length == 0)
        {
            System.out.println("Arquivo de entrada não especificado.");
            return;
        }

        try 
        {
            Compilador c = new Compilador();
            c.lex(args[0]);
        }
        catch(Exception ex)
        {
            System.out.println("Erro não especificado.");
            //ex.printStackTrace();
        }
    }
}