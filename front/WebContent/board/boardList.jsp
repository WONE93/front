<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
<title>boardList.jsp</title>
</head>
<body>

	<h3>게시판</h3>
	<table border="1">
		<tr><th>제목</th><th>작성자</th><th>작성일자</th></tr>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td><a href="BoardUpdate.do?seq=${vo.seq}">${vo.title}</a></td>				
			<td>${vo.id}</td>
			<td>${vo.regdt}</td>
				</tr>
		</c:forEach>
	</table>

</body>
</html>