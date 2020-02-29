$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/books"
    }).then(function(data) {
        if(data) {
            var data = data.content;
            var len = data.length;
            var txt = "";

            if(len > 0){
                for(var i=0;i<len;i++){
                    if(data[i].title && data[i].author){
                        txt += "<tr><td>"+data[i].title+"</td><td>"+data[i].author+"</td></tr>";
                    }
                }
                if(txt != ""){
                    $("#allBooks").append(txt).removeClass("hidden");
                }
            }
        }
    });
});