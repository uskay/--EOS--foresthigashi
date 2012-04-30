//Dojoライブラリのインポート
dojo.require("dojox.widget.Dialog");
dojo.require("dijit.MenuBar");
dojo.require("dijit.MenuBarItem");
dojo.require("dijit.PopupMenuBarItem");
dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");;
dojo.require("dojo.parser");
dojo.require("dijit.form.Form");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.NumberTextBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.Select");
dojo.require("dijit.form.SimpleTextarea");
dojo.require("dijit.ProgressBar");
dojo.require("dojox.fx");

//メニューバーの作成
var pMenuBar;
dojo.addOnLoad(function() {
    pMenuBar = new dijit.MenuBar({
        id: "menu",
        layoutAlign: "right"
    });
    pMenuBar.addChild(new dijit.MenuBarItem({
        label: "<font color='#4A4A4A'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;トップページ&nbsp;&nbsp;&nbsp;&nbsp;</b></font>",
        onClick: fClickTopPage
    }));
    pMenuBar.addChild(new dijit.MenuBarItem({
    	id: "menuBarItem2",
        label: "<font color='#4A4A4A'><b>&nbsp;&nbsp;&nbsp;&nbsp;「フォレストひがし」について&nbsp;&nbsp;&nbsp;&nbsp;</b></font>",
        onClick: fClickAbout,
    }));
    pMenuBar.addChild(new dijit.MenuBarItem({
        label: "<b>&nbsp;&nbsp;&nbsp;&nbsp;ブログ「やんばる日記」&nbsp;&nbsp;&nbsp;&nbsp;</b>",
        disabled: true,
        onClick: fClickBlog
    }));
    var pSubMenuLink = new dijit.Menu({});
    pSubMenuLink.addChild(new dijit.MenuItem({
        label: "沖縄県国頭郡東村役場公式ホームページ",
        onClick: fClickItem1,
    }));
    pSubMenuLink.addChild(new dijit.MenuItem({
        label: "［ランチ＆カフェ・宿泊］カナンファーム",
        onClick: fClickItem2
    }));
    pSubMenuLink.addChild(new dijit.MenuItem({
        label: "やんばるロハス",
        onClick: fClickItem3
    }));
    pMenuBar.addChild(new dijit.PopupMenuBarItem({
        label: "<font color='#4A4A4A'><b>&nbsp;&nbsp;&nbsp;&nbsp;リンク&nbsp;&nbsp;&nbsp;&nbsp;</b></font>",
        popup: pSubMenuLink
    }));
    pMenuBar.placeAt("wrapper");
    pMenuBar.startup();
});
function fClickTopPage() {
	location.reload();
};
function fClickAbout() {	
	dijit.byId('about').show();	
};
function fClickBlog() {
    location.href = "blog.html";
};
function fClickItem1() {
	window.open("http://www.vill.higashi.okinawa.jp");
};
function fClickItem2() {
	window.open("http://www.canaan-farm.com");
};
function fClickItem3() {
    window.open("http://yanbaru-lohas.com");
};

//お申し込み内容確認Request
function xhr4ReservationConfirmServlet() {
	
	var nameKana = dijit.byId("NAME_KANA").get("value");
	var nameKanji = dijit.byId("NAME_KANJI").get("value");
	var adultNumber = dijit.byId("ADULT_NUMBER").get("value");
	var childNumber = dijit.byId("CHILD_NUMBER").get("value");
	var plan = dijit.byId("PLAN").get("value");
	var date = dijit.byId("DATE").get("displayedValue");
	var time = dijit.byId("TIME").get("displayedValue");
	var tel = dijit.byId("TEL").get("value");
	var mailaddress = dijit.byId("MAILADDRESS").get("value");
	var mailaddress2 = dijit.byId("MAILADDRESS2").get("value");
	var comment = dijit.byId("COMMNET").get("value");
	
	var postData =
		"NAME_KANA="+nameKana+"&"+
		"NAME_KANJI="+nameKanji+"&"+
		"ADULT_NUMBER="+adultNumber+"&"+
		"CHILD_NUMBER="+childNumber+"&"+
		"PLAN="+plan+"&"+
		"DATE="+date+"&"+
		"TIME="+time+"&"+
		"TEL="+tel+"&"+
		"MAILADDRESS="+mailaddress+"&"+
		"MAILADDRESS2="+mailaddress2+"&"+
		"COMMNET="+comment+"&";			
	
	var config = {
		url: "ReservationConfirmServlet",
		postData: postData,
		load: handleLoad4ReservationConfirmServlet,
		error: handleError4ReservationConfirmServlet,
		handleAs: "json"
	};
	dojo.xhrPost(config);	
}

//お申し込み内容確認Requestの応答処理（正常時）
function handleLoad4ReservationConfirmServlet(response, ioArgs) {
	if(ioArgs.xhr.status == "200"){
		if(response.RESERVATION_CHECK_RESULT==""){
			dojo.byId("TITLE_STEP1").style.backgroundColor = "#D3D3D3";
			dojo.byId("ERROR_MESSAGE").innerHTML = "";
			dojo.byId("TITLE_STEP2").innerHTML = "２．お申し込み内容は上記でよろしいでしょうか。";
			dojo.byId("TITLE_STEP2").style.backgroundColor = "#165829";
			dojo.byId("TOTAL_AMOUNT").innerHTML = "合計金額";
    		dojo.byId("TOTAL_AMOUNT").style.backgroundColor = "#D3D3D3";
    		dojo.byId("TOTAL_AMOUNT_VALUE").innerHTML = response.TOTAL_AMOUNT;
    		dojo.byId("TOTAL_AMOUNT_VALUE").style.backgroundColor = "#F1F1F1";
			validObject_Step1(true);
			validObject_Step2(true, false);
		} else {
			dojo.byId("ERROR_MESSAGE").innerHTML = response.RESERVATION_CHECK_RESULT;
		}
	} else {
		dojo.byId("ERROR_MESSAGE").innerHTML = "Error occured! お電話にてお問い合わせください";
	}
}

//お申し込み内容確認Requestの応答処理（エラー時）
function handleError4ReservationConfirmServlet(response, ioArgs) {
	dojo.byId("ERROR_MESSAGE").innerHTML = "Error occured! HttpStatusCode = " +  ioArgs.xhr.status;
}	

//お申し込み実施Request
function xhr4ReservationServlet() {
	
	dojo.style(dijit.byId("PROGRESS_BAR").domNode, {visibility:"visible"});		
	validObject_Step2(true, true);
	
	var nameKana = dijit.byId("NAME_KANA").get("value");
	var nameKanji = dijit.byId("NAME_KANJI").get("value");
	var adultNumber = dijit.byId("ADULT_NUMBER").get("value");
	var childNumber = dijit.byId("CHILD_NUMBER").get("value");
	var plan = dijit.byId("PLAN").get("value");
	var date = dijit.byId("DATE").get("displayedValue");
	var time = dijit.byId("TIME").get("displayedValue");
	var tel = dijit.byId("TEL").get("value");
	var mailaddress = dijit.byId("MAILADDRESS").get("value");
	var mailaddress2 = dijit.byId("MAILADDRESS2").get("value");
	var comment = dijit.byId("COMMNET").get("value");
	
	var postData =
		"NAME_KANA="+nameKana+"&"+
		"NAME_KANJI="+nameKanji+"&"+
		"ADULT_NUMBER="+adultNumber+"&"+
		"CHILD_NUMBER="+childNumber+"&"+
		"PLAN="+plan+"&"+
		"DATE="+date+"&"+
		"TIME="+time+"&"+
		"TEL="+tel+"&"+
		"MAILADDRESS="+mailaddress+"&"+
		"MAILADDRESS2="+mailaddress2+"&"+
		"COMMNET="+comment+"&";			
	
	var config = {
		url: "ReservationServlet",
		postData: postData,
		load: handleLoad4ReservationServlet,
		error: handleError4ReservationServlet,
		handleAs: "json"
	};
	dojo.xhrPost(config);	
}

//お申し込み実施Requestの応答処理（正常時）
function handleLoad4ReservationServlet(response, ioArgs) {
	dojo.style(dijit.byId("PROGRESS_BAR").domNode, {visibility:"hidden"});
	if(ioArgs.xhr.status == "200"){
		if(response.RESERVATION_RESULT==""){
			dojo.byId("TITLE_STEP2").style.backgroundColor = "#D3D3D3";
			dojo.byId("ERROR_MESSAGE").innerHTML = "";
			dojo.byId("TITLE_STEP3").innerHTML = "３．お申し込み承りました。";
			dojo.byId("TITLE_STEP3").style.backgroundColor = "#165829";
			dojo.byId("END_MESSAGE").innerHTML = "お申し込み情報を、お客さまのメールアドレス宛て<br>にお送りしましたのでご確認くださいませ";
			validObject_Step2(true, true);
			validObject_Step3(true, false);	
		} else{
			dijit.byId("BUTTON_UNDO").set("disabled",false);
			dojo.byId("ERROR_MESSAGE").innerHTML = response.RESERVATION_RESULT;
		}			
	} else {
		dijit.byId("BUTTON_UNDO").set("disabled",false);
		dojo.byId("ERROR_MESSAGE").innerHTML = "Error occured! お電話にてお問い合わせください";
	}
}

//お申し込み実施Requestの応答処理（エラー時）
function handleError4ReservationServlet(response, ioArgs) {
	dojo.style(dijit.byId("PROGRESS_BAR").domNode, {visibility:"hidden"});
	dojo.byId("ERROR_MESSAGE").innerHTML = "Error occured! HttpStatusCode = " +  ioArgs.xhr.status;
	validObject_Step2(true, false);
}

//「訂正する」ボタン押下時
function handleButtonUndo(){
	dojo.byId("TITLE_STEP1").style.backgroundColor = "#165829";
	dojo.byId("TITLE_STEP2").innerHTML = "";
	dojo.byId("TITLE_STEP2").style.backgroundColor = "#F1F1F1";
	dojo.byId("TOTAL_AMOUNT").innerHTML = "";
	dojo.byId("TOTAL_AMOUNT").style.backgroundColor = "#F1F1F1";
	dojo.byId("TOTAL_AMOUNT_VALUE").innerHTML = "";
	dojo.byId("TOTAL_AMOUNT_VALUE").style.backgroundColor = "#F1F1F1";
	dojo.byId("ERROR_MESSAGE").innerHTML = "";
	validObject_Step1(false);
	validObject_Step2(false, true);
}

//「了解」ボタン押下時
function handleButtonEnd(){
	initObject();
}

//画面項目の初期化
function initObject(){
	dojo.byId("TITLE_STEP1").style.backgroundColor = "#165829";
	dojo.byId("ERROR_MESSAGE").innerHTML = "";
	dojo.byId("TITLE_STEP2").innerHTML = "";
	dojo.byId("TITLE_STEP2").style.backgroundColor = "#F1F1F1";
	dojo.byId("TOTAL_AMOUNT").innerHTML = "";
	dojo.byId("TOTAL_AMOUNT").style.backgroundColor = "#D3D3D3";
	dojo.byId("TOTAL_AMOUNT_VALUE").innerHTML = "";
	dojo.byId("TOTAL_AMOUNT_VALUE").style.backgroundColor = "#F1F1F1";
	dojo.byId("ERROR_MESSAGE").innerHTML = "";
	dojo.byId("TITLE_STEP3").innerHTML = "";
	dojo.byId("TITLE_STEP3").style.backgroundColor = "#F1F1F1";
	dojo.byId("END_MESSAGE").innerHTML = "";	
	dijit.byId("NAME_KANA").set("value","");
	dijit.byId("NAME_KANJI").set("value","");
	dijit.byId("ADULT_NUMBER").set("value","大人０名");
	dijit.byId("CHILD_NUMBER").set("value","こども０名");
	dijit.byId("PLAN").set("value","慶佐次川マングローブカヌー体験");
	dijit.byId("DATE").set("value"," ");
	dijit.byId("TIME").set("value","指定なし");
	dijit.byId("TEL").set("value","");
	dijit.byId("MAILADDRESS").set("value","");
	dijit.byId("MAILADDRESS2").set("value","");
	dijit.byId("COMMNET").set("value","その他ご要望はこちらに入力して下さい");
	validObject_Step1(false);
	validObject_Step2(false, true);
	validObject_Step3(false, true);
}

//画面項目の操作（Step1）
function validObject_Step1(disable){
	dijit.byId("NAME_KANA").set("disabled",disable);
	dijit.byId("NAME_KANJI").set("disabled",disable);
	dijit.byId("ADULT_NUMBER").set("disabled",disable);
	dijit.byId("CHILD_NUMBER").set("disabled",disable);
	dijit.byId("PLAN").set("disabled",disable);
	dijit.byId("DATE").set("disabled",disable);
	dijit.byId("TIME").set("disabled",disable);
	dijit.byId("TEL").set("disabled",disable);
	dijit.byId("MAILADDRESS").set("disabled",disable);
	dijit.byId("MAILADDRESS2").set("disabled",disable);
	dijit.byId("COMMNET").set("disabled",disable);
	dijit.byId("BUTTON_CHECK").set("disabled",disable);
}

//画面項目の操作（Step2）
function validObject_Step2(visible, disable){
	if(visible){
		dojo.style(dijit.byId("BUTTON_CONFIRM").domNode, {visibility:"visible"});
		dojo.style(dijit.byId("BUTTON_UNDO").domNode, {visibility:"visible"});	
	} else {
		dojo.style(dijit.byId("BUTTON_CONFIRM").domNode, {visibility:"hidden"});
		dojo.style(dijit.byId("BUTTON_UNDO").domNode, {visibility:"hidden"});
	}
	dijit.byId("BUTTON_CONFIRM").set("disabled",disable);
	dijit.byId("BUTTON_UNDO").set("disabled",disable);
}

//画面項目の操作（Step3）
function validObject_Step3(visible, disable){
	if(visible){
		dojo.style(dijit.byId("BUTTON_END").domNode, {visibility:"visible"});	
	} else {
		dojo.style(dijit.byId("BUTTON_END").domNode, {visibility:"hidden"});
	}
	dijit.byId("BUTTON_END").set("disabled", disable);
}

//画面項目の操作（申込プラン押下時のご希望時間帯の制御）
function checkTime(){
	var plan = dijit.byId("PLAN").get("value");
	if(plan==1){
		dijit.byId("TIME").set("value","指定なし");
		dijit.byId("TIME").set("disabled",true);
	} else {
		dijit.byId("TIME").set("disabled",false);
	}
}

//イメージオブジェクトonmouseout時
function imgOnMouseOut(objID){
	dojo.style(objID, "opacity", "1.0");	
}

//イメージオブジェクトonmouseover時
function imgOnMouseOver(objID){
	dojo.style(objID, "opacity", "0.7");	
}

//onLoad
dojo.addOnLoad(function() {
	validObject_Step1(false);
	validObject_Step2(false, true);
	validObject_Step3(false, true);
	dojo.style(dijit.byId("PROGRESS_BAR").domNode, {visibility:"hidden"});
});