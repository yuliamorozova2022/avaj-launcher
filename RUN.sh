#!/bin/bash
find . -name "*.java" > sources.txt
javac @sources.txt
java ro.academyplus.avaj.simulator.Simulator scenario.txt
