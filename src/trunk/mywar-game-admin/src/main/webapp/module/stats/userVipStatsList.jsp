<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/graphTools.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="userVipStatsListJsp.title"></s:text></title>
		<style type="text/css">
		td {
			text-align: center;
		}
		</style>		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="../../css/main.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../../My97DatePicker/stats_WdatePicker.js"></script>
	</head>
	<body>
		<form action="userVipStatsList" method="post" onsubmit="return check()">
			<s:text name="timeFrom"></s:text><s:text name="colon"></s:text>
			<input type="text" onfocus="WdatePicker({onpicked:function(){endDate.focus();}})" class="Wdate" id="startDate" name="startDate" value="<s:if test="startDate != null"><s:text name="format.date_ymd"><s:param value="startDate"></s:param></s:text></s:if>" style="width:100px"/>
			<s:text name="timeTo"></s:text>
			<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" class="Wdate" id="endDate" name="endDate" value="<s:if test="endDate != null"><s:text name="format.date_ymd"><s:param value="endDate"></s:param></s:text></s:if>" style="width:100px"/>
			
			<s:text name="pageSize"></s:text><s:text name="colon"></s:text><input type="text" name="pageSize" value="${pageSize}" class="maxLife" size="20" onblur="value=value.replace(/[^\d]/g,'')">
			<input type="submit" value="<s:text name='submit'></s:text>" class="button" />
		</form>
		<table id="table" cellpadding="3" cellspacing="1" border="0" width="100%" align=center>
			<tr class="border">
				<td class="td_title" colspan="22" align="center">
					<center>
						<s:text name="userVipStatsListJsp.title"></s:text>
					</center>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<s:text name="userVipStats.time"></s:text>
				</td>
				<td rowspan="2">
					<s:text name="userVipStats.channel"></s:text>
				</td>
				<td colspan="11" style="text-align:center">
					<s:text name="userVipStats.vipLevel"></s:text>
				</td>
				<tr>
					<td>
						<s:text name="userVipStats.v0"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v1"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v2"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v3"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v4"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v5"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v6"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v7"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v8"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v9"></s:text>
					</td>
					<td>
						<s:text name="userVipStats.v10"></s:text>
					</td>
				</tr>
			</tr>
			<s:iterator var="list" value="statsMap">
				<tr>
					<td rowspan="${fn:length(value)+1}">
						${key}
					</td>
					<s:iterator var="stats" value="value">
						<tr>
							<td>
								${partnerMap[stats.partnerId].PName}
							</td>
							<td>
								${stats.v0}
							</td>
							<td>
								${stats.v1}
							</td>
							<td>
								${stats.v2}
							</td>
							<td>
								${stats.v3}
							</td>
							<td>
								${stats.v4}
							</td>
							<td>
								${stats.v5}
							</td>
							<td>
								${stats.v6}
							</td>
							<td>
								${stats.v7}
							</td>
							<td>
								${stats.v8}
							</td>
							<td>
								${stats.v9}
							</td>
							<td>
								${stats.v10}
							</td>
						</tr>	
					</s:iterator>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="22">
					<aldtags:pageTag datePara1="startDate" dateValue1="${startDate}" datePara2="endDate" dateValue2="${endDate}" para1="pageSize" value1="${pageSize}"/>
				</td>
			</tr>
		</table>
		<br/>
		说明：
		<br/>
		1、统计当天数据
	</body>
</html>