const refresh = document.querySelector("#refresh");
const info = document.querySelector("#info");
const butemoji = document.querySelector("#emoji");
const infop = document.querySelector("#infop");
let emoji;
infop.style.display = 'none';
let visible = false;
refresh.addEventListener("click",temp())
async function temp(){

    {
        const url = "https://fp.mateuyabar.com/DAM-M04/UF2/exercicis/weather.json";
        const response = await fetch(url);
        const json = await response.json()
        const tiempo = await json.weather[0].main;
        switch(tiempo){
            case "Clear": emoji= "☀"; break;
            case "Rain": emoji = '🌨'; break;
            case "Storm": emoji = "🌩"; break;
            default: emoji = "Error"; break;
        }
        butemoji.textContent =  emoji;
    }
}
temp()
info.addEventListener("click", function(){

    if (visible){
        infop.style.display = 'none';
        visible = false;
    }
    else{
        infop.style.display = 'block';
        visible = true;
    }
})