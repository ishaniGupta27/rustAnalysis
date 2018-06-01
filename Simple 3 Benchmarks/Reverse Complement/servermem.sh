#!/bin/bash

repeats=50
output_file='resultsMemory.txt'
while read exec; do
    
	command_to_run="./memusg3 "$exec"  < revcomp-input100000000.txt ";
	echo 'Benchmarking ' $command_to_run;
    sum=0
    zeroVal=0
	for (( i = 1; i <= $repeats ; i++ ))
    do
        # percentage completion
        p=$(( $i * 100 / $repeats))
        # indicator of progress
        l=$(seq -s "+" $i | sed 's/[0-9]//g')

        # runs time function for the called script, output in a comma seperated
        # format output file specified with -o command and -a specifies append
        #/usr/bin/time -lp "%E,%U,%S" -o ${output_file} -a ${command_to_run} > ${output_file} 2>&1
        #curl https://sh.rustup.rs -sSf | sh
       	#(time ${command_to_run}) 2>>${output_file} #>> will append whereas > will wipe out !
    	#utime="$( TIMEFORMAT='%U';time ${command_to_run} 2>&1 1>/dev/null )"
        #echo 'usertime' $utime

        #stime="$( TIMEFORMAT='%S';time ${command_to_run} 2>&1 1>/dev/null )"
        #echo 'systime' $stime

        #echo 'ttime'.r
        #echo 'yo'
        memy=`${command_to_run} 2>&1 1>/dev/null`  #>> $output_file
        if [[ $memy -eq 0 ]]
        then
            #echo Zero
            zeroVal=$((zeroVal + 1))
        fi
       # echo "memy ""$memy"
        
        sum=$((sum + memy))
        #echo "sum" "$sum"

        
        #echo -ne ${l}' ('${p}'%) \r' #for the progress
    done;
    #echo 'sum'
    totalLoop=$((repeats - zeroVal))
    echo 'Test For..' $exec >> $output_file
    echo "Total Sum" $sum >> $output_file
    echo "Total Iterations..." $totalLoop >> $output_file
    #echo -ne '\n'

    # Convenience seperator for file
    echo '--------------------------' >> $output_file

done < execToRun.txt
