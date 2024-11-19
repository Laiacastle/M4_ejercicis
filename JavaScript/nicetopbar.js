const header = document.querySelector("header");

window.onscroll = function() {
    console.log(document.body.scrollTop);
    console.log(document.documentElement.scrollTop);
    if(document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        header.style.fontSize = "10px";
        header.style.height = "100px";
    }
    else{
        header.style.fontSize = "50px";
        header.style.height = "600px";
    }
}
