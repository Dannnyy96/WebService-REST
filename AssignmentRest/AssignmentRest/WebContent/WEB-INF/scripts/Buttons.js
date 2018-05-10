function XMLgetAllFilms() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/AssignmentRest/rest/films/getAllFilmsXML", true);
    xhttp.setRequestHeader("Content-type", "text/html");
    xhttp.send();
    var response = JSON.parse(xhttp.responseText);
}

function JSONgetAllFilms() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/AssignmentRest/rest/films/getAllFilmsJSON", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    var response = JSON.parse(xhttp.responseText);
}