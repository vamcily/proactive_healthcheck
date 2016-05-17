function confirmUpgrade() {
  var progressbar = $("#progressbar"),
    progressLabel = $(".progress-label"),
    progressContent = $(".progress-content"),
    stepTitle = $("#stepTitle"),
    installSteps = $("#upgrade-steps"),
    step1 = $("#step1"),
    step2 = $("#step2"),
    step3 = $("#step3"),
    step4 = $("#step4"),
    step5 = $("#step5"),
    step6 = $("#step6"),
    myModal = $("#myModal"),
    modalBody = $("#upgrade-modal-body");

     fUrl = "../fixComponent?serialNumber=" + serialNumber + "&component=Software&tid=emc";
    
    $.ajax({
      type: "GET",
      url: fUrl
    });

  $(".confirmBtn").attr("disabled", true);
  var btn = $(".confirmBtn");
  btn.attr("disabled", true);
  btn.attr("style", "background:#CCCCCC");

  myModal.css("height","281px");
  modalBody.css("height","165px");
  progressbar.show();
  stepTitle.text("Current Step:");
  installSteps.show();

  var imgHtml = "<img src=\"complete-icon.png\"  style=\"height:15px\"/>";

  progressbar.progressbar({
    value: false,
    change: function() {
      progressLabel.text(progressbar.progressbar("value") + "%");
      /*if (progressbar.progressbar("value") < 30)
        progressContent.text("Fixing step 1");
      else if(progressbar.progressbar("value") == 30) {
        progressContent.append("<img src=\"complete-icon.png\"  style=\"height:15px\"/>");
      } else if (progressbar.progressbar("value") <= 60){
        progressContent.text("Fixing step 2");
        //progressContent.append("<img src=\"complete-icon.png\"  style=\"height:15px\"/>");
      }else
        progressContent.text("Fixing step 3");
        //progressContent.append("<img src=\"complete-icon.png\"  style=\"height:15px\"/>");*/
      step1.css("color", "#000000");
      if (progressbar.progressbar("value") <10) {
        step1.css("font-weight", "bold");
      } else if (progressbar.progressbar("value") == 10){
        step1.append(imgHtml);
      } else if (progressbar.progressbar("value") < 35) {
        step1.css("font-weight", "normal");
        step2.css("color", "#000000");
        step2.css("font-weight", "bold");
      } else if (progressbar.progressbar("value") == 35) {
        step2.append(imgHtml);
      }else if (progressbar.progressbar("value") < 50) {
        step2.css("font-weight", "normal");
        step3.css("color", "#000000");
        step3.css("font-weight", "bold");
      } else if (progressbar.progressbar("value") == 50) {
        step3.append(imgHtml);  
      } else if (progressbar.progressbar("value") < 75) {
        step3.css("font-weight", "normal");
        step4.css("color", "#000000");
        step4.css("font-weight", "bold");
      } else if (progressbar.progressbar("value") == 75) {
        step4.append(imgHtml);
      }
      else if (progressbar.progressbar("value") < 100) {
        step4.css("font-weight", "normal");  
        step5.css("color", "#000000");
        step5.css("font-weight", "bold");
      } /*else  if (progressbar.progressbar("value") == 100) {

      }*/
    },
    complete: function() {
      progressLabel.text("complete!");
      //progressContent.text("All steps complete!");
      step5.css("font-weight", "normal");
      step5.append(imgHtml);
      step6.show();
      //progressContent.append("<img src=\"complete-icon.png\"  style=\"height:15px\"/>");
      completeFix();
      //window.location.reload(false); 
    }
  });

  function progress() {
    var val = progressbar.progressbar("value") || 0;
    progressbar.progressbar("value", val + 1);

    if (val < 99) {
      setTimeout(progress, 100);
    }
  }
  setLoadIcon();
  setTimeout(progress, 3000);
}