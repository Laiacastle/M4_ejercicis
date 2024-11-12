const button = document.querySelector("#button");
const edat = document.querySelector("#edat");
const menor = document.querySelector("#menor");
const inputs = document.querySelectorAll("input");
button.addEventListener("click", check);
menor.style.display = 'none';
let mono = true;
function check(){
    for (const input of inputs){
        const error = input.parentNode.querySelector(".error");
        const label = input.parentNode.querySelector(".label")
        error.textContent = "";
        if(input.value.length === 0) {
            if(mono){

                error.textContent = `Error! ${label.textContent} is empty`;
                break;
            }
            else{
                if(input !== document.querySelector("#TutorN") && document.querySelector("#TutorC")){
                    error.textContent = `Error! ${label.textContent} is empty`;
                    break;
                }
            }
        }

    }
    if (edat.value < 18) {
        menor.style.display = 'block';
        mono = true;
    }else{

        menor.style.display = 'none';
        //mono = false;
    }

}