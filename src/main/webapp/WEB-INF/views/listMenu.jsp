<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
    <title>Restaurant Menu</title>
    <style>
    .error 
    {
        color: #ff0000;
        font-weight: bold;
    }
    #menu tr:first-child{
        font-weight: bold;
    }
    </style>
</head>
 
<body>
     
    <h2><spring:message code="lbl.page.list" text="lbl.page.list" /></h2>
    <br/>
     
    <table id="Menu" border="1">
    <tr>
        <td>ID</td>
        <td>Category</td>
        <td>Food Item</td>
        <td>Price</td>
      </tr>
    <c:forEach items="${allItems}" var="foodItem">    
      <tr>
        <td>${foodItem.id}</td>
        <td>${foodItem.category}</td>
        <td>${foodItem.itemName}</td>
        <td>${foodItem.price}</td>
      </tr>
    </c:forEach>
    </table>
 
    <h2><spring:message code="lbl.page" text="Add Menu item" /></h2>
    <br/>
    <form:form method="post" modelAttribute="foodItem">
        <table>
            <tr>
                <td><spring:message code="lbl.category" text="Category" /></td>
                <td><form:input path="category" /></td>
                <td><form:errors path="category" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.itemName" text="Item Name" /></td>
                <td><form:input path="itemName" /></td>
                <td><form:errors path="itemName" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.price" text="Price" /></td>
                <td><form:input path="price" /></td>
                <td><form:errors path="price" cssClass="error" /></td>
            </tr>           
            <tr>
                <td colspan="3"><input type="submit" value="Add Item"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>