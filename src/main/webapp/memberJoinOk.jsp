<%@page import="com.kmii.dto.MemberDto"%>
<%@page import="com.kmii.dao.MemberDao"%>
<jsp:useBean id="memberDto" class="com.kmii.dto.MemberDto"/>
<jsp:setProperty property="*" name="memberDto"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 확인</title>
</head>
<body>
		<h2>회원 가입</h2>
		<hr>
		<%
		
		//String mid = request.getParameter("memberid"); 
		//String mpw = request.getParameter("memberpw");
		//String mname = request.getParameter("membername");
		//int mage = Integer.parseInt(request.getParameter("memberage"));
		//String memail = request.getParameter("memberemail");
		
		//MemberDto memberDto = new MemberDto();
		//memberDto.setMemberid(mid);
		//memberDto.setMemberpw(mpw);
		//memberDto.setMembername(mname);
		//memberDto.setMemberage(mage);
		//memberDto.setMemberemail(memail);
		
				MemberDao memberDao = new MemberDao();
				
				int idCheck = memberDao.confirmId(memberDto.getMemberid());
				
				if(idCheck == MemberDao.MEMBER_ID_EXISTENT){
					out.println("<script>alert('회원 가입 성공');</script>");
				}else {
					int joinResult = memberDao.insertMember(memberDto); //1(성공), 0(실패)
					
					if(joinResult == MemberDao.MEMBER_JOIN_SUCCESS){  // String mid == 1
							out.println("<script>alert('회원 가입 성공');</script>");
							// response.sendRedirect("login.jsp");
					} else {
						out.println("<script>alert('회원 가입 실패');history.go(-1);</script>");  // history.go(-1) : 이전페이지로
						
					}
					
				}
				
				
		%>
		
</body>
</html>