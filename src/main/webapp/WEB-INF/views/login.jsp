<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">

<head>



<c:url value="/resources/css/font-face.css" var="fontfacecsscss" />

<c:url value="/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" var="fontawesomemincss" />

<c:url value="/resources/vendor/font-awesome-5/css/fontawesome-all.min.css" var="fontawesomeallmincss" />

<c:url value="/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" var="materialdesigniconicfontmincss" />

<c:url value="/resources/vendor/bootstrap-4.1/bootstrap.min.css" var="bootstrapmincss" />

<c:url value="/resources/vendor/animsition/animsition.min.css" var="animsitionmincss" />

<c:url value="/resources/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" var="bootstrapprogressbar33css" />

<c:url value="/resources/vendor/wow/animate.css" var="animatecsscss" />

<c:url value="/resources/vendor/css-hamburgers/hamburgers.min.css" var="hamburgersmincss" />

<c:url value="/resources/vendor/slick/slick.css" var="slickcsscss" />

<c:url value="/resources/vendor/select2/select2.min.css" var="select2mincss" />

<c:url value="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css" var="perfectscrollbarcsscss" />

<c:url value="/resources/css/theme.css" var="themecsscss" />


<c:url value="/resources/vendor/jquery-3.2.1.min.js" var="jquery32js" />

<c:url value="/resources/vendor/bootstrap-4.1/popper.min.js" var="popperminjs" />

<c:url value="/resources/vendor/bootstrap-4.1/bootstrap.min.js" var="bootstrapminjs" />

<c:url value="/resources/vendor/slick/slick.min.js" var="slickminjs" />

<c:url value="/resources/vendor/wow/wow.min.js" var="wowminjs" />

<c:url value="/resources/vendor/animsition/animsition.min.js" var="animsitionminjs" />

<c:url value="/resources/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js" var="bootstrapprogressbarminjs" />

<c:url value="/resources/vendor/counter-up/jquery.waypoints.min.js" var="jquerywaypointsjs" />

<c:url value="/resources/vendor/counter-up/jquery.counterup.min.js" var="jquerycounterupjs" />

<c:url value="/resources/vendor/circle-progress/circle-progress.min.js" var="circleprogressminjs" />

<c:url value="/resources/vendor/perfect-scrollbar/perfect-scrollbar.js" var="perfectscrollbarjsjs" />

<c:url value="/resources/vendor/chartjs/Chart.bundle.min.js" var="Chartbundlejs" />

<c:url value="/resources/vendor/select2/select2.min.js" var="select2minjs" />

<c:url value="/resources/js/main.js" var="mainjsjs" />


<c:url value="/resources/images/icon/logo.png" var="logopngpng" />

    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Login</title>

    <!-- Fontfaces CSS-->
    <link href="${fontfacecsscss}" rel="stylesheet" media="all">
    <link href="${fontawesomemincss}" rel="stylesheet" media="all">
    <link href="${fontawesomeallmincss}" rel="stylesheet" media="all">
    <link href="${materialdesigniconicfontmincss}" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="${bootstrapmincss}" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="${animsitionmincss}" rel="stylesheet" media="all">
    <link href="${bootstrapprogressbar33css}" rel="stylesheet" media="all">
    <link href="${animatecsscss}" rel="stylesheet" media="all">
    <link href="${hamburgersmincss}" rel="stylesheet" media="all">
    <link href="${slickcsscss}" rel="stylesheet" media="all">
    <link href="${select2mincss}" rel="stylesheet" media="all">
    <link href="${perfectscrollbarcsscss}" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="${themecsscss}" rel="stylesheet" media="all">

<script type="text/javascript">

function notify(message){
    alert(message);
}

function notifyRe(message){
    alert(message);
    var url = "${pageContext.request.contextPath}";
    window.location.href = url + "/";
}

</script>

<%
String failed = (String)session.getAttribute("failed");
String message = (String)session.getAttribute("message");
if (failed != null)
{
	%>
    <script>
    notify('<%= message %>');
    </script>
	<%
}

String success = (String)session.getAttribute("success");
if (success != null)
{
	%>
    <script>
    notifyRe('<%= message %>');
    </script>
	<%
}
%>

</head>

<body class="animsition">
    <div class="page-wrapper">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="login-logo">
                            <a href="#">
                                <img src="${logopngpng}" alt="BATUserManagement">
                            </a>
                        </div>
                        <div class="login-form">
                            <form action="userlogin" method="post">
                                <div class="form-group">
                                    <label style="color: #00ad5f;">Username</label>
                                    <input style="color: #4272d7; height: 50px;" class="au-input au-input--full" type="text" name="username" placeholder="Username" value="${username}" style="height: 50px;">
                                </div>
                                <div class="form-group">
                                    <label style="color: #00ad5f;">Password</label>
                                    <input style="color: #4272d7;height: 50px;" class="au-input au-input--full" type="password" name="password" placeholder="Password"  value="${password}" style="height: 50px;">
                                </div>
                                <div class="login-checkbox">                                 
                                        <div>
                                            <input type="checkbox" class="fancy-check chkbox" name="remember" id="remember" value="1"/>
                                            <label for="remember" style="color: #00ad5f;"><span>Remember Me</span>
                                            </label>
                                        </div>
                                   
                                </div>
                                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">sign in</button>
                       			
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Jquery JS-->
    <script src="${jquery32js}"></script>
    <!-- Bootstrap JS-->
    <script src="${popperminjs}"></script>
    <script src="${bootstrapminjs}"></script>
    <!-- Vendor JS       -->
    <script src="${slickminjs}">
    </script>
    <script src="${wowminjs}"></script>
    <script src="${animsitionminjs}"></script>
    <script src="${bootstrapprogressbarminjs}">
    </script>
    <script src="${jquerywaypointsjs}"></script>
    <script src="${jquerycounterupjs}">
    </script>
    <script src="${circleprogressminjs}"></script>
    <script src="${perfectscrollbarjsjs}"></script>
    <script src="${Chartbundlejs}"></script>
    <script src="${select2minjs}">
    </script>

    <!-- Main JS-->
    <script src="${mainjsjs}"></script>

<script type="text/javascript">

document.onkeydown=function(evt){
	var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
    if(keyCode == 13)
    {
    	var source = event.target || event.srcElement;
        console.log(source.type);
        
        if (source.type != "textarea")
        {
        	document.getElementsByClassName("au-btn")[0].click();   
        }
    }
}

</script>

</body>

</html>
<!-- end document-->