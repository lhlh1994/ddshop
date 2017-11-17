<%--
  Created by IntelliJ IDEA.
  User: hao
  Date: 2017/11/13
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="toolbar1">
    <div>
        <button type="button" onclick="addParam()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button type="button" onclick="editParam()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button type="button" onclick="delParam()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
    </div>
</div>
<table id="table"></table>

<script>

        //新增分组页面
        function addParam() {
            ddshop.addTabs("新增商品规格模板","item-param-add");
        }


        //列表
        $('#table').datagrid({
            title: '商品规格模板列表',
            url:'itemParams',
            fit: true,
            rownumbers: true,
            pagination: true,
            pageSize:20,
            toolbar: '#toolbar1',
            columns:[[
                {field:'ck', checkbox: true},
                {field:'id',title:'ID', sortable: true},
                /* {field:'itemCatId',title:'商品类目ID'}, */
                {field:'itemCatName',title:'商品类目'},
                {field:'paramData',title:'规格(只显示分组名称)', formatter:function(value,row,index){
                    var obj=JSON.parse(value);
                    var array=[];
                    $.each(obj,function (i,e) {
                        array.push(e.group);
                    })
                    return array;
                }},
                {field:'createdView',title:'创建日期', formatter:function(value,row,index){
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }},
                {field:'updated',title:'更新日期', formatter:function(value,row,index){
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }}
            ]]

        });
</script>