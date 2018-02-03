<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>配置权限</title>
	<%@ include file="/WEB-INF/jsp/public/public.jspf"%>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pageCommon.css" />
	<script language="javascript" src="${pageContext.request.contextPath}/js/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery_treeview/jquery.treeview.css" />
<script type="text/javascript">
	$(function() {

		// 增加onclick事件处理函数			
		$("input[name=privilegeIds]").click(
			function() {
				// 当选中或取消某个权限时，同时也选中或取消所有的下级权限
				$(this).siblings("ul").find("input").attr("checked",
						this.checked);

				// 当选中某个权限时，应同时选中他的直系上的权限。
				if (this.checked == true) { // 可以直接写为 if(this.checked){..}
					$(this).parents("li").children(
							"input[name=privilegeIds]").attr("checked",
							true);
				}

				// 当取消某个权限时，如果同级的权限都没有选择，就也取消上级权限
				else {
					if ($(this).parent().siblings("li").children(
							"input:checked").size() == 0) {
						$(this).parent().parent().siblings("input").attr(
								"checked", false);
					}
				}
			});
	});
</script>
</head>
<body>
	<!-- 标题显示 -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">岗位管理</a></li>
	    <li><a href="#">设置权限</a></li>
	    </ul>
    </div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="role_setPrivilege">
			<s:hidden name="id"></s:hidden>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<!--表头-->
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;"><input
									type="checkbox" id="cbSelectAll"
									onclick="  $('input[name=privilegeIds]').attr('checked', this.checked)  " />
									<label for="cbSelectAll">全选</label></td>
							</tr>
						</thead>

						<!--显示数据列表-->
						<tbody id="TableData">
							<tr class="TableDetail1">
								<!-- 显示权限树 -->
								<td>
									<ul id="tree">
										<%-- 第一层 --%>
										<s:iterator value="topPrivilegeList">
											<li><input type="checkbox" name="privilegeIds"
												value="${id}" id="cb_${id}"
												<s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
												<label for="cb_${id}"><span class="folder">${name}</span></label>
												<ul>
													<%-- 第二层 --%>
													<s:iterator value="children">
														<li><input type="checkbox" name="privilegeIds"
															value="${id}" id="cb_${id}"
															<s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
															<label for="cb_${id}"><span class="folder">${name}</span></label>
															<ul>
																<%-- 第三层 --%>
																<s:iterator value="children">
																	<li><input type="checkbox" name="privilegeIds"
																		value="${id}" id="cb_${id}"
																		<s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
																		<label for="cb_${id}"><span class="folder">${name}</span></label>
																	</li>
																</s:iterator>
															</ul></li>
													</s:iterator>
												</ul></li>
										</s:iterator>
									</ul> 
									<script type="text/javascript">
										$("#tree").treeview();
									</script>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
	        <div id="InputDetailBar">
	            <input type="submit" class="btn1" value="保存"/>
	            <input type="button" class="btn1" value="返回" onclick="javascript:history.go(-1);"/>
	        </div>
		</s:form>
	</div>

</body>
</html>
