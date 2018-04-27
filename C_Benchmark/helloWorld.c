#include <stdio.h>
#include <time.h>
int main(){
	clock_t st;
	clock_t end;
	st=clock();
	printf("Hello,World!");
	end=clock();
	double cpu_time_used = (((double) (end - st)) / CLOCKS_PER_SEC)*(1000000000);
	printf("\n %lf",cpu_time_used);
	return 0;

}
