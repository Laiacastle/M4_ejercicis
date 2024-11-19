const casillas = document.querySelectorAll('.casilla');
let list = "abcdefghijklmnñopqrstuvwxyzçABCDEFGHIJKLMNÑOPQRSTUVWXYZÇ阿贝色车德俄非伊"
let indexer = 0;

function getRandomIntInclusive(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1) + min);
}

let minas = [64]
for (const mina of minas) {
    let trues = 0;
    if (trues < 10){
        console.log("pocos trues");
        minas[mina] = Boolean(getRandomIntInclusive(0, 1));
    }
    else{
        console.log("muchos trues");
        minas[mina] = false;
    }
    if (minas[mina]){
        console.log("sumo trues");
        trues ++;
    }
    console.log(minas[mina]);
}
for(const casilla of casillas){
    casilla.setAttribute("id", `${list[indexer]}`);
    if (minas[indexer]){
        document.querySelector(`#${list[indexer]}`).textContent = "💣";
        console.log("tomate tomate tomalo")
    }
    indexer ++;
}


const prueba = document.querySelector('#a');
prueba.addEventListener("click", Minas);
function Minas(){
    prueba.style.backgroundColor = "white";
    prueba.style.color = "black";
}