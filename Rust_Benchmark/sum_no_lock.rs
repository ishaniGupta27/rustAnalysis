
use std::sync::{Arc};
use std::thread;
use std::env;
use std::thread::JoinHandle;


fn main() {
    let args: Vec<String> = env::args().collect();
    
    let a=&args[1];
    //let b=&args[2];
    const n: usize=2000;//a.parse::<usize>().unwrap(); //20000; //total slots
    let t: usize=a.parse::<usize>().unwrap();//threads number

    let mut base_vec: Vec<f64> = vec![0.0; n];
  
    for i in 0..base_vec.len() {
        base_vec[i] = 1.0;
    }
  

    
    
    if t==1 {
        let sum = part_sum(&base_vec, 0, n);
        println!("{}",sum);
    } else { 
        let base_vec_arc = Arc::new(base_vec);
        
        let mut threads : Vec<JoinHandle<(f64)>> = Vec::new();
        let slot_size= n/t;
        for j in 0..t {
            let base_vec_shared = base_vec_arc.clone();
           

            let handle = thread::spawn(move || {
                let inside_start=j*slot_size;
                let inside_end=inside_start+slot_size;
                let local_sum = part_sum(&base_vec_shared,inside_start,inside_end);
                local_sum
            });

            threads.push(handle);
        }

        let mut global_sum : f64 = 0.0;
        for handle in threads {
            global_sum += handle.join().unwrap();
        }

        println!("{}", global_sum);
    }
    
}

fn part_sum(vec_to_add: & Vec<f64>, inside_start: usize, inside_end: usize) -> f64 {
    let mut sum: f64 = 0.0;
    for i in inside_start..inside_end {
       
            sum = sum + vec_to_add[i];
       
    }
    sum
}