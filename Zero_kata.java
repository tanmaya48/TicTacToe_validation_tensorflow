/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zero_kata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ce
 */
public class Zero_kata {

    static char[][]  array;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      
         reset();
        
        
        int O = 0;
        int X = 0;
        int N = 0;
        
         BufferedWriter writer= null;
          
         File file = new File("TTT_test.csv");
       
         writer=new BufferedWriter(new FileWriter(file));
       
        writer.write("a1,a2,a3,b1,b2,b3,c1,c2,c3,rslt"+"\n");
        

        int x =3000;
        
        for(int n = 0;n<1000;n++)
        {
            reset();
            char c =run();
            
            if(c == 'O')
            {
             if(O>x)continue;
             O++;             
            }
            if(c == 'X')
            {
             if(X>x)continue;
             X++;                
            }
            if(c == '_')
            { 
                if(N>x)continue;
                N++;
            }
            
            for(int i=0;i<3;i++)
               {
                  for(int j=0;j<3;j++)
                      {
                          char ch = array[i][j];
                          
                           if(ch == 'O')    writer.write("0,");
                           
                           else if(ch == 'X')   writer.write("1,");
                           
                           else if(ch == '_')    writer.write("-1,");
                      }
               }
            
             if(c == 'O')    writer.write("0"+"\n");
                           
             else if(c == 'X')   writer.write("1"+"\n");
                           
             else if(c == '_')    writer.write("2"+"\n");
 
        }
        
        display();
        
        
        System.out.println("O = "+ O);
        System.out.println("X = "+ X);
        System.out.println("none = "+ N);
        
        writer.close();
        
        
    }
    
    
    
    
    public static void display()
    {
        System.out.println();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    public static boolean isWin(char b)
    {
        /////////////////////  horizontal
        for( int i=0;i<3;i++)
        {
            if(array[i][0]==b)
                if(array[i][1]==b)
                    if(array[i][2]==b)
                        return true;
        }
        //////////////////////  vertical
        for( int i=0;i<3;i++)
        {
            if(array[0][i]==b)
                if(array[1][i]==b)
                    if(array[2][i]==b)
                        return true;
        }
        /////////// diagonal 1
        if(array[0][0]==b)
              if(array[1][1]==b)
                    if(array[2][2]==b)
                        return true;
        /////////// diagonal 2
         if(array[0][2]==b)
              if(array[1][1]==b)
                    if(array[2][0]==b)
                        return true;
         
        return false;
    }
    
    
    public static boolean isEmpty(int a, int b)
    {
        if(array[a][b] == '_')
            return true;
        return false;
    }
    
    
    public static boolean full()
    {
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        if(array[i][j] == '_')
            return false;
        return true;
    }
    
    
    public static char run()
    {
       
        char s='O'; 
        
       while(true)
        {
          //  display();
            
            int a = (int)(Math.random()*2.99f);
            int b = (int)(Math.random()*2.99f);
            
            if(isEmpty(a,b))
            {
                array[a][b] = s;
                
                if(isWin(s))
                {
                  //  System.out.println(s+ " has won");     
                   // display(); 
                    return s;
                }
                if(s=='O') s='X';
                else 
                    s='O';
            }
         
            if(full())
               {
                   // System.out.println("no one has won");     
                   // display(); 
                    return '_' ;
                } 
            
        }
        
        
    }
    
    
    public static void reset()
    {
        array = new char[3][3];
        
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                array[i][j]='_';
    }
    
    
    
    
    
}
