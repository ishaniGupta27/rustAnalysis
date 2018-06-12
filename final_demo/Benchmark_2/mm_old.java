import java.util.Scanner;

class MatrixProduct extends Thread {
      private int[] A;
      private int[] B;
      private int[] C;
      private int rig,col;
      private int dim;

      public MatrixProduct(int[] A,int[]B,int[] C,int rig, int col,int dim_com)
      {
         this.A=A;    
         this.B=B;
         this.C=C;
         this.rig=rig;    
         this.col=col; 
         this.dim=dim_com;     
      }

     public void run()
     {
         for(int i=0;i<dim;i++){
               C[i]+=A[i]*B[i];        
         }      
          System.out.println("Thread "+rig+","+col+" complete.");        
     }          
 }

 public class MatrixMultiplication {
       public static void main(String[] args)
      {      
       int size = 1048576;
       int[] A=new int[size];
       int[] B=new int[size]; 
       MatrixProduct[] thrd= new MatrixProduct[rA][cB];
      
        for(int i=0;i<rA;i++)
         {
              A[i]=4;
         }        
        
        for(int i=0;i<rB;i++){
            B[i]=2;
          }
                 
          
        for(int i=0;i<rA;i++)
        {
         
            thrd[i][j]=new MatrixProduct(A,B,C,i,j,cA);
            thrd[i][j].start();
          
        }    
}      
}