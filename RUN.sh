#!/bin/bash

find . -name "*.java" > sources.txt

javac -d out @sources.txt || { echo "Compilation failed"; exit 1; }

if [ ! -f scenario.txt ]; then
    echo "Error: scenario.txt not found!"
    exit 1
fi

java -cp out ro.academyplus.avaj.simulator.Simulator scenario.txt > simulation.txt
