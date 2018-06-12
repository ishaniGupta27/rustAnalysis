## Points to be Noted
1. server.sh and servermem.sh are the two central scripts to run for time and memory benchmarking.
2. resultsMemory.txt and resultsTime.txt will be generated (or appended ) with the new results.
3. All the excutables for C and Rust are in Execuatable folders.


## Steps to benchmark 1

1. In  Executables folder : Binaries should exist in with the names mmC & mmR. For mmC, compile using gcc -o mmC mm.c 
   For rust, rustc -o mmR mm.rs 
   
 2. For Java, make sure the bytecode stays in the place where server.sh and servermem.sh are present. For compiling do
     javac MultithreadingDemo.java
 
 3. Run ./serser.sh and ./servermem.sh . Result Files will be generated.

