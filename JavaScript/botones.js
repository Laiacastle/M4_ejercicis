const buttons = document.querySelectorAll("button");
const P = document.querySelectorAll("p");
for (const button of buttons) {
    button.addEventListener("click", setAttribute);
}


function setAttribute(){
    for (const p of P) {
        p.textContent= 'One button clicked' ;
    }

}