#!/bin/bash

# Color definitions
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
MAGENTA='\033[0;35m'
CYAN='\033[0;36m'
WHITE='\033[1;37m'
BOLD='\033[1m'
RESET='\033[0m'

echo -e "${CYAN}${BOLD}╔════════════════════════════════════════╗${RESET}"
echo -e "${CYAN}${BOLD}║     ${WHITE}Tic-Tac-Toe Build System${CYAN}      ║${RESET}"
echo -e "${CYAN}${BOLD}╚════════════════════════════════════════╝${RESET}"
echo ""

echo -e "${YELLOW}🧹 Cleaning build directory...${RESET}"
rm -rf out

echo -e "${BLUE}📦 Compiling Java sources...${RESET}"
javac -d out src/Main.java

if [ $? -eq 0 ]; then
    echo -e "${GREEN}${BOLD}✓ Compilation successful!${RESET}"
    echo ""
    echo -e "${MAGENTA}${BOLD}╔════════════════════════════════════════╗${RESET}"
    echo -e "${MAGENTA}${BOLD}║         ${WHITE}Starting Game...${MAGENTA}          ║${RESET}"
    echo -e "${MAGENTA}${BOLD}╚════════════════════════════════════════╝${RESET}"
    echo ""
    java -cp out Main
else
    echo -e "${RED}${BOLD}✗ Compilation failed!${RESET}"
    exit 1
fi
