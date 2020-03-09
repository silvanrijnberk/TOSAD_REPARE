var selected = null;
var lijst1 = []
var lijst2 = []

function loadTables() {
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/tables");
    fetch(url, {method : 'GET'})
        .then (function(response){
            console.log(response);
            return (response.text())
        })
        .then (function(data){
            var tables = JSON.parse(data);
            for (let t of tables) {
                var option = document.createElement("option");
                option.text = t.table;
                option.value = t.table;
                document.getElementById("tables").add(option);
                console.log(t.table);
            }
            loadColumns();
            loadColumns2();
        })
}

function loadTables2() {
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/tables");
    fetch(url, {method : 'GET'})
        .then (function(response){
            console.log(response);
            return (response.text())
        })
        .then (function(data){
            var tables = JSON.parse(data);
            for (let t of tables) {
                var option = document.createElement("option");
                option.text = t.table;
                option.value = t.table;
                document.getElementById("tables2").add(option);
                console.log(t.table);
            }
        })
}

function loadTablesDouble() {
    loadTables();
    loadTables2();
}

function loadColumns(tableName) {
    var select = document.getElementById("columns");
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
        select.options[i] = null;
    }
    let y = document.getElementById("tables");
    let z = y.options[y.selectedIndex].value;
    console.log(z);
    console.log(y);
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/columns/");
    url = url + z;
    fetch(url, {method : 'GET'})
        .then (function(response){
            console.log(response);
            return (response.text())
        })
        .then (function(data){
            var columns = JSON.parse(data);
            for (let c of columns) {
                var option = document.createElement("option");
                option.text = c.column;
                option.value = c.column;
                document.getElementById("columns").add(option);
                console.log(c.column);
            }
        })
}

function loadColumns2(tableName) {
    var select = document.getElementById("columns2");
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
        select.options[i] = null;
    }
    let y = document.getElementById("tables");
    let z = y.options[y.selectedIndex].value;
    console.log(z);
    console.log(y);
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/columns/");
    url = url + z;
    fetch(url, {method : 'GET'})
        .then (function(response){
            console.log(response);
            return (response.text())
        })
        .then (function(data){
            var columns = JSON.parse(data);
            for (let c of columns) {
                var option = document.createElement("option");
                option.text = c.column;
                option.value = c.column;
                document.getElementById("columns2").add(option);
                console.log(c.column);
            }
        })
}

function loadColumns2Tables2(tableName) {
    var select = document.getElementById("columns2");
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
        select.options[i] = null;
    }
    let y = document.getElementById("tables2");
    let z = y.options[y.selectedIndex].value;
    console.log(z);
    console.log(y);
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/columns/");
    url = url + z;
    fetch(url, {method : 'GET'})
        .then (function(response){
            console.log(response);
            return (response.text())
        })
        .then (function(data){
            var columns = JSON.parse(data);
            for (let c of columns) {
                var option = document.createElement("option");
                option.text = c.column;
                option.value = c.column;
                document.getElementById("columns2").add(option);
                console.log(c.column);
            }
        })
}

function loadColumnsDouble() {
    loadColumns();
    loadColumns2();
}

function updateSelected() {
    let e = document.getElementById("tables");
    selected = e.options[e.selectedIndex].value;
    removeSelected();
}

function removeSelected() {
    var select = document.getElementById("tables2");
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
        if (select.options[i].value == selected) {
            select.options[i] = null;
        }
    }
}