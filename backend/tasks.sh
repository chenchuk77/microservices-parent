#!/bin/bash

# test script that should be executed by maven exec plugin
echo "listing local docker images (frontend/backend)"
sleep 1s
docker images | grep -E "back|front"
sleep 1s
