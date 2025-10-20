#!/bin/zsh

# Compile source files
javac src/*.java

# Compile test files
javac -cp "libs/junit-platform-console-standalone-1.0.0.jar:src" -d test test/*.java

# Run tests
java -jar libs/junit-platform-console-standalone-1.0.0.jar --class-path test:src --scan-class-path
