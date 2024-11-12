const button = document.querySelector("#add");
const input = document.querySelector("#textInput");
const list = document.querySelector("#list");

button.addEventListener("click", setAttribute);

function setAttribute() {

    let node = document.createElement("li");
    let text = document.createElement("p");
    text.value = input.value;
    if (text.value.length === 0){
        window.alert("Error! el text es buit.");
    }else {
        node.innerHTML = `<li>${text.value} <button>Delete</button></li>`;
        node.querySelector("button").addEventListener("click", function () {
            node.remove();
        });
        list.appendChild(node);
    }
}



