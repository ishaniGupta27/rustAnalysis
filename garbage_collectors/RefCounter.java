
import java.util.*;
import java.io.*;
public class RefCounter{
	static int sizeMin=0;
	static int sizeMax=3;
	static int newNo=3;
	static int heapSize=10;
	static List<Integer> freeList=new ArrayList<Integer>();
	static int[][] heap =new int[10][3];
	public static void initSt(){
		for(int i=0;i<heapSize;i++){
			freeList.add(i); //all the freelist.
		}
        for(int i=0;i<3;i++){
			int res=allocate();
			heap[res][0]=1; //this is RC
		    heap[res][1]=-1;
		    heap[res][2]=-1;
	    }

	}
	public static int allocate(){
		int res=freeList.remove(0);
		return res;
	}
	public static void newObj(int hd,int l,int r){
		if(freeList == null){
			System.out.println("No memory");
		}
		int res=allocate();
		heap[res][0]=1; //this is RC
		heap[res][1]=l;
		heap[res][2]=r;
		System.out.println("New Obj done");
	}
	public static void decreaseHd(int whomToUpdate,int indx){
		if(indx==0){
			//this is delting this node
			heap[whomToUpdate][0]--;

			if(heap[whomToUpdate][0]==0){
				for(int i=1;i<3;i++){
					if(heap[whomToUpdate][i]!=-1){ //check for children
					  decreaseHd(heap[whomToUpdate][i],0);

				}
				}
				freeList.add(0,whomToUpdate );
					  heap[whomToUpdate]=null;
			}
		}else{
			int indxToDec= heap[whomToUpdate][indx];
	      	if(indxToDec==-1){
	      		return;
	      	}
			heap[indxToDec][0]--;

			if(heap[indxToDec][0]==0){
				for(int i=1;i<3;i++){ //check for children
					if(heap[indxToDec][i]!=-1){
					  decreaseHd(heap[indxToDec][i],0);
				}
				}
				freeList.add(0,indxToDec );
					  heap[indxToDec]=null;
			}

		}
	System.out.println("Decrease done");
	}
	public static void update(int whomToUpdate, String which, int withWhomToUpdate){
		//System.out.println("which... "+which);
		if(which.equals("d")){
			System.out.println("Inside delete ");
			decreaseHd(whomToUpdate,0);
			return;
		}
		heap[withWhomToUpdate][0]++; //this is to which the pointer has increased	
		int idx =0;
		switch(which){
			case "s":
			idx=0;
			decreaseHd(whomToUpdate,0);
			break;
			case "l":
			idx=1;
			decreaseHd(whomToUpdate,1);
			break;
			case "r":
			idx=2;
			decreaseHd(whomToUpdate,2);
			break;
			default:
			System.out.println("Issue in which");

		}

		System.out.println("Returned from updating (more of decreasing ptr) ptrs");
		heap[whomToUpdate][idx]=withWhomToUpdate;
		System.out.println("Done updating");
	}
	public static void printHeap(){
	System.out.println("Let me show you how heap looks");
							String toPrint="Root-->";
                         for(int i=0;i<newNo;i++){
                          
                          if(heap[i]!= null){
                          	toPrint=toPrint+i+"--->"+heap[i][0]+","+heap[i][1]+","+heap[i][2]+"; \n";
                          	//System.out.println("Root-->"+i+"--->"+heap[i][0]+","+heap[i][1]+","+heap[i][2]+";");
                          }
                          //System.out.println("Root-->"+i+"--->"+heap[i][0]+","+heap[i][1]+","+heap[i][2]+";");
                         }
                         System.out.println(toPrint);
	}

	public static void printList(){
		System.out.println("Let me show you how list looks");
                         for(int i=0;i<freeList.size();i++){
                          System.out.print(freeList.get(i)+",");
                         }
	}
	public static void main(String [] args){
		initSt();
		
			/*for(int i=0;i<newNo;i++){
				Random rand=new Random();
				int rN= rand.nextInt(((sizeMax-1) - sizeMin) + 1) + sizeMin;
				int rN2= rand.nextInt(((sizeMax-1) - sizeMin) + 1) + sizeMin;
				newObj(0,rN,rN2);
			}*/
		       printHeap();
		       printList();
		    if (Integer.parseInt(args[0])==2){

		    	while(1>0){
		
				Scanner reader = new Scanner(System.in);  // Reading from System.in
                                System.out.println("Which obj to update? ");
                                int whomToUpdate = reader.nextInt();
				System.out.println("What about this obj to update");
				String which = reader.next();
				System.out.println("With whom to update it");
				int withWhomToUpdate= reader.nextInt();	
			
			update(whomToUpdate,which,withWhomToUpdate);

	        printHeap();
	        printList();	
	        System.out.println("Exit [E] or update [U]?");
	        String opt = reader.next();
	        if(opt=="E"){
	        	break;
	        }
	        }
	        }

	}	
}
