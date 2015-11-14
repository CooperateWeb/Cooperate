<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>模型策划平台</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/easyui/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/style/main.css">
<script type="text/javascript">
var treeData = <%=request.getAttribute("catalogTreeJson") %>;

$(function(){
	$('#help_tree').tree({
		checkbox: false,
		animate:true,
		lines:true,
		data: treeData,
		onClick:function(node) {
			var tree=$(this).tree;
			if (node.attributes && tree('isLeaf',node.target)) {
				$('#show_win').panel('refresh','<%=request.getContextPath() %>/page/packageDesign');
				$('body').layout('panel', 'center').panel('setTitle', node.text);
			} 
		},
		formatter: function(node) {
			return node.text + "-";
		}
	});
	$('#show_win').panel({
				fit:true,
				border:false,
				noheader:false
	});
});
</script>
</head>
<body class="easyui-layout">

<div data-options="region:'north'" style="height:50px;overflow:hidden;">
  <h1>模型策划平台</h1>
  <div id="login_user_info">欢迎你：${currentUser.userName}. <a href="<%=request.getContextPath() %>/logout">退出</a></div>
</div>


<div data-options="region:'west',split:true,title:'导航窗口',iconCls:'icon-help'" style="width:248px;padding:5px; text-align:left;">
	<div id="rolelist_dg_toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCatalog()">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCatalog()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCatalog()">删除</a>
</div>
<div id="catalog_save_dialog" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#catalog_save_dialog_buttons" modal="true">
	<div class="ftitle">输入目录节点的信息</div>
	<form id="catalog_info_form" method="post">
		<div class="fitem">
			<label>节点名称：</label>
			<input type="text" name="catalogName">
		</div>
		<div class="fitem">
			<label>类       别：</label>
			<select name="catalogType" id="catalogList1">
			<option value="0" selected>数据库分类</option>
			<option value="1" >数据库</option> 
			</select>
		</div>
		<div class="fitem">
			<label >备      注：</label>
			<input type="text" name="remark" />
		</div>
	</form>
	<div id="#catalog_save_dialog_buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCatalog()">保存</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#catalog_save_dialog').dialog('close')">取消</a>
	</div>
</div>
	<ul id="help_tree" class="easyui-tree"></ul>
</div>
<div data-options="region:'center'" title="主窗口" style=" padding:10px; text-align:left;">
  <div id="show_win"></div>
</div>

<script>
setInterval(function() {
	var url = '<%=request.getContextPath() %>/checkSession';
	$.get(url, function(result) {
		if (! result.successful) {
			window.location.href="<%=request.getContextPath() %>";
		}
	}, 'json');
}, 60000);
</script>
<script type="text/javascript">
var UrlConfig={
		catalogAdd:'<%=request.getContextPath()%>/catalog/add',
		catalogUpdate:'<%=request.getContextPath()%>/catalog/update',
		catalogDelete:'<%=request.getContextPath()%>/catalog/delete',
		catalogList:'<%=request.getContextPath()%>/catalog/list'
};
var url;
function newCatalog(){
	$('#catalog_save_dialog').dialog('open').dialog('setTitle','添加目录');
	$('#help_tree').form('clear');
	url=UrlConfig.catalogAdd;
}

function saveCatalog(){
	$('#catalog_info_form').form('submit',{
		url:url,
		onSubmit:function(){
			return $(this).form('validte');
		},
		success:function(result){
			result=eval('('+result+')');
			if(result.successful){
				$('#catalog_save_dialog').dialog('close');
				$('#help_tree').tree('reload');
				$.messager.show({title:'操作结果',msg:'操作成功'});
			}else{
				$.messager.show({title:'操作结果',msg:result.msg});
			}
		}
	});
}

</script>
</body>
</html>
