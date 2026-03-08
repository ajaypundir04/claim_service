#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Starting Claim Service Demo Setup...${NC}"

# 1. Build the project
echo -e "${GREEN}Building the project with Maven...${NC}"
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "Maven build failed. Please check the errors."
    exit 1
fi

# 2. Start Docker containers
echo -e "${GREEN}Starting Docker containers...${NC}"
docker-compose up -d --build

if [ $? -ne 0 ]; then
    echo "Docker Compose failed. Please check the errors."
    exit 1
fi
