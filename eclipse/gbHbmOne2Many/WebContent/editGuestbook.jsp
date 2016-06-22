<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit - JSP Guest Book</title>
<link href="bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container">

<h1> Edit My Guestbook </h1>
 
 <div class="row"> 
 	<div class="col-sm-4">
		<form class="form-horizontal" role="form" action="guestbook" method="post">
		  <div class="form-group">
		    <label class="control-label" for="name">Name:</label>
		    <div>
		      <!-- 
		      <input type="text" class="form-control" id="name" name="name" maxlength="15" size="15" 
		      		required placeholder="Enter name" value="${message.user.username}">
		     	-->
		      ${message.user.username}
		    </div> 
		  </div>
		  <div class="form-group">
		    <label class="control-label" for="message">Message:</label>
		    <div> 
		      <textarea class="form-control" id="message" name="message" rows="5" cols="5" required 
		      		placeholder="Enter message">${message.message}</textarea>
		    </div>
		  </div>
		  <div class="form-group"> 
		    <div>
		      <input type="hidden" name="id" value="${message.id}" />
		      <input type="hidden" name="action" value="update" />
		      <button type="submit" class="btn btn-default">Submit</button>
		      <button type="reset" class="btn btn-default">Reset</button>
		    </div>
		  </div>
		</form>
	</div>
  </div>

</div>

</body>
</html>