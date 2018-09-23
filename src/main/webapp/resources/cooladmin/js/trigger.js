	$(document).ready(function() {
		getRequest();
	});
	
	$('input[type="checkbox"]').on('change', function() {
		$('input[type="checkbox"]').not(this).prop('checked', false);
	});
	
	function getDarkRandomColor() {
		var letters = '012345'.split('');
		var color = '#';
		color += letters[Math.round(Math.random() * 5)];
		letters = '0123456789ABCDEF'.split('');
		for (var i = 0; i < 5; i++) {
			color += letters[Math.round(Math.random() * 15)];
		}
	
		if (tinycolor(color).isLight() || tinycolor(color).getBrightness() < 70) {
			getDarkRandomColor();
		}
	
		return color;
	}
	
//	var hue = 'rgb(' + (Math.floor(Math.random() * 256)) + ','
//		+ (Math.floor(Math.random() * 256)) + ','
//		+ (Math.floor(Math.random() * 256)) + ')';
	
	function setCookie(cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays * 1000 * 60 * 60 * 24));
		var expires = "expires=" + d.toGMTString();
		window.document.cookie = cname + "=" + cvalue + "; " + expires;
	}
	
	function getCookie(cname) {
		var name = cname + "=";
		var cArr = window.document.cookie.split(';');
		
		for (var i = 0; i < cArr.length; i++) {
			var c = cArr[i].trim();
			
			if (c.indexOf(name) == 0)
				return c.substring(name.length, c.length);
		}
		
		return "";
	}
	
	function deleteCookie(cname) {
		var d = new Date();
		d.setTime(d.getTime() - (1000 * 60 * 60 * 24));
		var expires = "expires=" + d.toGMTString();
		window.document.cookie = cname + "=" + "; " + expires;
	
	}
	
	function getRequest() {
		try {
			
			var strRequest = document.getElementById('request_id').value;
			var inshedules = document.getElementById('inshedules').value;
			var select = document.getElementById("interview_id");
			var userSelect = document.getElementById("user_id");
			var strUser = document.getElementById("user_id").value;
			var interviewSelect = document.getElementById("interview_id");
			var strInterview = document.getElementById("interview_id").value;
			
			while (select.options.length > 0) {
				select.remove(0);
			}
			
			var interview_shedules = inshedules.replace(/, "InterviewShedules":/gi, ", ");
			var inshedules = interview_shedules.replace(/\[/gi, "{");
			var interview_shedules = inshedules.replace(/\]/gi, "}");
			var inshedules = interview_shedules.replace(/"InterviewShedules":/gi, "\"InterviewShedules\":[");
			var interview_shedules = inshedules.replace(/}}/gi, "}]}");
			var inshedules = interview_shedules.replace(/\n/g, "\\\\n").replace(/\r/g, "\\\\r").replace(/\t/g, "\\\\t");
			//		console.log(inshedules);
	
			var myshedule = JSON.parse(inshedules);
			//		console.log(myshedule);
	
			var j = 0;
			var userValue = [];
			var myshelen = Object.keys(myshedule.InterviewShedules).length;
			for (var i = 0; i < myshelen; i++) {
				if (myshedule.InterviewShedules[i].request_id == strRequest) {
					userValue[j++] = myshedule.InterviewShedules[i].user_id;					
					try {
						select.add(new Option(myshedule.InterviewShedules[i].Name, myshedule.InterviewShedules[i].sheid)); 
					} catch (e) {
						select.appendChild(new Option(myshedule.InterviewShedules[i].Name, myshedule.InterviewShedules[i].sheid));
					}
				}
			}
			
//			for(var i = (userSelect.options.length - 1); i > 0; i--){
//				var bFlag = false;
//				for (var j = 0; j < userValue.length; j++)
//				{
//					if (userValue[j] == userSelect.options[i].value)
//					{
//						bFlag = true;
//					}
//					if (!bFlag)
//					{
//						userSelect.remove(i);
//					}
//				}
//			}
			
			var UserName = [];
			for(var i = 0; i < userSelect.options.length; i++){
				UserName[i] = userSelect.options[i].innerHTML;
			}
	
			
			while (userSelect.options.length > 0) {
				userSelect.remove(0);
			}
			
			for (var i = 0; i < userValue.length; i++) {
				
				try {
					userSelect.add(new Option(UserName[parseInt(userValue[i]) - 1], userValue[i])); 
				} catch (e) {
					userSelect.appendChild(new Option(UserName[parseInt(userValue[i]) - 1], userValue[i]));
				}
			}
			
			for(var i = 0; i < interviewSelect.options.length; i++){
				if (strInterview.localeCompare(interviewSelect.options[i].value) == 0)
				{
					interviewSelect.selectedIndex = i;
				}
			}
			
			for(var i = 0; i < userSelect.options.length; i++){
				if (strUser.localeCompare(userSelect.options[i].value) == 0)
				{
					userSelect.selectedIndex = i;
				}
			}
	
		} catch (error) {
			console.log(error);
		}
	}
	
//	function getRequest() {
//		try {
//			
//			var strRequest = document.getElementById('request_id').value;
//			var inshedules = document.getElementById('inshedules').value;
//			var select = document.getElementById("interview_id");
//			var userSelect = document.getElementById("user_id");
//			
//			while (select.options.length > 0) {
//				select.remove(0);
//			}
//			
//			var interview_shedules = inshedules.replace(/, "InterviewShedules":/gi, ", ");
//			var inshedules = interview_shedules.replace(/\[/gi, "{");
//			var interview_shedules = inshedules.replace(/\]/gi, "}");
//			var inshedules = interview_shedules.replace(/"InterviewShedules":/gi, "\"InterviewShedules\":[");
//			var interview_shedules = inshedules.replace(/}}/gi, "}]}");
//			var inshedules = interview_shedules.replace(/\n/g, "\\\\n").replace(/\r/g, "\\\\r").replace(/\t/g, "\\\\t");
//			//		console.log(inshedules);
//	
//			var myshedule = JSON.parse(inshedules);
//			//		console.log(myshedule);
//	
////			var j = 0;
////			var userValue = [];
//			var myshelen = Object.keys(myshedule.InterviewShedules).length;
//			for (var i = 0; i < myshelen; i++) {
//				if (myshedule.InterviewShedules[i].request_id == strRequest) {
////					userValue[j++] = myshedule.InterviewShedules[i].user_id;
////					 && myshedule.InterviewShedules[i].Result_Status_Id != 2					
//					try {
//						select.add(new Option(myshedule.InterviewShedules[i].Name, myshedule.InterviewShedules[i].sheid)); // this will fail in DOM browsers but is needed for IE 
//					} catch (e) {
//						select.appendChild(new Option(myshedule.InterviewShedules[i].Name, myshedule.InterviewShedules[i].sheid));
//					}
//				}
//			}
//			
////			for(var i = (userSelect.options.length - 1); i > 0; i--){
////				var bFlag = false;
////				for (var j = 0; j < userValue.length; j++)
////				{
////					if (userValue[j] == userSelect.options[i].value)
////					{
////						bFlag = true;
////					}
////					if (!bFlag)
////					{
////						userSelect.remove(i);
////					}
////				}
////			}
//	
//		} catch (error) {
//			console.log(error);
//		}
//	}
	
	