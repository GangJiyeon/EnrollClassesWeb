/**
 * 
 */
 
 $(document).ready(function(){

    $('#showoption').css('display', 'none');
    $('#showparentSpan').css('display', 'none');
    $('#showbtn').css('display', 'none');

    $('#showoption').on('click', function(){

        $("#nav").css('display','block');
        $('header').css('left', '8.7rem');
        $('#hideoption').css('left', '7.5em');
        $('main').css('margin-left', '7.5rem');
        $('footer').css('margin-left', '7.5rem');
        
    });

    $('#hideoption').on('click', function(){
        $('#showoption').css('display', 'block');
        $('#showparentSpan').css('display', 'block');
        $('#showbtn').css('display', 'block');
        $("#nav").css('display','none');
        $('header').css('left', '1.2rem');
        $('#showoption').css('left', '0rem');
        $('main').css('margin-left', '0px');
        $('footer').css('margin-left', '0px');
    });
});