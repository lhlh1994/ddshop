<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table id="dg" class="easyui-datagrid"
       data-options="fitColumns:true,singleSelect:true,pagination:true,rownumbers: true">

    <thead>
    <tr>
        <th data-options="field:'code',width:100">标题</th>
        <th data-options="field:'name',width:100">名称</th>
        <th data-options="field:'price',width:100,align:'right'">价格</th>
    </tr>
    </thead>
    <tbody>
            <c:forEach items="${list}" var="item">
                <tr>
                <td>${item.title}</td>
                <td>${item.num}</td>
                <td>${item.price}</td>
                </tr>
            </c:forEach>
    </tbody>
</table>


