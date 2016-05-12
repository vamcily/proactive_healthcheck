/*$(function(){

$('#trigger').click(function(){
  $('#myModal').modal('show');
  return false;
})

});*/

function clickAction(me){
	
  d3.select(me).attr("xlink:href", function(d) {return "loading-icon.gif";});
  $('#myModal').modal('show');
  setTimeout( function completeFix() { setCompleteIcon(me); }, 13000 );
  return false;
}

function setCompleteIcon(me) {
	d3.select(me).attr("xlink:href", function(d) {return "check-icon.png";});
}