/*$("#myModal").draggable({   
    handle: ".modal-header",   
    cursor: 'move',   
    refreshPositions: true  
}); 
*/
function clickAction(serialNumber) {
	$("#myModal").draggable();
	$("#myModal").modal({
		backdrop: false
	});
	$('#myModal').modal('show');
	return false;
}

function setLoadIcon() {
	d3.select(clickedImage).attr("xlink:href", function(d) {
		return "loading-icon.gif";
	});
}

function completeFix() {
	//setCompleteIcon(clickedImage);
	$.ajax({
		type: "GET",
		url: qUrl,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			updateData(data);
			//root = data;
			update(root);
		},
		error: function(msg) {
			alert(msg);
		}
	});
}

function updateData(data) {
	root.score = data.score;
	root.actions = data.actions;
	for (var i in root.children) {
		updateScoreActions(root.children[i], data.children[i]);
		if (root.children[i].children) {
			for (var j in root.children[i].children) {
				updateScoreActions(root.children[i].children[j], data.children[i].children[j]);
			}
		} else if (root.children[i]._children) {
			for (var j in root.children[i]._children) {
				updateScoreActions(root.children[i]._children[j], data.children[i].children[j]);
			}
		}
	}
}

function updateScoreActions(d,ad){
	d.score = ad.score;
	if(d.actions)
		d.actions = ad.actions;
}


// Initialize the display to show a few nodes.
// dingj2
//root.children.forEach(toggleAll);

/*function setCompleteIcon(me) {
	d3.select(me).attr("xlink:href", function(d) {
		return "check-icon.png";
	});
	if (me.id == "image0") {
		d3.select("#image1").attr("xlink:href", function(d) {
			return "check-icon.png";
		});
		d3.select("#image2").attr("xlink:href", function(d) {
			return "check-icon.png";
		});
		changeScoreJson(root);
	} else if (me.id == "image1") {
		d3.select("#image2").attr("xlink:href", function(d) {
			return "check-icon.png";
		});
		changeScoreJson(root.children[0]);
		updateParentScore(root.children[0]);
	} else {
		changeScoreJson(root.children[0].children[0]);
		updateParentScore(root.children[0].children[0]);
	}

	update(root);
}*/

/*
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
*/
/*
function changeScoreJson(data) {
	data.score = 100;
	if (data.actions != null)
		data.actions = "Fixed";
	var children = data.children;
	if (children != null) {
		for (var i in children) {
			changeScoreJson(children[i]);
		}

	}
}

function updateParentScore(data) {
	var hasChildNeedFix = false;
	var hasChildTotalFixed = false;
	var hasChildPartFixed = false;
	var parent = data.parent;

	if (parent != null) {
		var temp = parent.actions;
		parent.actions = "";
		for (var childindex in parent.children) {
			var child = parent.children[childindex];
			if (child.actions != null) {
				if (child.actions != "Fixed" && child.actions != null) {
					hasChildNeedFix = true;
					parent.actions += child.actions;
				} else if (child.actions == "Fixed" && child.score == 100) {
					hasChildTotalFixed = true;
				} else if (child.actions.indexOf("Partly Fixed") > 0 && child.score < 100) {
					hasChildPartFixed = true;
				}
			}
		}

		if (hasChildNeedFix == false && hasChildTotalFixed == true && hasChildPartFixed == false) {
			parent.score = 100;
			parent.actions = "Fixed";
		} else if (hasChildNeedFix == true && hasChildTotalFixed == true && hasChildPartFixed == false) {
			parent.score = parent.score + (100 - parent.score) / 2;
			parent.actions = "Partly Fixed." + parent.actions;
		} else if (hasChildNeedFix == true && hasChildPartFixed == true) {
			parent.score = parent.score + (100 - parent.score) / 4;
			parent.actions == "Partly Fixed" + parent.actions;
		} else if (hasChildNeedFix = false && hasChildTotalFixed == false && hasChildPartFixed == true) {
			parent.score = parent.score + (100 - parent.score) / 2;
		}
		updateParentScore(parent);
	}
}

*/

//LUN faulted fix process
function clickActionConfiguration() {
	$("#myLunModal").draggable();
	$("#myLunModal").modal({
		backdrop: false
	});
	$('#myLunModal').modal('show');
	return false;
}

function confirmOpenSR() {

	fUrl = "../fixComponent?serialNumber=" + serialNumber + "&component=Disk&tid=emc";

	$.ajax({
		type: "GET",
		url: fUrl
	});

	var recommendAction = $("#recommend-action"),
		fixProcess = $("#process"),
		startStep = $("#start-step"),
		startStepIcon = $("#start-step-icon"),
		fixingStep = $("#fixing-step"),
		fixingStepIcon = $("#fixing-step-icon"),
		finishStep = $("#finish-step"),
		finishStepIcon = $("#finish-step-icon");

	window.open("http://support.emc.com");
	setLoadIcon();

	setTimeout(function() {
		recommendAction.hide();
		fixProcess.show();
	}, 1000);

	setTimeout(function start() {
		startStep.css("font-weight", "bold");
		startStepIcon.attr("src", "loading-icon.gif");
		startStepIcon.css("height", "15px");
	}, 3000);

	setTimeout(function fixing() {
		//startStep.css("font-weight", "bold");
		startStep.css("font-weight", "normal");
		startStepIcon.attr("src", "complete-icon.png");
		startStepIcon.css("height", "15px");

		fixingStep.css("font-weight", "bold");
		fixingStepIcon.attr("src", "loading-icon.gif");
		fixingStepIcon.css("height", "15px");

	}, 6000);

	setTimeout(function fixed() {
		//startStep.css("font-weight", "bold");
		fixingStep.css("font-weight", "normal");
		fixingStepIcon.attr("src", "complete-icon.png");
		fixingStepIcon.css("height", "15px");

		finishStepIcon.attr("src", "complete-icon.png");
		finishStepIcon.css("height", "15px");

		completeFix();
		//completeLunFix();
		//window.location.reload(false);
	}, 8000);
}


/*function completeLunFix() {
	d3.select(clickedImage).attr("xlink:href", function(d) {
		return "check-icon.png";
	});
	if (clickedImage.id == "image0") {
		d3.select("#image1").attr("xlink:href", function(d) {
			return "check-icon.png";
		});
		d3.select("#image2").attr("xlink:href", function(d) {
			return "check-icon.png";
		});
		changeScoreJson(root);
	} else if (clickedImage.id == "image1") {
		d3.select("#image2").attr("xlink:href", function(d) {
			return "check-icon.png";
		});
		changeScoreJson(root.children[2]);
		updateParentScore(root.children[2]);
	} else {
		changeScoreJson(root.children[2].children[0]);
		updateParentScore(root.children[2].children[0]);
	}

	update(root);
}*/