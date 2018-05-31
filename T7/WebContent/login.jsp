<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
 <%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
 <html>
 <head>
 <title>login.jsp</title>
 </head>
 <body>
    <f:view>
        <h:form>
            <h3>login</h3>
            name:<h:inputText value="#{user.name}"/><p>
            password:<h:inputSecret value="#{user.password}"></h:inputSecret><p/>
            <h:commandButton value="submit" actionListener="#{user.loginIn}" action="#{user.getOutCome}"/>
            <font color="red"><h:outputText value="#{user.errMessage}"/></font><p>
        </h:form>
    </f:view>
 </body>
 </html>