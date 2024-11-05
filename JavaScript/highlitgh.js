const button = document.querySelector("#button1");
const welcome = document.querySelector("#welcome");

button.addEventListener("click", setAttribute);

function setAttribute(){
    welcome.style.backgroundColor = 'green';
    welcome.style.fontSize = '30px';
    welcome.style.weight = 'bold';
}