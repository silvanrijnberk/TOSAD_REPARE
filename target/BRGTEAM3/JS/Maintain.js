function initPage() {
    LoadAll();
    //loadAttributeListRule();
    // loadAttributeOtherRule();
    // loadAttributeRangeRule();
    // loadEntityOtherRule();
    //loadInterEntityCompareRule();
    // loadModifyRule();
    //loadTupleCompareRule();
    // loadTupleOtherRule();
}

function LoadAll() {
    fetch("http://localhost:8081/BRGTEAM3/rest/tooldb/AllRules")
        .then(function (response) {
            return response.json();
        })

        .then(function (json) {
            var frame = document.getElementById("frame");
            console.log(json);
            if (JSON.stringify(json) === '{}')
         {
                var header = document.createElement("h2");
                header.innerHTML = "No Rules Exist!";
                frame.appendChild(header);
            }
            for (var n in json) {
                var div = document.createElement("div");
                var table = document.createElement("table");
                var div_2 = document.createElement("div");
                var header = document.createElement("h2");
                header.innerHTML = n;
                div_2.appendChild(header);
                div.appendChild(div_2);
                div.appendChild(table);
                frame.appendChild(div);
                var row = document.createElement("tr");
                table.appendChild(row);
                for (var p in json[n]) {
                    for (var q in json[n][p]) {
                        var cell = document.createElement("th");
                        cell.innerHTML = q;
                        row.appendChild(cell);
                    }
                    var cell = document.createElement("th");
                    cell.innerHTML = "Delete";
                    row.appendChild(cell);
                    break;
                }

                for (var p in json[n]) {
                    var row = document.createElement("tr");
                    table.appendChild(row);
                    for (var q in json[n][p]) {
                        var cell = document.createElement("td");
                        cell.innerHTML = json[n][p][q];
                        row.appendChild(cell);
                    }
                    var cell = document.createElement("td");
                    let button = document.createElement("button");
                    cell.appendChild(button);
                    row.appendChild(cell)
                    button.textContent = "delete";
                    button.id = "delete";
                    button.setAttribute("class", "deleteAttributeCompareRule");
                    console.log(json[n][p]["RULEID"]);
                    button.value = json[n][p]["RULEID"]+"-;-"+n;
                    //add function to delete
                    button.addEventListener("click", function () {
                        console.log(button.value);
                        var res = button.value.split("-;-");
                        var id = res[0];
                        var table = res[1];
                        fetch("http://localhost:8081/BRGTEAM3/rest/tooldb/delete/"+ table +"/" + id, {
                             method: 'DELETE',
                         })

                             .then(function (response) {
                                if (response.ok) {
                                    console.log("Rule deleted!");
                                } else console.log("Cannot delete rule");
                           })

                        var parent = this.parentNode.parentNode;
                        parent.remove();
                    })


                }

            }
        })



}