#!/bin/bash

src=
dest=

registry=./seq_nr
today=$(date +%Y%m%d)
read -r last_date last_seq < $registry

if [[ $last_date == $today ]]; then
	seq=$((last_seq+1))
else
	seq=0
fi

prefix=fdsa
fname=${prefix}_$today

echo "$fname"
