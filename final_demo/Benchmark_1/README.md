## Points to be Noted
1. server.sh and servermem.sh are the two central scripts to run for time and memory benchmarking.
2. resultsMemory.txt and resultsTime.txt will be generated (or appended ) with the new results.
3. All the excutables for C and Rust are in Execuatable folders.


## Steps to benchmark 1

1. Binaries should exist in Executable folder with the names revC & revR. For revC, compile using gcc -o revC reverse-complement.c
   For rust, external library "Rayon" is needed. So, need to do Cargo build with Rayon dependencies in the Cargo.toml.
   
 2. For Java, make sure the bytecode stays in the place where server.sh and servermem.sh are present.
 
 3. Run ./serser.sh and ./servermem.sh . Result Files will be generated.
