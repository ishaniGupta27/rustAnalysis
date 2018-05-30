

struct Test {
    obj_name: String
}

fn main() {
	let mut t1=Test {
        obj_name: String::from("t1"),
    	};

    let t2=Test {
        obj_name: String::from("t2"),
    	};

    t1=t2;

    //println!("answ.. {}", t1.obj_name);
    
}