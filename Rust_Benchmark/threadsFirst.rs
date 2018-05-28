use std::thread;
use std::time::Duration;
use std::sync::{Mutex, Arc};

fn main() {
	let mut array: [i32; 200]= [0; 200] ;
	for i in 1..200 { //index starts at 0, 200 not included
		array[i]=i as i32;
	}

	let n=200; //total slots
	let t=8;  //threads number

	let slot_size= n/t;
	println!("hi numbers {} handled by single thread", slot_size);
	let mut start =0;
	let mut end=slot_size-1;
	for j in 0..t{ //8 not included
		//spawn and do whatever..
	    
		thread::spawn(move || {
			let mut inside_start=start;
		    let mut inside_end=end;
		    
			//for c in inside_start..inside_end {
	            println!("hi going to sum from {} to {} from the spawned thread!", inside_start,inside_end);
	            println!("Going to sleep");
	            thread::sleep_ms(100);
	            println!("Got up");
	        //}
		});
		start=end+1;
		end=start+slot_size-1;
	}


	for i in 1..5 {
        println!("hi number {} from the main thread!", i);
        thread::sleep(Duration::from_millis(2000));
    }
}

