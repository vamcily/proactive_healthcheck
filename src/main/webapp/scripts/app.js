/*$(function(){

$('#trigger').click(function(){
  $('#myModal').modal('show');
  return false;
})

});*/

function clickAction(){
  $("#myModal").modal({backdrop:false});
  $('#myModal').modal('show');
  return false;
}

function setLoadIcon(){
   d3.select(clickedImage).attr("xlink:href", function(d) {return "loading-icon.gif";});
}

function completeFix() { setCompleteIcon(clickedImage); }



function setCompleteIcon(me) {
  d3.select(me).attr("xlink:href", function(d) {return "check-icon.png";});
  if(me.id=="image0"){
    d3.select("#image1").attr("xlink:href", function(d) {return "check-icon.png";});
    d3.select("#image2").attr("xlink:href", function(d) {return "check-icon.png";});
  } else if(me.id=="image1") {
    d3.select("#image2").attr("xlink:href", function(d) {return "check-icon.png";});
  }
  upgradeScore(me);
  changeScoreJson(root);
}


function  upgradeScore(me){
  if(me.id == "image0") {
    var g0 = $("#image0").parent();
    var g1 = $("#image1").parent();
    var g2 = $("#image2").parent();

    g0.children("#score").text(100);//change score to 100
    g0.children("circle").css("fill","#8BC34A");
    g1.children("#score").text(100);//change score to 100
    g1.children("circle").css("fill","#8BC34A");
    g2.children("#score").text(100);//change score to 100
    g2.children("circle").css("fill","#8BC34A");
  } else if(me.id=="image1") {
    var g1 = $("#image1").parent();
    var g2 = $("#image2").parent();

    g1.children("#score").text(100);//change score to 100
    g1.children("circle").css("fill","#8BC34A");
    g2.children("#score").text(100);//change score to 100
    g2.children("circle").css("fill","#8BC34A");
  } else if(me.id=="image2") {
    var g2 = $("#image2").parent();

    g2.children("#score").text(100);//change score to 100
    g2.children("circle").css("fill","#8BC34A");
  }
}

function changeScoreJson(data){
  data.score = 100;
  var children = data.children;
  if(children != null){
    for(var i in children){
      changeScoreJson(children[i]);
    }

  }
}