const header = document.querySelector("header");
window.onscroll = function() {
    scrolled = true;
}
setInterval(function(){
    if(scrolled) {
        scrolled = false;
        header.style.height = '10px';
        header.style.position = "sticky"
        header.style.top = "3px";
    }
})