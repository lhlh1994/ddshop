<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table id="dg"></table>
</table>

<script>
    $('#dg').datagrid({
        url:'items',//请求远程url
        striped:true,//斑马线效果,隔行变色
        rownumbers:true,//显示行号
        pagination:true,//显示分页工具
        fit:true,//使得数据表格自适应填充父容器
        pageSize: 10,
        pageList: [10,50,100],
        columns:[[
            {field:'ck',checkbox:true},//显示复选框
            {field:'title',title:'标题',width:100},
            {field:'price',title:'价格',width:100},
            {field:'catName',title:'商品类别',width:100},
            {field:'sellPoint',title:'卖点',width:100}
        ]]
    });
</script>
