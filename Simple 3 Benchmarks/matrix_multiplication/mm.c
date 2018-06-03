/******************************************************************************

 *   The main data is made available to all threads through a globally 
 *   accessible structure. Each thread works on a different part of the 
 *   data.  The main thread waits for all the threads to complete their 
 *   computations, and then it prints the resulting sum.
 * SOURCE: Vijay Sonnad, IBM
 * LAST REVISED:  01/29/09 Blaise Barney
 ******************************************************************************/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>


/*   
     The following structure contains the necessary information to allow the 
     function "dotprod" to access its input data and place its output into 
     the structure.  This structure is unchanged from the sequential version.
     */
int NTHREADS =4;
typedef struct 
{
    double      *a;
    double      *b;
    int         veclen; 
    double     sum[4]; 
} DOTDATA;

/* Define globally accessible variables and a mutex */

#define VECLEN 1048576

DOTDATA dotstr; 
pthread_t threads[4];

/*
   The function dotprod is activated when the thread is created.  As before, 
   all input to this routine is obtained from a structure of type DOTDATA and 
   all output from this function is written into this structure. The benefit 
   of this approach is apparent for the multi-threaded program: when a thread 
   is created we pass a single argument to the activated function - typically 
   this argument is a thread number. All the other information required by the 
   function is accessed from the globally accessible structure. 
   */

void *dotprod(void *arg)
{
    /* Define and use local variables for convenience */

    int i, start, end, len ;
    long tid;
    double partialsum, *x, *y;
    tid = (long)arg;

    len = dotstr.veclen;
    start = tid * len;
    end   = start + len;
    x = dotstr.a;
    y = dotstr.b;

    /*
       Perform the dot product and assign result to the appropriate variable in 
       the structure. 
       */

    partialsum = 0;
    for (i = start; i < end ; i++) 
    {
        partialsum += (x[i] * y[i]);
    }

    /*
       Lock a mutex prior to updating the value in the shared structure, and 
       unlock it upon updating.
       */
    dotstr.sum[tid] += partialsum;

#if NTHREADS != 1
    pthread_exit((void*) 0);
#else
    return NULL;
#endif
}

/* 
   The main program creates threads which do all the work and then print out 
   result upon completion. Before creating the threads, the input data is 
   created. Since all threads update a shared structure, we need a mutex for 
   mutual exclusion. The main thread needs to wait for all threads to complete, 
   it waits for each one of the threads. We specify a thread attribute value 
   that allow the main thread to join with the threads it creates. Note also 
   that we free up handles  when they are no longer needed.
   */

int main ()
{
#if NTHREADS != 1
    long tid;
#endif
    long i;
    double *a, *b;
    double sum = 0.0;

    /* Assign storage and initialize values */
    a = (double*) malloc (VECLEN*sizeof(double));
    b = (double*) malloc (VECLEN*sizeof(double));

    for (i = 0; i < VECLEN; i++) {
        a[i] = 40.0;
        b[i] = 30.0;
    }

    if (VECLEN % NTHREADS != 0)
    {
        fprintf(stderr, "VECLEN=%d must be evenly divisible by NTHREADS=%d\n",
                VECLEN, NTHREADS);
        return 1;
    }

    dotstr.veclen = VECLEN / NTHREADS; 
    dotstr.a = a; 
    dotstr.b = b; 

  
#if NTHREADS != 1
    void *status;
    pthread_attr_t attr;

    /* Create threads to perform the dotproduct  */
    pthread_attr_init(&attr);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);

    for(tid = 0; tid < NTHREADS; tid++) {
        /* Each thread works on a different set of data.
           The tid is specified by 'i'. The size of
           the data for each thread is indicated by VECLEN.
           */
        pthread_create(&threads[tid], &attr, dotprod, (void *)tid); 
    }

    pthread_attr_destroy(&attr);

    /* Wait on the other threads */
    for(tid = 0; tid < NTHREADS; tid++) {
        pthread_join(threads[tid], &status);
        sum += dotstr.sum[tid];
    }
#else
    dotprod((void *) 0);
    sum = dotstr.sum[0];
#endif
    

    /* After joining, print out the results and cleanup */
    free (a);
    free (b);

    printf("%f\n", sum);

    return 0;
}
