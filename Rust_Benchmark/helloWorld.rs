fn main(){
    use std::time::Instant;
    let st=Instant::now();
    println!("Hello, world!");
    let elaps=st.elapsed().subsec_nanos();
    println!("{}",elaps );

} 
