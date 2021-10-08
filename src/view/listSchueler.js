$(document).ready(function () {
    listschueler();
});
function listschueler() {
var URL = "https://api.npoint.io/0032fa0bc1702e0b27e5";
    $.getJSON(URL).done(function (data) {
        var table = document.getElementById("schueler");
        var thead = document.createElement('thead');

        thead.appendChild(document.createElement("th")).appendChild(document.createTextNode("image"));
        thead.appendChild(document.createElement("th")).appendChild(document.createTextNode("firstName"));
        thead.appendChild(document.createElement("th")).appendChild(document.createTextNode("name"));

        table.appendChild(thead);
        var klasse = data[0].klasse;
        var klasseTag = document.getElementById("klasse");
        klasseTag.innerText = klasse;
        console.log(klasse);
        $.each(data, function (index, schueler) {
            var klasse = schueler.klasse;
            var name = schueler.name;
            var firstname = schueler.firstName;
            var image = schueler.image;

            console.log(schueler.image);


            var row = table.insertRow(-1)

            var cell = row.insertCell(-1)

            cell.innerHTML = "<img src=\"" + image + "\"width=\"100px\" height=\"100px\">";

            cell = row.insertCell(-1)

            cell.innerHTML = firstname;

            cell = row.insertCell(-1)

            cell.innerHTML = name;






        })

    })
}