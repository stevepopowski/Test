jQuery(function(){
			jQuery('#example').superfish({
				//useClick: true
			});
		});
$(document).ready(function(){	
});
$(function() {
    $( "#slider-range-min" ).slider({
      range: "min",
      value: 3,
      min: 1,
      max: 10,
      slide: function( event, ui ) {
        $( "#vlan" ).val( ui.value );
      }
    });
    $( "#vlan" ).val( $( "#slider-range-min" ).slider( "value" ) );
  });
  
$(function() {
    $( "#bandwidth-radio" ).buttonset();
  });
  
  $(function() {
  	$('#radio2').click(function(){
  		$('.bandwidth').show();
  	});
  	$('#radio1').click(function(){
  		$('.bandwidth').hide();
  	});	
  	});

$(function() {
    $( ".datepicker" ).datepicker();
  });