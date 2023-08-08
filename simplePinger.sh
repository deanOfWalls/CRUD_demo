#!/bin/sh

# Define the endpoint URL of your CRUD app
endpoint_url="http://127.0.0.1:8080/readAll" # Change this to your actual endpoint

# Debug: print the endpoint URL
echo "Endpoint URL: $endpoint_url"

while true; do
  echo "Starting a new iteration at $(date)" # Debug message
  
  # Debug: print the URL being processed
  echo "Processing URL: $endpoint_url"
    
  # Send GET request using curl and display the HTTP status code
  http_code=$(curl -s -o /dev/null -w "%{http_code}" "$endpoint_url")
  echo "HTTP Status Code for $endpoint_url: $http_code"
    
  # Wait 30 seconds to send the next request
  sleep 30

  echo "Ending iteration at $(date)" # Debug message
done
