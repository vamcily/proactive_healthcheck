$(function() {
    var progressbar = $( "#progressbar" ),
      progressLabel = $( ".progress-label" ),
      progressContent = $(".progress-content");
      bundleStatus = $("#bundleStatus");

    progressbar.progressbar({
      value: false,
      change: function() {
        progressLabel.text( progressbar.progressbar( "value" ) + "%" );
        if(progressbar.progressbar("value") <=30)
          progressContent.text("Fixing step 1");
        else if(progressbar.progressbar("value") <=60)
           progressContent.text("Fixing step 2");
         else
           progressContent.text("Fixing step 3");
      },
      complete: function() {
        progressLabel.text( "complete!" );
        progressContent.text("All steps complete!");
        bundleStatus.text("Fixed");
      }
    });
    function progress() {
      var val = progressbar.progressbar( "value" ) || 0;
 
      progressbar.progressbar( "value", val + 1 );
 
      if ( val < 99 ) {
        setTimeout( progress, 100 );
      }
    }
    setTimeout( progress, 3000 );
  });