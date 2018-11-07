require.config({
    baseUrl : '.',
    paths : {
        'interpreter.runStackMachineJson' : './runStackMachineJson',
		'interpreter.constants' : './constants',
        'interpreter.interpreter' : './interpreter',
        'interpreter.nativeInterface' : './nativeInterface',
        'interpreter.nativeWeDo' : './nativeWeDo',
        'interpreter.state' : './state',
        'interpreter.util' : './util'
    }
});

require([ 'require', interpreter.runStackMachineJson ], function(
        require, runStackMachineJson) {
	runStackMachineJson()
});

/**
 * Initializations
 */
function init() {
    COMM.setErrorFn(handleServerErrors);
    $.when(languageController.init()).then(function(language) {
        return webviewController.init(language);
    }).then(function(language, opt_data) {
        return guiStateController.init(language, opt_data);
    }).then(function() {
        return robotController.init();
    }).then(function() {
        return userController.init();
    }).then(function() {
        galleryListController.init();
        progListController.init();
        progDeleteController.init();
        confListController.init();
        confDeleteController.init();
        progShareController.init();
        logListController.init();
        
        programController.init();
        configurationController.init();
        progHelpController.init();
        progInfoController.init();
        progCodeController.init();
        progSimController.init();
        progRunController.init();
        menuController.init();
        tutorialController.init();
        multSimController.init();
        
        cookieDisclaimer.init();
        $(".cover").fadeOut(100, function() {
            if (!guiStateController.getStartWithoutPopup()) {
                if (guiStateController.noCookie()) {
                    $("#show-startup-message").modal("show");
                } else {
                    userModel.getStatusText(function(result) {
                        if (result.statustext[0] !== "" && result.statustext[1] !== "") {
                            $('#modal-statustext').modal("show");
                        }
                    });
                }
            }
        });
        
        $(".pace").fadeOut(500);
    });
}

/**
 * Handle server errors
 */
function handleServerErrors() {
    // TODO more?        
    guiStateController.setPing(false);
    $('#message').attr('lkey', Blockly.Msg.SERVER_NOT_AVAILABLE);
    $('#message').html(Blockly.Msg.SERVER_NOT_AVAILABLE);
    $('#show-message').modal({
        backdrop : 'static',
        keyboard : false
    })
    $('#show-message :button').hide();
    $('#show-message').on('hidden.bs.modal', function(e) {
        // $("#show-message").modal("show");
        guiStateController.setPing(true);
    });
    $("#show-message").modal("show");
}
