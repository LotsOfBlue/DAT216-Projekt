package sample;

/**
 * @author Daniel.
 */
public class Utils
{
    public static int levDist(String a, String b)
    {
        int ack = 0;
        for(int i = 0; i<a.length();i++)
        {
            if(a.charAt(i)!=b.charAt(i))
                ack++;
        }
        return ack;
    }
}
