#!/bin/bash
for (( counter=1; counter<=5; counter++ ))
do
mongo nbd zapytanie_${counter}_A.js >> wyniki_${counter}_A.json
mongo nbd zapytanie_${counter}_MR.js >> wyniki_${counter}_MR.json
done
