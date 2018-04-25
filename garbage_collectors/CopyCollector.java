
import java.util.*;
import java.io.*;
public class CopyCollector{
        static int sizeMin=0;
	static int sizeMax=3;
	static int objSize=3;
	static int maxPrToSpace=sizeMax-1;
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

		for(int i=sizeMin;i<sizeMax;i++){
			int[]obj=new int[objSize];
			for(int j=0;j<objSize;j++){
				obj[j]=-1;
			}
			toSpace.add(obj);
		}
		}

	public static void memFinish(){
		flip();

	}

	public static void flip(){
		
		//check my roots for copying live data
		int ptrToSpace=sizeMax-1;
		for(int i=sizeMin;i<sizeMax;i++){
			int[] obj = fromSpace.get(i);
			//int ptrToSpace=-1;
			System.out.println("ptrToSpace.."+ptrToSpace);
			copy(obj,ptrToSpace);
			ptrToSpace=maxPrToSpace;
			//toSpace.add(indxToSpace,obj);
		}

	}

	public static int copy(int obj[],int ptrToSpace){
			
		if(obj== null){
			return -1 ;
		}
		//ptrToSpace++;
		if(obj[0] == -1){//not copied
			//obj[0]=ptrToSpace;
			ptrToSpace++;
			maxPrToSpace=ptrToSpace;
			obj[0]=ptrToSpace;
			toSpace.add(ptrToSpace,obj);	
	
	
			for(int j =1;j<objSize;j++){
			        int[] objLoc=toSpace.get(ptrToSpace);
				objLoc[j]=copy(fromSpace.get(obj[j]),ptrToSpace);
				//toSpace.add(retPtrSpace,fromSpace.get(obj[j]));
			}
		}
		return obj[0];
	}
	
	public static void main(String [] args){
		System.out.println("Calling init");
		init();
		System.out.println("\n Ending init");
		if(Integer.parseInt(args[0]) == 1){
			System.out.println("mem full");
			memFinish();
		}
		for (int i =sizeMin;i<toSpace.size();i++){
                      int [] obj= toSpace.get(i);
                          for(int j=0;j<objSize;j++){
                                  System.out.print(obj[j]+",");
                          }
                          System.out.print(";");
                          }
                  }
		}


