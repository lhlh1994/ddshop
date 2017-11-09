<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table id="dg"></table>
</table>

<script>
    //添加工具栏
    var toolbar=[{
        iconCls: 'icon-add',
        text:'新增',
        handler: function () {
            console.log('add');
        }
    },{
        iconCls: 'icon-remove',
        text:'删除',
        handler: function () {
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
                            alert('删除成功');
                            $('#dg').datagrid('reload');
                        },
                        //datatype:返回的数据类型,String类型
                        'json'
                    );
                }

            });
        }
    },{
        iconCls: 'icon-edit',
        text:'编辑',
        handler: function () {
            console.log('edit');
        }
    },{
        iconCls: 'icon-up',
        text:'上架',
        handler: function () {
            var ids=[];//为了存放id集合
            var selections = $('#dg').datagrid('getSelections');
            for(var i=0;i<selections.length;i++){
                ids.push(selections[i].id   );
            }
            $.post(
                'items/up',
                {'ids[]':ids},
                function(data){
                    alert('上架成功')
                    $('#dg').datagrid('reload');
                },
                'json'
            );
        }
    },{
        iconCls: 'icon-down',
        text:'下架',
        handler: function () {
            var ids=[];//为了存放id集合
            var selections = $('#dg').datagrid('getSelections');
            for(var i=0;i<selections.length;i++){
                ids.push(selections[i].id   );
            }
            $.post(
                'items/down',
                {'ids[]':ids},
                function(data){
                    alert('下架成功')
                    $('#dg').datagrid('reload');
                },
                'json'
            );
        }
    }

    ]

    //初始化表格数据
    $('#dg').datagrid({
        toolbar:toolbar,//将工具栏添加到表格中
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
