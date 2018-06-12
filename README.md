# rustAnalysis
Does Rust really do what everyone claims it do?
Presentation : https://docs.google.com/presentation/d/14W6bwEfNapSeCpTKXIb4FNrGCP4jhmQcUqgtRlBIn-o/edit?usp=sharing

# Setup 

Install rustup (also installs Rust compiler) curl https://sh.rustup.rs -sSf | sh

# Architecture

## Centralized / Uniform

There is python script which runs 3 executables in Rest, C, third Language (yet to be decided). This approach introduces uniformity in testing the cpu time taken by various executables.

We can also make sure that python overhead do not add in cpu time, we can run hello_world and do cputime-cputime(hello_world).

Another important factor in having centralized system is that plugging in the system is easier and quick.


# Plan

Using git projects to plan every weeks work. I am maintaining the To-Dos , In Progress and Done boards to track the project goals.

# Till Now Progress:

1. Rust: Many things are unstable in rust like its test and bencher crate so I can not find any solution to use them till now. 
2. I have made a basic hello world testing environment for C and Rust. I am using C: clock() and Rust : Instant::now(). 
3. Another things to be pondered upon is precision . I am not getting very high precision in C unlike in Rust library for time keeping.
4. How Am I benchmarking it ? Should I have a standard python script which will call all the functions executable so only runtime ? 
5. Write *Native* Matrix Multiplication in both the languages.
6. Write down heapsort in rust 
7. Implement in bash
8. Memory Evaluation
9. Write *Sudoku* Benchmark on three the languages. Read what all are the graphs needed at end. time taken is one. Memory Usage . 

# Presentation Goals :

1. Introduce features novel to Rust --> Ownership 
2. Benchmarking on different languages.
3. Benchmarking on different machines
4. Mathematical model to unify and quantify the benchmarks.


