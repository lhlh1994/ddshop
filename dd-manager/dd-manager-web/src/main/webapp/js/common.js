
var ddshop = {

    registerMenuEvent:function(){
        //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        var $tree = $('#menu .easyui-tree');
        //将当前树打印到控制台
        //console.log($tree);
        $tree.tree({
            onClick:function(node){
                var href = node.attributes.href;//item-add
                var text = node.text;
                var flag=$('#tab').tabs('exists',text);
                if(flag){
                    $('#tab').tabs('select',node.text) //如果当前选项卡已经打开，则选中已打开的选项
                    return;
                }
                $('#tab').tabs('add',{
                    title: text,
                    href: href,
                    closable:true,

                });
            }
        });
    }

};



