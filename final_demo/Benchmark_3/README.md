## Points to be Noted
1. server.sh and servermem.sh are the two central scripts to run for time and memory benchmarking.
2. resultsMemory.txt and resultsTime.txt will be generated (or appended ) with the new results.
3. All the excutables for C and Rust are in Execuatable folders.


## Steps to benchmark 1

1. In  Executables folder : Binaries should exist in with the names mmC & mmR. For mmC, compile using gcc -o piC pidigits.c -lgmp
   For rust,rustc -o piR pidigits.rs
   
 2. For Java, **make sure the bytecode stays in the place where server.sh and servermem.sh are present.** 
    For compiling 
    1. Biginteger : Java folder -> pidigits.java. (make sure the bytecode stays in benchmark_2 folder )
    2. GMP wrapper for Java: https://github.com/dfdeshom/GMP-java.
 3. Run ./server.sh and ./servermem.sh . Result Files will be generated.

