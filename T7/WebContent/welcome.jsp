<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
 <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
 <html>
 <head>
 <title>welcome.jsp</title>
 </head>
 <body>
    <f:view>
        <h:outputText value="#{user.name}"/> hello!
        <h3>welcom to JavaServer Faces!</h3>
    </f:view>
 </body>
 </html>