const header = document.querySelector("header");
const form = document.querySelector('form');

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
        const error = input.parentNode.parentNode.querySelector(".error");
        const label = input.parentNode.parentNode.querySelector("label")
        error.textContent = "";
        if(input.value.length === 0) {
            todo = false;
            error.textContent = `Error! ${label.textContent} is empty`;
            break;
        }
        else if (label.textContent.includes('correu') && !input.value.includes('@')){
            todo = false;
            console.log(input.textContent);
            error.textContent = `Error! ${label.textContent} is not an email!`;
            break;
        }
        else{
            todo = true;
        }
    }

    if(todo){

        form.setAttribute("action","https://formspree.io/f/xnnqaaoe" );
        button.setAttribute("type", "submit")
        form.submit();

    }

}
