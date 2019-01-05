/**
 * 
 */

var API_KEY= 'CODE_SAMPLES_KEY_9d3608187'
var BASE_URL = 'https://api.betterdoctor.com/2016-03-01/doctors?location=37.773,-122.413,100&skip=2&limit=10&user_key=';

$(document).ready(function(){
    $("#add_specialty").click(function () {
    	  $('#specialty_parent').append($('#specialty_container').html());
    });
});
$(document).ready(function(){
    $("#add_insurance").click(function () {
    	  $('#insurance_parent').append($('#insurance_container').html()); 
    });
});