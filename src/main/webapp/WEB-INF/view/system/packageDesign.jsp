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
var treeData = <%=request.getAttribute("packageDesignTreeJson") %>;

$(function(){
	$('#help_tree').tree({
		checkbox: false,
		animate:true,
		lines:true,
		data: treeData,
		onClick:function(node) {
			if (node.attributes && node.attributes.menuUrl) {
				$('body').layout('panel', 'center').panel('setTitle', node.text);
			} 
		},
		formatter: function(node) {
			return node.text + "-";
		}
	});
});
</script>
</head>
<body class="easyui-layout">

<div data-options="region:'north'" style="height:50px;overflow:hidden;">
  <h1>数据包结构信息</h1>
</div>


<div data-options="region:'east',split:true,title:'导航窗口',iconCls:'icon-help'" style="width:248px;padding:5px; text-align:left;">
	<ul id="help_tree" class="easyui-tree"></ul>
</div>

</body>
</html>
