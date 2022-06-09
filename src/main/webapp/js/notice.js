$(function(){
    $('#content2').css('display', 'none');
    $('#content3').css('display', 'none');
    $('#tabTitle1').css('background-color', 'lightsteelblue');

    $('#tabTitle1').on('click', function(){
        $('#tabTitle1').css('background-color', 'lightsteelblue');
        $('#tabTitle2').css('background-color', 'white');
        $('#tabTitle3').css('background-color', 'white');
        $('#tabTitle1').css('border-bottom', 'none');
        $('#tabTitle2').css('border-bottom', 'solid 1px rgb(215, 215, 215)');
        $('#tabTitle3').css('border-bottom', 'solid 1px rgb(215, 215, 215)');
        $('#content1').css('display', 'block');
        $('#content2').css('display', 'none');
        $('#content3').css('display', 'none');
    });

    $('#tabTitle2').on('click', function(){
        $('#tabTitle2').css('background-color', 'lightsteelblue');
        $('#tabTitle1').css('background-color', 'white');
        $('#tabTitle3').css('background-color', 'white');
        $('#tabTitle1').css('border-bottom', 'solid 1px rgb(215, 215, 215)');
        $('#tabTitle2').css('border-bottom', 'none');
        $('#tabTitle3').css('border-bottom', 'solid 1px rgb(215, 215, 215)');
        $('#content1').css('display', 'none');
        $('#content2').css('display', 'block');
        $('#content3').css('display', 'none');
    });

    $('#tabTitle3').on('click', function(){
        $('#tabTitle3').css('background-color', 'lightsteelblue');
        $('#tabTitle1').css('background-color', 'white');
        $('#tabTitle2').css('background-color', 'white');
        $('#tabTitle1').css('border-bottom', 'solid 1px rgb(215, 215, 215)');
        $('#tabTitle2').css('border-bottom', 'solid 1px rgb(215, 215, 215)');
        $('#tabTitle3').css('border-bottom', 'none');
        $('#content1').css('display', 'none');
        $('#content2').css('display', 'none');
        $('#content3').css('display', 'block');
    });

});

$(function(){
    $("#notice3").css('display', 'none');
});

