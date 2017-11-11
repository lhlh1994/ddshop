<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title">
        <label>商品状态：</label>
        <select id="status" class="easyui-combobox">
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
        <%--<a onclick="searchForm()" class="easyui-linkbutton">搜索</a>--%>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>

<table id="dg"></table>
</table>

<script>

    function searchForm() {
        $('#dg').datagrid('load',{
            title:$('#title').val(),
            status:$('#status').combobox('getValue')
        });
    }

    function add() {
        ddshop.addTabs('新增商品','item-add');
    }

    function edit() {
        console.log('edit');
    }

    function remove() {
        //获取复选框选中的记录的信息
        var selections = $('#dg').datagrid('getSelections');
        //console.log(selections)
        if(selections.length==0){
            $.messager.alert('提示','请至少选中一条记录!');
            return;
        }
        $.messager.confirm('确认','您确定要删除该商品吗?',function(r){
            if(r){
                var ids=[];//为了存放id集合
                for(var i=0;i<selections.length;i++){
                    ids.push(selections[i].id   );
                }
                $.post(
                    //url:请求后台的地址进行处理,String类型
                    'items/batch',
                    //data:从前来提交到后台的数据,Object类型
                    {'ids[]':ids},
                    //callback:后台处理成功后的回调函数,相当于success,function类型
                    function(data){
                        alert(data+'件商品删除成功');
                        $('#dg').datagrid('reload');
                    },
                    //datatype:返回的数据类型,String类型
                    'json'
                );
            }

        });
    }

    function down() {
        var ids=[];//为了存放id集合
        var selections = $('#dg').datagrid('getSelections');
        for(var i=0;i<selections.length;i++){
            if(selections[i].status!=2) {
                ids.push(selections[i].id);
            }
        }
        if(ids.length>0){
            $.post(
                'items/down',
                {'ids[]':ids},
                function(data){
                    alert(data+'件商品下架成功')
                    $('#dg').datagrid('reload');
                },
                'json'
            );
        }else{
            alert('请选择未下架的商品');
        }

    }

    function up() {
        var ids=[];//为了存放id集合
        var selections = $('#dg').datagrid('getSelections');
        for(var i=0;i<selections.length;i++){
            if(selections[i].status!=1){
                ids.push(selections[i].id);
            }

        }
        if(ids.length>0) {
            $.post(
                'items/up',
                {'ids[]': ids},
                function (data) {
                    alert(data + '件商品上架成功')
                    $('#dg').datagrid('reload');
                },
                'json'
            );
        }else{
            alert('请选择未上架的商品');
        }
    }


    //初始化表格数据
    $('#dg').datagrid({
        multiSort:true,//允许多列排序
        toolbar:"#toolbar",//将工具栏添加到表格中
        url:'items',//请求远程url
        striped:true,//斑马线效果,隔行变色
        rownumbers:true,//显示行号
        pagination:true,//显示分页工具
        fit:true,//使得数据表格自适应填充父容器
        pageSize: 10,
        pageList: [10,50,100],
        columns:[[
            {field:'ck',checkbox:true},//显示复选框
            {field:'id',title:'商品编号',width:100,sortable:true},
            {field:'title',title:'商品名称',width:100,sortable:true},
            {field:'priceView',title:'价格',width:100},
            {field:'catName',title:'商品类别',width:100},
            {field:'statusName',title:'状态',width:100},
            {field:'sellPoint',title:'卖点',width:100},
            {field:'created',title:'创建日期',width:100,formatter:function(value,row,index){
                return moment(value).format('LL');
            }},
            {field:'updated',title:'最后修改日期',width:100,formatter:function(value,row,index){
                return moment(value).format('LL');
            }}
        ]]
    });
</script>