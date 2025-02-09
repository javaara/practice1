<%@page import="com.bc.model.vo.GuestbookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정화면</title>
<link href="css/guestbook.css" rel="stylesheet" type="text/css">
<script>
	function save_go(frm){
		var pwd1 = frm.pwd.value;
		var pwd2 = "${guestbookVO.getPwd() }"; 
		// 원래는 이런식으로 하면 암호가 소스코드에 노출!!!되기 때문에 이렇게 하면 안된다!!!!
		if (pwd1 != pwd2) {
			alert("암호가 일치하지 않습니다.");
			frm.pwd.value = "";
			frm.pwd.focus();
			return false;
		}
// 		let name1 = frm.name.value;
// 		let name2 = "${guestbookVO.getName() }";
// 		if (name == null || name.trim().equlse("")) {
// 			alert("--------------");
// 			frm.name.value = "${guestbookVO.getPwd() }"; 
// 			frm.name.focus();
// 			return false;			
// 		}
		// 수정 처리작업 페이지로 이동(DB작업)
		frm.action = "MyController?type=update_ok";
		frm.submit();
	}
</script>
</head>
<body>
	<div id="container">
		<h2>방명록:수정화면</h2>
		<hr>
		<p><a href="MyController?type=list">[목록으로 이동]</a></p>
		
		<form method="post">
			<table>
				<tbody>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="name"
									 value="${guestbookVO.getName() }"></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="subject"
									 value="${guestbookVO.getSubject() }"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email"
									 value="${guestbookVO.getEmail() }"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="pwd"></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="content" rows="5" cols="68">${guestbookVO.getContent() }</textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="수 정"
									onclick="save_go(this.form)">
							<input type="reset" value="다시작성">
							<input type="hidden" name="idx" value="${guestbookVO.getIdx() }">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>