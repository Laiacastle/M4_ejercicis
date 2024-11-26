const header = document.querySelector("header");
const divB = document.querySelector(".menu");
const button = divB.firstChild

window.onscroll = function() {
    if(document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {

        header.style.height = "100px";
        header.querySelector('h1').style.fontSize = "30px";
        divB.style.display = 'none';
    }
    else{
        header.querySelector('h1').style.fontSize = "70px";
        header.style.height = '200px';
        divB.style.display = 'block';
    }
}
