var user;
var books;

function getUser(){
    user = prompt("Please enter your name.");
}
window.onload=getUser();


$(document).ready(function() {
    $.ajax({
        // url: "http://localhost:8080/books"
        url: "https://amazin-online-bookstore.herokuapp.com/books"
    }).then(function(data) {
        if(data) {
            books = data.content
            var len = books.length;
            var book = "";
            var genreFilter = "";
            var authorFilter = "";

            if(len > 0){
                for(var i=0;i<len;i++){
                    if(books[i].title && books[i].author && books[i].cost){
                        genreFilter += "<a class=\"dropdown-item\" href=\"#\">"+books[i].genre+"</a>"
                        authorFilter += "<a class=\"dropdown-item\" href=\"#\">"+books[i].author+"</a>"

                        book += "<div class=\"card\">" +
                            "       <div class=\"card-body\">" +
                            "           <h5 class=\"card-title\ title\">"+books[i].title+"</h5>" +
                            "           <h8 class=\"card-text\ author\">"+"by "+books[i].author+"</h8>" +
                            "           <p class=\"card-text\ cost\">"+"$"+books[i].cost+"</p>" +
                            "           <a class=\"btn btn-info\ add-to-cart-btn\">Add to cart</a>" +
                            "       </div>" +
                            "     </div>";
                    }
                }
                if(book != ""){
                    $(".bookstore-books").append(book);
                }

                if(genreFilter != ""){
                    $(".dropdown-menu-genre").append(genreFilter);
                }

                if (authorFilter != ""){
                    $(".dropdown-menu-author").append(authorFilter);
                }
            }
        }
    });
});