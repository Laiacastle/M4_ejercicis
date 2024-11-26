const header = document.querySelector("header");
const divB = document.querySelector("#butLik");
const button = divB.firstChild

window.onscroll = function() {
    if(document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {

        header.style.height = "100px";
        header.querySelector('h1').style.fontSize = "30px";
        header.querySelector('.menu').style.display = 'none';
    }
    else{
        header.querySelector('h1').style.fontSize = "70px";
        header.style.height = '200px';
        header.querySelector('.menu').style.display = 'block';
    }
}
button.addEventListener("click", function shoot(){
    if (button.textContent.length < 30) {
        button.textContent = button.textContent.concat("👍");
    }
    confetti({
        ...defaults,
        particleCount: 40,
        scalar: 1.2,
        shapes: ["star"],
    });

    confetti({
        ...defaults,
        particleCount: 10,
        scalar: 0.75,
        shapes: ["circle"],
    });

})

const defaults = {
    spread: 360,
    ticks: 50,
    gravity: 0,
    decay: 0.94,
    startVelocity: 30,
    shapes: ["star"],
    colors: ["FFE400", "FFBD00", "E89400", "FFCA6C", "FDFFB8"],
};


setTimeout(shoot, 0);
setTimeout(shoot, 100);
setTimeout(shoot, 200);
//-----------------------LIST------------
const botimgtor = document.querySelector("#tortuga-img");
const botimgnut = document.querySelector("#nutria-img");
const botimgmur = document.querySelector("#murcielago-img");
botimgtor.addEventListener("click", cambiar);
botimgnut.addEventListener("click", cambiar);
botimgmur.addEventListener("click", cambiar);

function cambiar(){

}