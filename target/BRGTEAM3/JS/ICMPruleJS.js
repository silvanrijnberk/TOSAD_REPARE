var selected = null;
var selected2 = null;
var lijst1 = []
var lijst2 = []
var lijstFull = []

function loadTables() {
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/tables");
    fetch(url, {method : 'GET'})
        .then (function(response){
            return (response.text())
        })
        .then (function(data){
            var tables = JSON.parse(data);
            for (let t of tables) {
                var option = document.createElement("option");
                option.text = t.table;
                option.value = t.table;
                document.getElementById("tables").add(option);
                lijst1.push(t.table);
                lijstFull.push(t.table);
            }
            loadColumns();
            updateSelected();
            fillTables2();
        })
}

function loadTablesDouble() {
    loadTables();
}

function loadColumns(tableName) {
    var select = document.getElementById("columns");
    var length = select.options.length;
    for (i = length-1; i >= 0; i--) {
        select.options[i] = null;
    }
    let y = document.getElementById("tables");
    let z = y.options[y.selectedIndex].value;
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/columns/");
    url = url + z;
    fetch(url, {method : 'GET'})
        .then (function(response){
            return (response.text())
        })
        .then (function(data){
            var columns = JSON.parse(data);
            for (let c of columns) {
                var option = document.createElement("option");
                option.text = c.column;
                option.value = c.column;
                document.getElementById("columns").add(option);
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
    let url = new URL ("http://localhost:8081/BRGTEAM3/rest/targetdb/columns/");
    url = url + z;
    fetch(url, {method : 'GET'})
        .then (function(response){
            return (response.text())
        })
        .then (function(data){
            var columns = JSON.parse(data);
            for (let c of columns) {
                var option = document.createElement("option");
                option.text = c.column;
                option.value = c.column;
                document.getElementById("columns2").add(option);
            }
        })
}

function updateSelected() {
    let e = document.getElementById("tables");
    selected = e.options[e.selectedIndex].value;
    console.log(selected);
}

function updateSelected2() {
    let e = document.getElementById("tables2");
    selected2 = e.options[e.selectedIndex].value;
    console.log(selected2);
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

function fillTables1(){
        //empty lijst1 en tables 1
        lijst1 = [];
        emptyTable();
        //fill lijst
        for (i = 0; i < lijstFull.length; i++) {
            if (lijstFull[i] != selected2) {
                lijst1.push(lijstFull[i]);
            }
        }
        //fill tables1
        for (i = 0; i < lijst1.length; i++) {
            var option = document.createElement("option");
            option.text = lijst1[i];
            option.value = lijst1[i];
            document.getElementById("tables").add(option);
        }
        loadColumns();
}

function fillTables2(){
    //empty lijst2 en tables 2
    lijst2 = [];
    emptyTable2();
    //fill lijst2
    for (i = 0; i < lijstFull.length; i++) {
        if (lijstFull[i] != selected) {
            lijst2.push(lijstFull[i]);
        }
    }
    //fill tables2
    for (i = 0; i < lijst2.length; i++) {
        var option = document.createElement("option");
        option.text = lijst2[i];
        option.value = lijst2[i];
        document.getElementById("tables2").add(option);
    }
    loadColumns2Tables2();
}

function emptyTable() {
    var box = document.getElementById("tables");
    var i;
    for(i = box.options.length - 1 ; i >= 0 ; i--) {
        box.remove(i);
    }
}

function emptyTable2() {
    var box = document.getElementById("tables2");
    var i;
    for(i = box.options.length - 1 ; i >= 0 ; i--) {
        box.remove(i);
    }
}

function fillWithSelected(){
    if (lijst2.length == lijstFull.length) {
        var box = document.getElementById("tables2");
        var option = document.createElement("option");
        option.text = selected;
        option.value = selected;
        box.add(option);
        lijst2.push(selected);
    }
}

function fillWithSelected2(){

    var box = document.getElementById("tables");
    var option = document.createElement("option");
    option.text = selected2;
    option.value = selected2;
    box.add(option);
    lijst1.push(selected);
}