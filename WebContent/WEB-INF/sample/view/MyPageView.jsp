<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*"
	import="model.*,java.util.*" import="java.util.HashMap"
	import="login.BusinessLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<script src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 並べ替えtable-dragger -->
<script
	src="https://cdn.jsdelivr.net/npm/table-dragger@1.0.3/dist/table-dragger.js"></script>
<!-- bulma -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.1/css/bulma.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
	<!-- navbar -->
	<nav class="navbar" role="navigation" aria-label="main navigation">
		<div class="navbar-brand">
			<a role="button" class="navbar-burger burger" aria-label="menu"
				aria-expanded="false" data-target="navbarBasicExample"> <span
				aria-hidden="true"></span> <span aria-hidden="true"></span> <span
				aria-hidden="true"></span>
			</a>
		</div>

		<div id="navbarBasicExample" class="navbar-menu">
			<div class="navbar-start">
				<a class="navbar-item" href="JsonGet"> Home </a> <a
					class="navbar-item" href="Mypage"> Mypage </a>
			</div>

			<div class="navbar-end">
				<div class="navbar-item">
					<div>
						<div class="buttons">
							<%
							    UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("LOGIN_INFO");
							%>
							<%
							    if (userInfoDto != null) {
							        String userName = userInfoDto.getUserName();
							        String sessinId = session.getId();
							%>
							ようこそ
							<%
							    out.print(userName);
							%>
							さん <a href="Logout" class="button is-light"> Logout <i
								class="fas fa-sign-out-alt"></i></a>
						</div>
						<%
						    } else {
						        response.sendRedirect("Login");
						        return;
						    }
						%>
					</div>
				</div>
			</div>
		</div>
	</nav>
	<div class="columns is-mobile">
		<!-- 左のサイドバー -->
		<div class="column"></div>
		<%
		    UserDataBaseDto userDataBaseDto = (UserDataBaseDto) session.getAttribute("DATABASE_INFO");
		%>
		<!-- メイン -->
		<div class="column is-two-thirds">
			<div class="has-text-centered">
				<div class="columns is-mobile">
					<div class="tile is-ancestor">
						<div class="tile is-vertical is-half">
							<div class="tile">
								<div class="tile is-parent">
									<article class="tile is-child notification is-white box">
										<form action="" method="POST">
											<p class="has-text-weight-bold is-size-5">
												<img alt=""
													src="${pageContext.request.contextPath}/images/user.png"
													style="width: 50px; vertical-align: middle;"><span
													class="ml-3" style="vertical-align: middle;">ユーザー情報</span>
											</p>
											<%
											    if (userInfoDto != null) {
											        out.println("<div class=\"columns is-mobile\">");
											        out.println("<div class=\"column\"></div>");
											        out.println("<div class=\"column is-half\">");
											        out.println("<div class=\"p-3 has-text-left\">");
											        out.println("<div class=\"columns is-mobile\">");
											        out.println("<div class=\"column\">");
											        out.println("<label>ユーザー名:</label>");
											        out.println(userInfoDto.getUserId());
											        out.println("</div>");
											        out.println("</div>");
											        out.println("</div>");
											        out.println("<div class=\"p-3 has-text-left\">");
											        out.println("<label>パスワード:</label>");
											        out.println("●●●●");
											        out.println("</div>");
											        out.println("</div>");
											        out.println("<div class=\"column\"></div>");
											        out.println("</div>");
											    }
											%>
											<div class="p-3">
												<button type="submit" class="mt-6 button is-primary btn"
													data-target="my-modal">Change</button>
											</div>
										</form>
									</article>
								</div>
								<div class="tile is-parent">
									<article class="tile is-child notification is-white box">
										<form action="MyDataBase" method="POST">
											<p class="has-text-weight-bold is-size-5">
												<img alt=""
													src="${pageContext.request.contextPath}/images/database.png"
													style="width: 50px; vertical-align: middle;"><span
													class="ml-3" style="vertical-align: middle;">データベース情報</span>
											</p>
											<%
											    if (userDataBaseDto != null) {
											        out.println("<div class=\"columns is-mobile\">");
											        out.println("<div class=\"column\"></div>");
											        out.println("<div class=\"column is-half\">");
											        out.println("<div class=\"p-3 has-text-left\">");
											        out.println("<div class=\"columns is-mobile\">");
											        out.println("<div class=\"column\">");
											        out.println("<label>ユーザー:</label>");
											        out.println(userDataBaseDto.getDataBaseId());
											        out.println("</div>");
											        out.println("</div>");
											        out.println("</div>");
											        out.println("<div class=\"p-3 has-text-left\">");
											        out.println("<label>パスワード:</label>");
											        out.println(userDataBaseDto.getDataBasePass());
											        out.println("</div>");
											        out.println("<div class=\"p-3 has-text-left\">");
											        out.println("<label>データベース名:</label>");
											        out.println(userDataBaseDto.getDataBaseName());
											        out.println("</div>");
											        out.println("</div>");
											        out.println("<div class=\"column\"></div>");
											        out.println("</div>");
											    } else {
											        out.println("データベース情報を登録してください");
											    }
											%>
											<div class="p-3">
												<button type="submit" class="button is-primary btn"
													data-target="my-modal">Change</button>
											</div>
										</form>
									</article>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 右のサイドバー -->
		<div class="column"></div>
	</div>
	<footer class="footer footerMain">
		<div class="content has-text-centered">
			<p>copyright &copy; 2021 Generator. All rights reserved</p>
		</div>
	</footer>
</body>
</html>