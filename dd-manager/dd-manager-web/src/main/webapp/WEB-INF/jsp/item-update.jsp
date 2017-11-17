<%--
  Created by IntelliJ IDEA.
  User: hao
  Date: 2017/11/13
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemUpdateForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="ucid" name="cid"  style="width:200px;">
                    <input type="hidden" id="ucatName" value="${tbItemCustom.catName}">
                    <input type="hidden" id="updateId" value="${tbItemCustom.id}" >
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="utitle" name="title" value="${tbItemCustom.title}"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="usellPoint" name="sellPoint"
                           value="${tbItemCustom.sellPoint}"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="upriceView" name="priceView"
                           value="${tbItemCustom.priceView}"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="uprice" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="unum" name="num"
                           value="${tbItemCustom.num}"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="ubarcode" name="barcode"
                           value="${tbItemCustom.barcode}"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="ucontainer" name="content" type="text/plain">${tbItemCustom.sellPoint}</script>
                </td>
            </tr>

            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="usubmitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="backForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">返回
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="uparamData" style="display:none;">
    </form>
</div>

<script>

    //返回关闭选项卡
    function backForm() {
        $("#tab").tabs('close','编辑商品');
    }


    //提交表单
    function usubmitForm(){
        $("#itemUpdateForm").form("submit",{

            url:'item/update',
            //表单提交之前做校验
            onSubmit:function () {
                $("#uprice").val($("#upriceView").val()*100);
                return $(this).form("validate");
            },

            //表单提交成功之后的回调函数
            success:function (data) {
                if(data>0){
                    $.messager.alert('温馨提示','添加商品成功');
                    ddshop.addTabs('查询商品','item-list');
                }

            }
        });
    }

    var ue = UE.getEditor('ucontainer');


    $("#ucid").combotree({
        url:'itemCats?parentId=0',
        required:true,
        onBeforeExpand: function (node) {
            //获取当前被点击的tree
            var $currentTree = $('#ucid').combotree('tree');
            //调用easyui tree组件的options方法
            var option = $currentTree.tree('options');
            //修改option的url属性
            option.url = 'itemCats?parentId=' + node.id;
        },
        onBeforeSelect: function (node) {
            //判断选中节点是否为叶子节点，如果是，返回true
            var isLeaf = $('#ucid').tree('isLeaf', node.target);
            //如果后台管理员选中的不是叶子节点的话，给出警告框
            if (!isLeaf) {
                $.messager.alert('警告', '请选中最终的类别！', 'warning');
                return false;
            }
        },
        onLoadSuccess:function () {
            $("#ucid").combotree("setValue",$("#ucatName").val());
        }
    });

</script>