$(function() {

    $('#subCon1').css('display', 'none');
    $('#subCon2').css('display', 'none');
    $('#subCon3').css('display', 'none');
    $('#subCon5').css('display', 'none');

   	$("#hideSubTitle1").css('display', 'none');
   	$("#hideSubTitle2").css('display', 'none');
   	$("#hideSubTitle3").css('display', 'none');
   	$("#hideSubTitle5").css('display', 'none');
    
    $("#showSubTitle1").on('click', function() {
		$("#showSubTitle1").css('display', 'none')
        $('#subCon1').css('display', 'block');
        $("#hideSubTitle1").css('display', 'block');
    }); 
    
    $("#hideSubTitle1").on('click', function() {
		$("#showSubTitle1").css('display', 'block')
        $('#subCon1').css('display', 'none');
        $("#hideSubTitle1").css('display', 'none');
    }); 
    
    $("#showSubTitle2").on('click', function() {
		$("#showSubTitle2").css('display', 'none')
        $('#subCon2').css('display', 'block');
        $("#hideSubTitle2").css('display', 'block');
    }); 
    
    $("#hideSubTitle2").on('click', function() {
		$("#showSubTitle2").css('display', 'block')
        $('#subCon2').css('display', 'none');
        $("#hideSubTitle2").css('display', 'none');
    }); 
    
    $("#showSubTitle3").on('click', function() {
		$("#showSubTitle3").css('display', 'none')
        $('#subCon3').css('display', 'block');
        $("#hideSubTitle3").css('display', 'block');
    }); 
    
    $("#hideSubTitle3").on('click', function() {
		$("#showSubTitle3").css('display', 'block')
        $('#subCon3').css('display', 'none');
        $("#hideSubTitle3").css('display', 'none');
    }); 
    
     $("#showSubTitle5").on('click', function() {
		$("#showSubTitle5").css('display', 'none')
        $('#subCon5').css('display', 'block');
        $("#hideSubTitle5").css('display', 'block');
    }); 
    
    $("#hideSubTitle5").on('click', function() {
		$("#showSubTitle5").css('display', 'block')
        $('#subCon5').css('display', 'none');
        $("#hideSubTitle5").css('display', 'none');
    }); 

 });  