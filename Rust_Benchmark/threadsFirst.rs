use std::thread;
use std::time::Duration;
use std::env;
use std::sync::mpsc;




fn main() {
	let args: Vec<String> = env::args().collect();
	let (tx, rx): (mpsc::Sender<i32>, mpsc::Receiver<i32>) = mpsc::channel();
	let a=&args[1];
	//let b=&args[2];
	const n: usize=20000;//a.parse::<usize>().unwrap(); //20000; //total slots
	let t: usize=a.parse::<usize>().unwrap();//threads number
	let mut array: [i32; n]= [0; n] ;
	for i in 0..n { //index starts at 0, 200 not included
		array[i]=i as i32;
	}

	

	let slot_size= n/t;
	//println!("hi numbers {} handled by single thread", slot_size);
	let mut start =0;
	let mut end=slot_size-1;
	for j in 0..t{ //8 not included
		//spawn and do whatever..
		let child_tx = tx.clone();
		thread::spawn(move || {
			let inside_start=start;
		    let inside_end=end;
		    let mut sum=0;
		    
		    //println!("hi going to sum from {} to {} from the spawned thread!", inside_start,inside_end);
			for c in inside_start..(inside_end+1) {
	            sum=sum+array[c];
	            //println!("Going to sleep");
	            //thread::sleep_ms(100);
	            //println!("Got up");
	        }
	        //println!("hi going to sum from {} to {} from the spawned thread! total..{}", inside_start,inside_end,sum);
	        child_tx.send(sum);

		});
		start=end+1;
		end=start+slot_size-1;
	}
	let mut result = rx.recv().unwrap();
	for i in 1..t {
	result = result+ rx.recv().unwrap();
	}
	//let result = rx.iter();
	//for i in 1..5 {
        println!("hi number {} is Result !!!", result);
        //thread::sleep(Duration::from_millis(2000));
    //}
}

