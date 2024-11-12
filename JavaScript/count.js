const button1 = document.querySelector("#button1");
const button2 = document.querySelector("#button2");
const buttons = document.querySelectorAll("button");
const p1 = document.querySelector("#p1");
const p2 = document.querySelector("#p2");
let num = 1;
let num2 = 1;
button1.addEventListener("click", function(){
    p1.textContent = num;
    num++;
});
button2.addEventListener("click", function(){
    p2.textContent = num2;
    num2  ++;
});

