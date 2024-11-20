const header = document.querySelector("header");

window.onscroll = function() {
    if(document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {

        header.style.height = "100px";
        header.querySelector('h1').style.fontSize = "30px";
        header.querySelector('#LinkMenu').style.display = 'none';
    }
    else{
        header.querySelector('h1').style.fontSize = "70px";
        header.style.height = '200px';
        header.querySelector('#LinkMenu').style.display = 'block';
    }
}

const button = document.querySelector("button");
let todo = false;
const inputs = document.querySelectorAll("input");
button.addEventListener("click", check);


function check(){
    for (const input of inputs){
        const error = input.parentNode.querySelector(".error");
        const label = input.parentNode.querySelector(".label")
        error.textContent = "";
        if(input.value.length === 0) {
            todo = false;


            error.textContent = `Error! ${label.textContent} is empty`;
            break;


        }else{
            todo = true;
        }


    }

    if(todo){
        window.location.replace("./enviat.html");
    }

}