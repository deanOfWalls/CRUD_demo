// Function to poll the health-check endpoint
function checkAppStatus() {
    fetch('/health-check')
        .then(response => {
            // Check for a good status
            if (response.status === 200) {
                return response.text();
            }
            // For any other status, throw an error to go to the catch block.
            throw new Error('Not up yet');
        })
        .then(status => {
            if (status === 'UP') {
                // App is up! Display the main section and hide the loading section
                document.getElementById('loadingSection').style.display = 'none';
                document.getElementById('mainAppSection').style.display = 'block';
            } else {
                // If the app sends back a response other than "UP", continue polling
                setTimeout(checkAppStatus, 5000); // Check every 5 seconds
            }
        })
        .catch(error => {
            // App is not up yet. Continue polling.
            setTimeout(checkAppStatus, 5000); // Check every 5 seconds
        });
}

// Start the polling as soon as the script is loaded
checkAppStatus();
