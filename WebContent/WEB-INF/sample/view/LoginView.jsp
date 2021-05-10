<%@ page language="java" contentType="text/html; charset=UTF-8"
  import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*"
  import="model.*,java.util.*" import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.7.4/lottie.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.1/css/bulma.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
  <nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
<a role="button" class="navbar-burger burger" aria-label="menu"
        aria-expanded="false" data-target="navbarBasicExample"> <span
        aria-hidden="true"></span> <span aria-hidden="true"></span> <span
        aria-hidden="true"></span>
      </a>
    </div>
  </nav>
    <!-- END NAV -->
    <form action="" method="POST" >
    <section class="container">
            <div class="columns is-centered">
                    <div class="column is-half-desktop">
                        <%
                        if(session.getAttribute("errormsg") != null){
                            %>
                    <script type="text/javascript">
                    var foo = function () {
                          // 1つ目に追加
                          $(".collapse1").addClass("animate__animated animate__hinge ");
                          // 2つ目に追加（1秒後）
                          setTimeout(function () {
                            $(".collapse2").addClass("animate__animated animate__hinge");
                          }, 1000);
                          // 3つ目に追加（1秒後）
                          setTimeout(function () {
                            $(".collapse3").addClass("animate__animated animate__hinge");
                          }, 2000);
                          // 最後に元に戻す
                          setTimeout("location.reload()",5000);
                        };setTimeout( "foo()", 3000 );
                    </script>
                            <%
                        }
                        %>
                    <div class="columns is-centered collapse2">
                    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
                    <lottie-player src="https://assets2.lottiefiles.com/packages/lf20_chst5vbq.json" background="transparent"  speed="1"  style="width: 500px; height: 100px;"autoplay></lottie-player>
                    <div class="columns is-vcentered">by LottieFiles</div>
                    </div>
                    	<div class="field collapse1">
							<div class="control has-icons-left has-icons-right">
							  <input class="input is-medium" id="user_id" name="USER_ID" type="email" placeholder="Email">
							  <span class="icon is-left">
							    <i class="fas fa-envelope"></i>
							  </span>
							  <span class="icon is-right">
							    <i class="fas fa-check"></i>
							  </span>
							</div>
						</div>
						<div class="field collapse1">
						  <div class="control has-icons-left has-icons-right">
						    <input class="input is-medium" id="user_pass" name="USER_PASSWORD" type="password" placeholder="Password" value="}Tp1c8XtmJX=-L">
						    <span class="icon is-small is-left">
						      <i class="fas fa-lock"></i>
						    </span>
						  </div>
						</div>
						<div class="collapse3">
						<div class="control has-text-centered has-text-danger">
						<%
						if(session.getAttribute("errormsg") != null){
						    out.println(session.getAttribute("errormsg"));
						    session.removeAttribute("errormsg");
						}
						%>
						</div>
                        <div class="has-text-left login_item"><a href="">Forgot Password ?</a></div>
                        <div class="has-text-left login_item"><a href="Register">Register</a></div>
                        <div class="control">
                            <button type="submit" id="login" name="login" class="button is-link is-fullwidth has-text-weight-medium is-medium">Login</button>
                        </div>
                        </div>
                    </div>
                </div>
    </section>
    </form>

</body>

</html>