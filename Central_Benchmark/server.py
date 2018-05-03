#from subprocess import call
from timeit import timeit
#call(['/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/C_Benchmark/helloWorld', '/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/C_Benchmark/test.txt'])

#call(['/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/Rust_Benchmark/helloWorld', '/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/Rust_Benchmark/test.txt'])
results= open('resultsFile.txt','w');
#----------Reading the executable names-----------------
with open('execToRun.txt') as f:
	content = f.readlines()
	
content = [x.strip() for x in content] 
#--------------------------------------------------------

for name in content:

	reps = 2
	fileToExecute= '/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/Central_Benchmark/Executables/'+name;
	time_taken = timeit(stmt = "subprocess.call('"+fileToExecute+"')", setup = "import subprocess", number = reps)/ reps
	results.write(name+','+str(reps)+','+str(time_taken)+'\n');
	#print "Average time taken for %s repetitions: %f seconds" % (reps, time_taken)