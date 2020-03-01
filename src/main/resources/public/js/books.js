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
                        txt += "<div class=\"card\"><div class=\"card-body\"><h5 class=\"card-title\">"+data[i].title+"</h5><h8 class=\"card-text\">"+"by "+data[i].author+"</h8><p class=\"card-text\">"+"$"+data[i].cost+"</p><a href=\"#\" class=\"btn btn-primary\ add-to-cart-btn\">Add to cart</a></div></div>";
                    }
                }
                if(txt != ""){
                    $("#allBooks").append(txt).removeClass("hidden");
                }
            }
        }
    });
});