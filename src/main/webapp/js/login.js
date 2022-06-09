$(document).ready(function(){
    
    $('#option').on('click', function(){
        
        $("#nav").css('display','none');
        $("span").remove('#showbtn');
        $("#parentSpan").prepend("<span id=\"hidebtn\"=>▶</span>");
        $('header').css('left', '1.2rem');
        $('#option').css('left', '0rem');
        $('main').css('margin-left', '0px');
        $('footer').css('margin-left', '0px');

        $('#option').on('click', function(){
            $("span").remove('#hidebtn');
            $("#nav").css('display','block');
            $("span").remove('#showbtn');
            $("#parentSpan").prepend("<span>◀</span>");
            $('header').css('left', '8.7rem');
            $('#option').css('left', '7.5em');
            $('main').css('margin-left', '7.5rem');
            $('footer').css('margin-left', '7.5rem');

        });
    })
});