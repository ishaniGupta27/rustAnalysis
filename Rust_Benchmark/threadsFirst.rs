use std::thread;
use std::time::Duration;

use std::sync::mpsc;




fn main() {
	let (tx, rx): (mpsc::Sender<i32>, mpsc::Receiver<i32>) = mpsc::channel();
	let mut array: [i32; 200]= [0; 200] ;
	for i in 0..200 { //index starts at 0, 200 not included
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
		let child_tx = tx.clone();
		thread::spawn(move || {
			let inside_start=start;
		    let inside_end=end;
		    let mut sum=0;
		    
		    println!("hi going to sum from {} to {} from the spawned thread!", inside_start,inside_end);
			for c in inside_start..(inside_end+1) {
	            sum=sum+array[c];
	            //println!("Going to sleep");
	            //thread::sleep_ms(100);
	            //println!("Got up");
	        }
	        println!("hi going to sum from {} to {} from the spawned thread! total..{}", inside_start,inside_end,sum);
	        child_tx.send(sum).unwrap();;

		});
		start=end+1;
		end=start+slot_size-1;
	}

	let result = rx.recv().unwrap() + rx.recv().unwrap() + rx.recv().unwrap() +rx.recv().unwrap() + rx.recv().unwrap() + rx.recv().unwrap() + rx.recv().unwrap() + rx.recv().unwrap();
	//let result = rx.iter();
	//for i in 1..5 {
        println!("hi number {} is Result !!!", result);
        //thread::sleep(Duration::from_millis(2000));
    //}
}

