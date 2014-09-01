if (phantom.args.length != 1) {
    console.log('Expected a target URL parameter.');
    phantom.exit(1);
}

var page = require('webpage').create();
var url = phantom.args[0];

page.onConsoleMessage = function (message) {
    console.log("Test console: " + message);
};

console.log("Loading URL: " + url);

page.open(url, function (status) {
    if (status != "success") {
        console.log('Failed to open ' + url);
        phantom.exit(1);
    }

    console.log("Running test.");

    var result = page.evaluate(function() {
        return slides2.test.run();
    });


    if (result != 0) {
        console.log("*** Test failed! ***");
        phantom.exit(1);
    }

    console.log("Test succeeded.");
    phantom.exit(0);
});
