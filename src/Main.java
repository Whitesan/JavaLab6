import java.util.Vector;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        while(true)
        {
           try{
               Operation();
               break;
           }
           catch(WektoryRoznejDlugosciException | NumberFormatException e)
           {
               System.out.println(e);
           }
        }
    }
    public static void Operation() throws WektoryRoznejDlugosciException
    {
        Vector<Integer> Vec1 = null;
        Vector<Integer> Vec2 = null;
        try{
            Vec1 = ReadVector();
            Vec2 = ReadVector();

        }catch(NumberFormatException e)
        {
            System.out.println("To nie jest wektor liczb");
            throw new NumberFormatException();
        }
        int size1 = Vec1.size();
        int size2 = Vec2.size();
        if(size1 != size2)
        {
            throw new WektoryRoznejDlugosciException(size1,size2);
        }
        String result = new String();
        for(int i=0;i<size1;i++)
        {
            String temp = new String(Integer.toString(Vec1.elementAt(i)+Vec2.elementAt(i)));
            result = result + temp + " ";
        }

        try{
            FileWriterS(result + "\n","Wyniki.txt");
            System.out.println("Result saved in file \"Wyniki.txt\"");
        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public static Vector<Integer> ReadVector()
    {
        System.out.println("Podaj wektor:");
        Scanner scanner = new Scanner(System.in);
        Vector<Integer> Vec = new Vector<Integer>();
        String S = scanner.nextLine();
        String [] arrString  = S.split(" ");
        for(int i=0;i<arrString.length;i++)
        {
            try{
                Vec.add(Integer.parseInt(arrString[i]));
            }catch(NumberFormatException e)
            {
                throw new NumberFormatException();
            }
        }
        return Vec;
    }
    public static void FileWriterS(String text, String filename) throws IOException {
        FileWriter File = null;
        try {
            File = new FileWriter(filename,true);
            File.write(text);
        } finally {
            if (File != null)
                File.close();
        }
    }
}
class WektoryRoznejDlugosciException extends Exception{
    int size1;
    int size2;
    public WektoryRoznejDlugosciException(int size1,int size2)
    {
        this.size1 = size1;
        this.size2 = size2;
    }
    public String toString()
    {
        return "Dlugosc pierwszego wektora to " + size1 + " a drugiego to " + size2;
    }
}
