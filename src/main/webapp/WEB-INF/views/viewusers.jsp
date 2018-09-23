<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.user.management.model.Qualification" %>
<%@ page import="com.user.management.model.UserRole" %>
<%@ page import="com.user.management.model.User" %>
<%@ page import="java.util.ArrayList" %>
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

<c:url value="/resources/js/trigger.js" var="triggerjsjs" />


<c:url value="/resources/images/icon/logo.png" var="logopngpng" />

<c:url value="/resources/images/icon/avatar-06.jpg" var="avatar06jpgjpg" />

<c:url value="/resources/images/icon/avatar-04.jpg" var="avatar04jpgjpg" />

<c:url value="/resources/images/icon/avatar-05.jpg" var="avatar05jpgjpg" />

<c:url value="/resources/images/icon/avatar-01.jpg" var="avatar01jpgjpg" />

<c:url value="/resources/images/icon/avatar-10.jpg" var="avatar10jpgjpg" />

    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>View Users</title>

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

    <% //notifi-dropdown
        pageContext.setAttribute("message", "");
        pageContext.setAttribute("noticount", 0);
        
        String failed = (String)session.getAttribute("failed");
        String message = (String)session.getAttribute("message");
        
        if (failed != null)
        {
            pageContext.setAttribute("noticount", 1);
            pageContext.setAttribute("message", message);
        }
        
        String success = (String)session.getAttribute("success");
        if (success != null)
        {
            pageContext.setAttribute("noticount", 1);
            pageContext.setAttribute("message", message);
        }
    %>


</head>

<body class="animsition">
    <div class="page-wrapper">
         <!-- HEADER MOBILE-->
        <header class="header-mobile d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="<c:url value='/index' />">
                            <img src="${logopngpng}" alt="BATUserManagement" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="container-fluid">
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="has-sub">
                           
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- END HEADER MOBILE-->

        <!-- MENU SIDEBAR-->
        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="#">
                    <img src="${logopngpng}" alt="Cool Admin" />
                </a>
            </div>
	        <div class="menu-sidebar__content js-scrollbar1">
	                <nav class="navbar-sidebar">
	                    <ul class="list-unstyled navbar__list">
	                        <li>
	                            <a href="<c:url value='/index' />">
	                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
	                        </li>
	                        <li class="active">
	                            <a href="<c:url value='/viewusers' />">
	                                <i class="fas fa-chart-bar"></i>Users</a>
	                        </li>
	                        <li>
	                            <a href="<c:url value='/viewrequests' />">
	                                <i class="fas fa-table"></i>Recruitment Requests</a>
	                        </li>
	                        <li>
	                            <a href="<c:url value='/viewInterviewShedule' />">
	                                <i class="far fa-check-square"></i>Interview Shedules</a>
	                        </li>
	                        <li>
	                            <a href="<c:url value='/viewSelectedCandidates' />">
	                                <i class="fas fa-calendar-alt"></i>Selected Candidates</a>
	                        </li>
	                    </ul>
	                </nav>
	            </div>
        </aside>
        <!-- END MENU SIDEBAR-->

        <!-- PAGE CONTAINER-->
        <div class="page-container">
            <!-- HEADER DESKTOP-->
            <header class="header-desktop">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="header-wrap">
                            <form class="form-header" action="" method="POST">
                              
                            </form>
                            <div class="header-button">
                              <div class="noti-wrap">
                                 <div class="noti__item js-item-menu">
                                        <i class="zmdi zmdi-notifications"></i>
                                        <span class="quantity">${noticount}</span>
                                        <div class="notifi-dropdown js-dropdown">
                                            <div class="notifi__title">
                                                <p>You have ${noticount} Notifications</p>
                                            </div>
                                            
                                            <div class="notifi__item">
                                                <div class="bg-c3 img-cir img-40">
                                                    <i class="zmdi zmdi-notifications"></i>
                                                </div>
                                                <div class="content">
                                                    <p>${message}</p>
                                                    <span class="date"></span>
                                                </div>
                                            </div>
                                            <div class="notifi__footer">
                                                <a href="#"></a>
                                            </div>
                                        </div>
                                    </div>
                               </div>
                               <div class="account-wrap">
                                    <div class="account-item clearfix js-item-menu">
                                        <div class="image">
                                            <img src="${avatar10jpgjpg}" alt="${curruser.getName()}" />
                                        </div>
                                        <div class="content">
                                            <a class="js-acc-btn" style="color: #4272d7;" href="#">${curruser.getName()}(${userrole})</a>
                                        </div>
                                        <div class="account-dropdown js-dropdown">
                                            <div class="info clearfix">
                                                <div class="image">
                                                    <a href="#">
                                                        <img src="${avatar10jpgjpg}" alt="${curruser.getName()}" />
                                                    </a>
                                                </div>
                                                <div class="content">
                                                    <h5 class="name">
                                                        <a style="color: #4272d7;" href="#">${curruser.getName()}(${userrole})</a>
                                                    </h5>
                                                    <span style="color: #00ad5f;" class="email">${curruser.getEmailID()}</span>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__body">
                                                <div class="account-dropdown__item">
                                                    <a href="<c:url value='/viewusers' />">
                                                        <i class="zmdi zmdi-account"></i>Account</a>
                                                </div>
                                                <div class="account-dropdown__item">
                                                    <a href="<c:url value='/viewusers' />">
                                                        <i class="zmdi zmdi-settings"></i>Setting</a>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__footer">
                                                <a href="<c:url value='/logout' />">
                                                    <i class="zmdi zmdi-power"></i>Logout</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                        <div class="row m-t-30">
                            <div class="col-md-12">
                               
                                <form name="userReg" action="userregistration" method="get" id="userReg">
                                <!-- DATA TABLE-->
                                <div class="table-responsive m-b-40">
                                    <table class="table table-borderless table-data3">
                                        <thead>
                                           <tr>
								            	<th style="width: 5%;"> </th>
								            	<th style="width: 20%;"> Name </th>
								            	<th style="width: 15%;"> Number </th>
								            	<th style="width: 40%;"> Address </th>
								            	<th style="width: 40%;"> Email </th>
								            	<th style="width: 15%;"> Qualification </th>
								            	<th style="width: 15%;"> Designation </th>
								            	<th style="width: 15%;"> Username </th>
								            	<th style="width: 15%;"> Role </th>
								        	</tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach items="${users}" var="user">
	            							<tr>
	            <%
				
				ArrayList<UserRole> roles = null;
				ArrayList<Qualification> qualifications = null;
				
				roles = (ArrayList<UserRole>) request.getAttribute("roles");
				qualifications = (ArrayList<Qualification>) request.getAttribute("qualifications");
				User user = (User) pageContext.getAttribute("user");
				
				String strQualification = null;
				String strUserrole = null;
				
				if (roles == null || qualifications == null || user == null)
				{
					System.out.println("UserRoles or Qualifications are not valid!");
				}
				else
				{
					
					
					for (Qualification qualification:qualifications)
					{
						if (user.getQualification_ID() == qualification.getId())
							strQualification = qualification.getQualification();
					}
					
					
					
					for (UserRole role:roles)
					{
						if (user.getRole_id() == role.getId())
							strUserrole = role.getRole();
					}
					
					boolean buadmin =false;
					if (strUserrole.equalsIgnoreCase("admin"))
					{
						buadmin = true;
					}
					
					pageContext.setAttribute("Qualification", strQualification);
	                pageContext.setAttribute("UserRole", strUserrole);
	                pageContext.setAttribute("buadmin", buadmin);
	                    
				}
				
				%>
				
							            <td style="width: 5%;">
							            <div>
										    <input type="checkbox" class="fancy-check chkbox" name="chkbox" id="chkbox${user.getUserid()}" value="${user.getUserid()}"/>
										    <label for="chkbox${user.getUserid()}"><span></span>
										    </label>
										</div>
<%-- 							            <input type="checkbox" class="chkbox" name="chkbox" value="${user.getUserid()}"></td> --%>
							            
						               <c:choose>
                                             <c:when test="${buadmin}">
                                                  <td style="width: 20%; color: #00ad5f;">${user.getName()}</td>
		                                          <td style="width: 15%; color: #00ad5f;">${user.getNumber()}</td>
		                                          <td style="width: 40%; color: #00ad5f;">${user.getAddress()}</td>
		                                          <td style="width: 40%; color: #00ad5f;">${user.getEmailID()}</td>
		                                          <td style="width: 15%; color: #00ad5f;">${Qualification}</td>
		                                          <td style="width: 15%; color: #00ad5f;">${user.getDesignation()}</td>
		                                          <td style="width: 15%; color: #00ad5f;">${user.getUsername()}</td>
                                                  <td style="width: 20%; color: #00ad5f;">
                                                   <span class="role admin"><%=strUserrole%></span>
                                                  </td>
                                             </c:when>
                                             <c:otherwise>
                                                  <td style="width: 20%; color: #fa4251;">${user.getName()}</td>
                                                  <td style="width: 15%; color: #fa4251;">${user.getNumber()}</td>
                                                  <td style="width: 40%; color: #fa4251;">${user.getAddress()}</td>
                                                  <td style="width: 40%; color: #fa4251;">${user.getEmailID()}</td>
                                                  <td style="width: 15%; color: #fa4251;">${Qualification}</td>
                                                  <td style="width: 15%; color: #fa4251;">${user.getDesignation()}</td>
                                                  <td style="width: 15%; color: #fa4251;">${user.getUsername()}</td> 
                                                  <td style="width: 15%; color: #fa4251;">
                                                   <span class="role user">  <%=strUserrole%> </span>
                                                  </td>
                                             </c:otherwise>
                                        </c:choose>
                                                    
							           
							            </tr>
							        	</c:forEach>
                                        </tbody>
                                    </table>
                                    
                              		</div>
                               		<!-- END DATA TABLE-->
                                
                                 	<input type="submit" value="Add" name="mysub" class="btn btn-success">
	    							<input type="submit" value="Edit" name="mysub" class="btn btn-primary">
									<input type="submit" value="Delete" name="mysub" class="btn btn-danger">
                              
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
    
    <script src="${triggerjsjs}"></script>
    
<script type="text/javascript">

function notify(message){
    //alert(message);
    document.getElementsByClassName("js-item-menu")[0].click();
}

function notifyRe(message){
    //alert(message);
    document.getElementsByClassName("js-item-menu")[0].click();
}

</script>

<%
    if (failed != null)
    {
        %>
        <script>
        notify('<%= message %>');
        </script>
        <%
    }
    
    if (success != null)
    {
        %>
        <script>
        notifyRe('<%= message %>');
        </script>
        <%
    }
%>

</body>

</html>
<!-- end document-->
