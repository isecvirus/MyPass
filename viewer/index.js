'use strict';
function add() {
    const index = document.getElementsByClassName("content-table")[0].children[1].children.length + 1;
    const id = document.getElementsByClassName("new-id")[0];
    const password = document.getElementsByClassName("new-password")[0];
    const element = `<tr>
    <td>${index}</td>
    <td>${id.value}</td>
    <td class="password">
        <input type="text" value="${password.value}" spellcheck="false">
    </td>
    <td>${password.value.length}</td>
    </tr>`

    // if (id.value.length > 0 && password.value.length > 0) {
        document.getElementsByClassName("content-table")[0].children[1].innerHTML += element;
        id.value = "";
        password.value = "";
        update_password_length();
    // }
}

function update_password_length() {
    const password_length = document.getElementsByClassName("new-password")[0].value.length;
    document.getElementsByClassName("new-length")[0].innerHTML = password_length;
}

function generate() {
    const rows = document.getElementsByClassName("content-table")[0].children[1].children;
    let data = {};
    for (var r=0;r<rows.length;r++) {
        var id = rows[r].children[1].innerText;
        var password = rows[r].children[2].children[0].value;
        data[id] = password;
    }
    console.log(document.getElementsByClassName("output")[0])
    document.getElementsByClassName("output")[0].value = JSON.stringify(data);
    console.log(data);
}