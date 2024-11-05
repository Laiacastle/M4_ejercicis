const Result = document.querySelector("#result");
const Nom = document.querySelector("#nameInput");
const Button = document.querySelector("#button1");
Button.addEventListener("click", setAttribute);

function setAttribute(){
    Result.textContent= `Hello ${Nom.value}` ;
}