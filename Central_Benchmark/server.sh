#!/bin/bash
repeats=3
output_file='resultsFileBash.txt'
while read exec; do
	command_to_run="./Executables/"$exec;
	echo 'Benchmarking ' $exec;
    sum=0.0
	for (( i = 1; i <= $repeats ; i++ ))
    do
        # percentage completion
        p=$(( $i * 100 / $repeats))
        # indicator of progress
        l=$(seq -s "+" $i | sed 's/[0-9]//g')

        # runs time function for the called script, output in a comma seperated
        # format output file specified with -o command and -a specifies append
        #/usr/bin/time -lp "%E,%U,%S" -o ${output_file} -a ${command_to_run} > ${output_file} 2>&1
        
       	#(time ${command_to_run}) 2>>${output_file} #>> will append whereas > will wipe out !
    	#utime="$( TIMEFORMAT='%U';time ${command_to_run} 2>&1 1>/dev/null )"
        #echo 'usertime' $utime

        #stime="$( TIMEFORMAT='%S';time ${command_to_run} 2>&1 1>/dev/null )"
        #echo 'systime' $stime

        #echo 'ttime'
        ttime=$(echo $( TIMEFORMAT="%3U + %3S"; { time ${command_to_run}; } 2>&1 1>/dev/null) "*1000" | bc -l)
        #echo "$ttime"

        sum=`echo "$sum+$ttime" | bc -l`

        #echo -ne ${l}' ('${p}'%) \r' #for the progress
    done;
    echo 'sum'
    echo $sum >> $output_file
    #echo -ne '\n'

    # Convenience seperator for file
    #echo '--------------------------' >> $output_file

done < execToRun.txt