window.ScheduleController = new ScheduleController();
$("button").click(function(){
	  $.getJSON("http://localhost:8080/categorias/1", function(result){
	    $.each(result, function(i, field){
	      $("div").append(field + " ");
	    });
	  });
	});