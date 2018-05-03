from subprocess import call

#call(['/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/C_Benchmark/helloWorld', '/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/C_Benchmark/test.txt'])

#call(['/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/Rust_Benchmark/helloWorld', '/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/Rust_Benchmark/test.txt'])
#----------Reading the executable names-----------------
with open('execToRunC.txt') as f:
	content = f.readlines()
	
content = [x.strip() for x in content] 

for cProgram in content:

	cProgramRun=cProgram+'.c';
	objectName= '/Users/ishaniGupta/Library/Mobile Documents/com~apple~CloudDocs/UCSB_3/Runtime System/Code/rustAnalysis/Central_Benchmark/Executables/'+cProgram+'C'
	call(['gcc','-o',objectName,cProgramRun])