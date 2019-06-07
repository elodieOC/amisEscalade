$(document).ready(function() {
    var windowHeight = $(window).height();
    if(windowHeight < 769){
        $("#search-header").click(function () {
            $("#search-list").toggle(1000);
        });
    }
});