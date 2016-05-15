function confirmUpgrade() {
  var progressbar = $("#progressbar"),
    progressLabel = $(".progress-label"),
    progressContent = $(".progress-content"),
    stepTitle = $("#stepTitle"),
    fUrl = "../fixComponent?serialNumber=" + serialNumber + "&component=Software&tid=emc";
  
  $.ajax({
    type: "GET",
    url: fUrl
  });
  
  bundleStatus = $("#bundleStatus");
  
  $(".confirmBtn").attr("disabled",true);
  var btn = $(".confirmBtn");
  btn.attr("disabled",true);
  btn.attr("style","background:#CCCCCC");

  progressbar.show();
  stepTitle.text("Current Step:");

  progressbar.progressbar({
    value: false,
    change: function() {
      progressLabel.text(progressbar.progressbar("value") + "%");
      if (progressbar.progressbar("value") <= 30)
        progressContent.text("Fixing step 1");
      else if (progressbar.progressbar("value") <= 60)
        progressContent.text("Fixing step 2");
      else
        progressContent.text("Fixing step 3");
    },
    complete: function() {
      progressLabel.text("complete!");
      progressContent.text("All steps complete!");
      bundleStatus.text("Fixed");
      completeFix();
      window.location.reload(false); 
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


