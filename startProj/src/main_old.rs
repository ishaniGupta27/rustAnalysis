fn main(){
    println!("Hello, world!");
    println!("{greeting}, {name}!",greeting="Hello", name="world");
    println!("{:?}",[1,2,3]);
    println!("{:#?}",[1,2,3]);
    
    //introducing variables
    let answer = 42;
    assert_eq!(answer,42); //this is checked at runtime
    println!("Hello {}", answer);

   
    //introducing loops & conditions
    // There are no brackets around the condition, just like in Go, but you must use curly brackets around the block.
    for i in 0..5{  //range is not inclusive so it will be 0,1,2,3,4
       if i%2==0 {
            println!("Even {}",i);
        } else{
            println!("Odd {}",i);
        }
     }

    for i in 0..5{
        let odd_even = if i%2 == 0 {"even"} else {"odd"};
        println!("{} {}", odd_even, i);

    }
    
    //Variable Bindings
    //In Rust variable are immutable by default, so we call them Variable bindings. To make them mutable, mut keyword is used.
   // Rust is a statically typed language; It checks data type at compile time. But it doesn’t require you to actually type it when declare variable bindings. On that case compiler checks the usage and set a better data type for it. But for constants and statics you must annotate the type.

    let a = true;
    let b: bool = true;

    let (mut x,y) = (1,2);
    
    let mut z =5; //mut makes it possible for a var to be muttable

    x= 7;
    
    z=6;

    //const keyword is used to define constants. It lives for the entire lifetime of a program but have no fixed address in memory. static keyword is used to define ‘global variable’ type facility. There is only one instance for each value, and it’s at a fixed location in memory.





}


