    class Person{
        constructor(id, firstName, lastName, birthDate){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    }
}


    function create(event){
    event.preventDefault(); //prevent default submission since asynchronous

    const personIdElement = document.getElementById("person-id");
    const firstNameElement = document.getElementById("first-name");
    const lastNameElement = document.getElementById("last-name");
    const birthDateElement = document.getElementById("birth-date");

    const personIdValue = personIdElement.value;
    const firstNameValue = firstNameElement.value;
    const lastNameValue = lastNameElement.value;
    const birthDateValue = birthDateElement.value;
    const person = new Person(personIdValue, firstNameValue, lastNameValue, birthDateValue);


    //call the create function of the spring boot app with the values
    const personData = JSON.stringify(person);
    console.log(personData);

    $.ajax({
        type: "POST",
        crossDomain: true,
        headers: {
            'Accept' :  'application/json',
            'Content-Type' : 'application/json',
            'Access-Control-Allow-Origin' : '*'
        },
        url: "/create",
        data: personData,
        dataType: "JSON",
        success: function(response) {
            alert(JSON.stringify(response));
        },        
    });
}

async function readAll(event) {
    event.preventDefault(); // Prevent the default form submission //by adding this to the button click event handler the default form submission is prevented and page won't refresh immediately after click, instead ajax requests will be sent

    try {
        const response = await $.ajax({
            type: "GET",
            crossDomain: true,
            headers: {
                'Accept':'application/json',
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/readAll",
        });
        alert(JSON.stringify(response));
    } catch (error) {
        alert("An error occurred during the readAll request.");
    }

    // Refresh the page or execute other code after the request is completed
}

async function readById(event) {
    event.preventDefault();
    const personIdElement = document.getElementById("person-id");
    const personIdValue = personIdElement.value;

    try {
        const response = await $.ajax({
            type: "GET",
            crossDomain: true,
            headers: {
                'Accept':'application/json',
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/read/" + personIdValue,
        });
        alert(JSON.stringify(response));
    } catch (error) {
        alert("An error occurred during the readById request.");
    }
}

async function update(event) {
    event.preventDefault();

    const personIdElement = document.getElementById("person-id"); // Get the input element for the person ID
    const personIdValue = personIdElement.value; // Get the value of the person ID

    const firstNameElement = document.getElementById("first-name"); // Get the input element for the first name
    const lastNameElement = document.getElementById("last-name"); // Get the input element for the last name
    const birthDateElement = document.getElementById("birth-date"); // Get the input element for the birth date

    const firstNameValue = firstNameElement.value; // Get the value of the first name
    const lastNameValue = lastNameElement.value; // Get the value of the last name
    const birthDateValue = birthDateElement.value; // Get the value of the birth date

    console.log("Updating Person Data:", {
        id: personIdValue,
        firstName: firstNameValue,
        lastName: lastNameValue,
        birthDate: birthDateValue,
    });

    try {
        const response = await $.ajax({
            type: "PUT",
            crossDomain: true,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/update/" + personIdValue,
            data: JSON.stringify({
                id: personIdValue,
                firstName: firstNameValue,
                lastName: lastNameValue,
                birthDate: birthDateValue,
            }),
        });

        console.log("Update Response:", response); // Log the server response
        alert(JSON.stringify(response));
    } catch (error) {
        console.error("Update Error:", error); // Log any error messages
        alert("An error occurred during the update request.");
    }
}






async function deleteThing(event) {
    event.preventDefault();

        const personIdElement = document.getElementById("person-id"); // Get the input element for the person ID
        const personIdValue = personIdElement.value; // Get the value of the person ID

    try {
        const response = await $.ajax({
            type: "DELETE",
            crossDomain: true,
            headers: {
                'Accept':'application/json',
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/delete/" + personIdValue,
        });
        alert(JSON.stringify(response));
    } catch (error) {
        alert("An error occurred during the deleteThing request.");
    }
}







