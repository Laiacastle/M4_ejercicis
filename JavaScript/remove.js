const button = document.querySelector("#add");
const input = document.querySelector("#textInput");
const list = document.querySelector("#list");





button.addEventListener("click", setAttribute);
function borrar() {
    let node = list.firstChild;
    list.removeChild(node);
}
function setAttribute() {
    let node = document.createElement("li");
    let text = document.createElement("p");
    let buttonR = document.createElement("button");

    text.value = input.value;
    buttonR.innerHTML = "<button>Delete</button>";
    buttonR.addEventListener("click", borrar);
    node.innerHTML = `<li>${text.value} ${buttonR.innerHTML}</li>`;

    list.appendChild(node);

}



