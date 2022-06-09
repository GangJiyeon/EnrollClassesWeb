/**
 * 
 */
$(function() {
    $('#sub').hide();
    $('#main_nav').hover(function() {
        $(this).parent().find('#sub').slideDown('fast');
        $(this).parent().hover(function() {
            
        }, function() {
            $(this).parent().find('#sub').slideUp('fast');
        });
    });
});






