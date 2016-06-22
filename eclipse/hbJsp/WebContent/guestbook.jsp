 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Guest Book</title>
<link href="bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<jsp:include page="header.jsp" />
  
 <div class="container">

<h1><a href="guestbook">My Guestbook </a> </h1>

<table class="table table-hover">
<tr><th>ID</th><th>Name</th><th>Message</th><th colspan="2"></th></tr>
<c:forEach items="${guestbook}" var="guestbook" varStatus="index">
<tr>
  	<td>${index.count}</td> 
    <td>${guestbook.name}</td>
    <td>${guestbook.message}</td>
    <td><a href="guestbook?action=delete&id=${guestbook.id}"><span class="glyphicon glyphicon-remove"></span></a></td>
    <td><a href="guestbook?action=edit&id=${guestbook.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
</tr>
 </c:forEach>
</table>

<!-- 
<h1>JSTL datasource</h1>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/usersdb"
     user="root"  password=""/>
 
<sql:query dataSource="${snapshot}" var="result">
SELECT * from guestbook;
</sql:query>
 
<table class="table table-hover">
<thead>
	<tr>
	   <th>ID</th>
	   <th>Name</th>
	   <th>Message</th>
	 </tr>
<thead>

<tbody>
<c:forEach var="row" varStatus="status" items="${result.rows}"> 
	<c:if test="${status.count %2 == 0}">
		<tr class="success">
	</c:if>
	<c:if test="${status.count %2 != 0}">
		<tr class="warning">
	</c:if>  
		   <td><c:out value="${row.id}"/></td>
		   <td><c:out value="${row.name}"/></td>
		   <td><c:out value="${row.message}"/></td>
		</tr>
</c:forEach>
</tbody>
</table>
 -->

<h1>Leave your message </h1> 
 <div class="row"> 
 	<div class="col-sm-4">
		<form class="form-horizontal" role="form" action="guestbook" method="post">
		  <div class="form-group">
		    <label class="control-label" for="name">Name:</label>
		    <div>
		      <input type="text" class="form-control" id="name" name="name" maxlength="15" size="15" required placeholder="Enter name">
		    </div> 
		  </div>
		  <div class="form-group">
		    <label class="control-label" for="message">Message:</label>
		    <div> 
		      <textarea class="form-control" id="message" name="message" rows="5" cols="5" required placeholder="Enter message"></textarea>
		    </div>
		  </div>
		  <div class="form-group"> 
		    <div>
		      <button type="submit" class="btn btn-default">Submit</button>
		      <button type="reset" class="btn btn-default">Reset</button>
		    </div>
		  </div>
		</form>
	</div>
  </div>
</div>
  
<jsp:include page="footer.jsp" />

</body>
</html>
 