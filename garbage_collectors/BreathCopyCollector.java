
import java.util.*;
import java.io.*;
public class BreathCopyCollector{
        static int sizeMin=0;
	static int sizeMax=3;
	static int objSize=3;
	static int ptrToSpace=-1;
	static  List<int[]> toSpace =new ArrayList<int[]>();
	static List<int[]> fromSpace = new ArrayList<int[]>();
        public static void init(){	
	
		//List<int[]> fromSpace = new ArrayList<int[]>();
		//List<int[]> toSpace =new ArrayList<int[]>();
		//the below is new.
		for(int i=sizeMin;i<sizeMax;i++){
			int[] obj =new int[objSize]; //object size if fixed at 3
			obj[0]=-1;//represent nothing is attached to toSpace header object
			for(int j =1;j<objSize;j++){
				Random rand=new Random();
				int rN= rand.nextInt(((sizeMax-1) - sizeMin) + 1) + sizeMin;
				obj[j]=rN;
			}
			fromSpace.add(obj);
	
		}
	        for (int i =sizeMin;i<sizeMax;i++){
			int [] obj= fromSpace.get(i);
			for(int j=0;j<objSize;j++){
				System.out.print(obj[j]+",");
			}
			System.out.print(";");
			}
		}

	public static void memFinish(){
		flip();

	}

	public static void flip(){
		
		//check my roots for copying live data
		for(int i=sizeMin;i<sizeMax;i++){
			int[] obj = fromSpace.get(i);
			//int ptrToSpace=-1;
			copy(obj,ptrToSpace);
			//toSpace.add(indxToSpace,obj);
		}

	}

	public static void copy(int obj[],int ptrToSpace){
		if(obj== null){
			return ;
		}
		ptrToSpace++;
		if(obj[0] == -1){//not copied
			obj[0]=ptrToSpace;
			//ptrToSpace++;
			toSpace.add(ptrToSpace,obj);	
	
	
			for(int j =1;j<objSize;j++){
			        copy(fromSpace.get(obj[j]),ptrToSpace);
				//toSpace.add(retPtrSpace,fromSpace.get(obj[j]));
			}
		}
		return ;
	}
	
	public static void main(String [] args){
		System.out.println("Calling init");
		init();
		System.out.println("Ending init");
		if(Integer.parseInt(args[0]) == 1){
			System.out.println("mem full");
			memFinish();
			for (int i =sizeMin;i<sizeMax;i++){
                          int [] obj= toSpace.get(i);
                          for(int j=0;j<objSize;j++){
                                  System.out.print(obj[j]+",");
                          }
                          System.out.print(";");
                          }
                  }
		}
	}

