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

function encodeText(str) {
    if (str == undefined || str == null) {
        return "";
    } else {
        str = str.replace(/%/gi, "%25");
        str = str.replace(/\+/g, "%2B");
        str = encodeURIComponent(str);
        return str;
    }
};

function decodeText(str) {
	str = str.replace(/%/gi, "%25");
	var map = {"gt":">" /* , … */};
    str = str.replace(/&(#(?:x[0-9a-f]+|\d+)|[a-z]+);?/gi, function($0, $1) {
        if ($1[0] === "#") {
            return String.fromCharCode($1[1].toLowerCase() === "x" ? parseInt($1.substr(2), 16)  : parseInt($1.substr(1), 10));
        } else {
            return map.hasOwnProperty($1) ? map[$1] : $0;
        }
    });
    str = decodeURIComponent(str);
    return str;
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
