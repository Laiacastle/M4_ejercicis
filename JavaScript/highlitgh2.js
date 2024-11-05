const button = document.querySelector("#button1");
const welcome = document.querySelector("#welcome");
let cla = false;
button.addEventListener("click", setAttribute);

function setAttribute(){
        welcome.classList.toggle('highligth');
}