function checkAppStatus() {
    fetch('/health-check')
        .then(response => {
            if (response.status === 200) {
                return response.text();
            }
            throw new Error('Not up yet');
        })
        .then(status => {
            if (status === 'UP') {
                document.getElementById('loadingSection').style.display = 'none';
                document.getElementById('mainAppSection').style.display = 'block';
            } else {
                setTimeout(checkAppStatus, 5000);
            }
        })
        .catch(error => {
            setTimeout(checkAppStatus, 5000);
        });
}

checkAppStatus();
