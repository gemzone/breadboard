//function getParameterByName(name, url) {
//    if (!url) {
//      url = window.location.href;
//    }
//    name = name.replace(/[\[\]]/g, "\\$&");
//    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
//        results = regex.exec(url);
//    if (!results) return null;
//    if (!results[2]) return '';
//    return decodeURIComponent(results[2].replace(/\+/g, " "));
//}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

//$(document).ready(function () {
//	var lang = getUrlParameter("lang");
//
//	jQuery.i18n.properties({
//		name: 'Messages',
//		path: 'static/i18n/',
//		mode: 'map',
//		language: (lang == undefined ? navigator.language : lang),
//		callback: function () {
//			translation();
//		}
//	});
//});
//
//function translation() {
//	// var regex = "\[(i:.*?)\]";
//	$("body span,th,label,button,select option").each(function (i, o) {
//		var val = $(o).text().trim();
//		if ($.i18n.map[val] != undefined) {
//			$(o).text($.i18n.prop(val));
//		}
//	});
//}
