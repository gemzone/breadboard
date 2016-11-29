$(document).ready( function() {
    spf.init();
});

$(document).on("spfclick", function() {
  // Show progress bar
  NProgress.start();
});

$(document).on("spfrequest", function() {
  // Increment progress as request is made
  NProgress.inc();
});

$(document).on("spfprocess", function() {
  // Set progress bar width to 100%
  NProgress.done();
});

$(document).on("spfdone", function() {
  // Finish request and remove progress bar
  NProgress.remove();
});