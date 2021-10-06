public class Main
{
    public static void main(String[] args)
    {
        Compilador c = new Compilador();
        c.lex("./tests/program.cm");
    }
}