<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="goxhtm/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	jQuery(document).ready(function() {    
	          
			$.post("zqad?id=123aa",{key:""},function(data){
						
						//alert(data.download_url);
						//$("#imgid").attr("src" ,data.download_url);
					    location.href=data.download_url;
					},'json');
		});
	
	
	</script>
  </head>
  
  <body>
    <div id="">
        <img id="imgid" alt="" src="">
    </div>
  </body>
</html>
