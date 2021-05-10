<%@ page language="java" contentType="text/html; charset=UTF-8"
  import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*"
  import="model.*,java.util.*" import="java.util.HashMap" import="login.BusinessLogic"%>
<%
@SuppressWarnings("unchecked")
List<JsonDto> DTOList = (List<JsonDto>) session.getAttribute("DTOList");
%>
<%
UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
String userName = null;
String userId = null;
String sessinId = null;
Random random = new Random();
int randomValue = random.nextInt(100);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jsonget_sample</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 並べ替えtable-dragger -->
<script src="https://cdn.jsdelivr.net/npm/table-dragger@1.0.3/dist/table-dragger.js"></script>
<!-- bulma -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.1/css/bulma.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<!-- navbar -->
  <nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <a role="button" class="navbar-burger burger" aria-label="menu"
        aria-expanded="false" data-target="navbarBasicExample">
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
      <form name="form_name" method="POST" action="Mypage">
        <div class="navbar-start">
          <a class="navbar-item" href=""> Home </a>
          <a class="navbar-item" href="javascript:form_name.submit()"> Mypage </a>
        </div>
      </form>

      <div class="navbar-end">
        <div class="navbar-item">
          <div>
          <div class="buttons">

          <%
          if(userInfoDto != null){
              userName = userInfoDto.getUserName();
              userId = userInfoDto.getUserId();
              sessinId = session.getId();
              %>
              ようこそ
              <%
              out.print(userName);
              %>
              さん
            <a href="Logout" class="button is-light"> Logout <i class="fas fa-sign-out-alt"></i></a>
          </div>
          <%
          }else{
              %>
          <div class="buttons">
            <a href="Login" class="button is-light"> Login <i class="fas fa-sign-in-alt"></i></a>
          </div>
          <%
          }
          %>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <div class="columns is-mobile">
    <!-- 左のサイドバー -->
    <div class="column">
    </div>
    <div class="column is-two-thirds">
      <form name="form" action="JsonGet" method="POST">

        <!-- 条件 -->
        <div class="columns is-mobile div_conditions">
          <div class="column">

            <div class="columns is-vcentered">

              <label class="checkbox my-3 mr-3"> <input type="checkbox" name="aaa" onClick="AllChecked();">
              全選択
              </label>

            </div>
            <div class="columns is-vcentered">
              <label class="my-3"> 件数 </label>
              <div class="select label_count">
                <select name="number">
                  <option value="10">10件</option>
                  <option value="20">20件</option>
                  <option value="30">30件</option>
                  <option value="40">40件</option>
                  <option value="50">50件</option>
                  <option value="60">60件</option>
                  <option value="70">70件</option>
                  <option value="80">80件</option>
                  <option value="90">90件</option>
                  <option value="100">100件</option>
                </select>
              </div>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="item_check" checked="checked">
               項番
              </label>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="phone_check" checked="checked">
               電話番号
              </label>
              <div class="control has-icons-left">
              <div class="select label_phone">
                <select class="item" name="phone">
                  <option value="1">電話番号</option>
                  <option value="2">携帯電話　　　　　　　　　</option>
                </select>
                <div class="icon is-small is-left">
                  <i class="fas fa-phone"></i>
                </div>
              </div>
              </div>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="mail_check">
                メールアドレス
              </label>
              <div class="control has-icons-left">
              <div class="select label_email">
                <select name="mail_format" class="item">
                  <option value="1">セーフメールアドレス　　　</option>
                  <option value="2">フリーメールアドレス</option>
                  <option value="3">企業メールアドレス</option>
                </select>
                <div class="icon is-small is-left">
                  <i class="fas fa-envelope"></i>
                </div>
              </div>
              </div>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="birthday_check" checked="checked">
                生年月日
              </label>
              <div class="control has-icons-left">
              <div class="select label_birthday">
                <select name="date_format" class="item">
                  <option value="1">yyyymmdd</option>
                  <option value="2">yyyy-mm-dd</option>
                  <option value="3">yyyy年mm月dd日　　　　　</option>
                </select>
                <div class="icon is-small is-left">
                  <i class="fas fa-calendar"></i>
                </div>
              </div>
              </div>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="age_check">
                年齢
              </label>
              <div class="columns">
                <div class="column">
	              <div class="select label_age">
	                <select class="item" name="age2">
	                  <option value="1">0歳代</option>
	                  <option value="2">10代</option>
	                  <option value="3">20代</option>
	                  <option value="4">30代</option>
	                  <option value="5">40代</option>
	                  <option value="6">50代</option>
	                  <option value="7">60代</option>
	                  <option value="8">70代</option>
	                  <option value="9">80代</option>
	                  <option value="10">90代</option>
	                  <option value="11">100代～</option>
	                </select>
	              </div>
	              </div>
	              <div class="column">
	              <p class="border_p">～</p>

	              </div>
	              <div class="column">
	              <div class="select">
	                <select class="item" name="age1">
	                  <option value="1">0歳代</option>
	                  <option value="2">10代</option>
	                  <option value="3">20代</option>
	                  <option value="4">30代</option>
	                  <option value="5">40代</option>
	                  <option value="6">50代</option>
	                  <option value="7">60代</option>
	                  <option value="8">70代</option>
	                  <option value="9">80代</option>
	                  <option value="10">90代</option>
	                  <option value="11">100歳～</option>
	                </select>
	              </div>
	              </div>
              </div>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3 mb-6"> <input type="checkbox" name="address_check" checked="checked">
                住所
              </label>
            </div>

          </div>
          <div class="column">
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="gender_check">
                性別
              </label>
              <div class="control has-icons-left">
              <div class="select label_address">
                <select class="item" name="gender_format">
                  <option value="1">男性・女性</option>
                  <option value="2">男性・女性・未回答</option>
                  <option value="3">男性・女性・その他</option>
                </select>
                <div class="icon is-small is-left">
                  <i class="fas fa-venus-mars"></i>
                </div>
              </div>
              </div>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="credit_check">
                クレジットカード
              </label>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3"> <input type="checkbox" name="password_check">
                パスワード
              </label>
            </div>
            <div class="columns is-vcentered">
              <label class="checkbox my-3 mr-3"> <input type="checkbox" name="name_check" checked="checked">
                氏名
              </label>
              <label class="checkbox my-3 mr-3"> <input type="checkbox" name="lastname_check">
                姓
              </label>
              <label class="checkbox my-3"> <input type="checkbox" name="firstname_check">
                名
              </label>
            </div>
          </div>
        </div>
        <!-- ボタン -->
        <div class="field">
          <div class="has-text-centered">
          <div class="columns is-mobile">
            <div class="column">
              <div class="card">
              <div class="card_title">Dummy Data</div>
              <div class="card_image"><i class="far fa-plus-square fa-3x"></i></div>
              <div class="card_sentence">Create dummy data.</div>
            <div>
            <button type='submit' class="button is-primary btn" name="submit">
              Create　<i class="fas fa-plus"></i>
            </button>
            </div>
            <div>
            <button type='submit' class="button is-primary btn" name="reforming">
              Reload　<i class="fas fa-redo"></i>
            </button>
            </div>
              </div>
            </div>
            <div class="column">
              <div class="card">
              <div class="card_title">Database</div>
              <div class="card_image"><i class="fas fa-database fa-3x"></i></div>
              <div class="card_sentence">Put data in the database.</div>
            <div>
            <button type="button" class="button show-modal is-primary btn" data-target="my-modal">
              Registration　<i class="fas fa-database"></i>
            </button>
            </div>
            <div>
            <button type="button" class="button show-modal is-primary btn" data-target="create-database-modal">
              Create table　<i class="fas fa-database"></i>
            </button>
            </div>
              </div>
            </div>
          </div>

            <!-- Modalの処理 -->
            <div class="modal" id="my-modal">
                <div class="modal-background"></div>
                <div class="modal-card">
                    <header class="modal-card-head">
                        <p class="modal-card-title">確認</p>
                        <button class="delete" aria-label="close" data-target="my-modal"></button>
                    </header>
                    <%
                    String radioCheck = (String)request.getAttribute("radioCheck");
                    System.out.println("radioCheckは" + radioCheck);
                    //個人情報を生成して表示しているか？
                    if(session.getAttribute("DTOList") != null){
                        BusinessLogic logic = new BusinessLogic();
                        //ログインしているユーザーがデータベースが存在しているかを確認する
                        ArrayList<DataBaseDto> dto1 = logic.excuteSelectDataBase(userId);
                        //ユーザーのデータベース情報（ユーザー名、データベース名、パスワード）を確認する
                        UserDataBaseDto dto2 = logic.excuteSelectUserDataBase(userId);
	                    if (dto1 != null && dto2.getDataBaseId() != null && dto2.getDataBaseName() != null) {
	                        out.println("<section class=\"modal-card-body\">");
	                        if(DTOList.size() > 0){
	                            out.println("テーブルを選択してください<br>");
	                            for(int i = 0; i < dto1.size(); i++) {
	                                out.println("<input type=\"radio\" name=\"radiobutton\" value="+ dto1.get(i).getDatabase() +" id=" + i +"><label for="+ i +">" +dto1.get(i).getDatabase() + "</label>");
	                            }
	                            out.println("</section>");
	                            out.println("<footer class=\"modal-card-foot\">");
	                            out.println("<button type=\"submit\" name=\"json_btn\" class=\"button is-success\">はい</button>");
	                            out.println("<button type=\"submit\" name=\"submit\" class=\"button is-success\">再生成する</button>");
	                            out.println("<button class=\"button\">戻る</button>");
	                            out.println("</footer>");
	                        }else{
	                            out.println("データを生成してください");
	                        }
	                    }else{
	                        out.println("<section class=\"modal-card-body\">");
	                        out.println("使用可能なデータベース情報を入力してください");
	                        out.println("<div><a href=\"Mypage\">マイページへ</a></div>");
	                        out.println("</section>");
	                        out.println("<footer class=\"modal-card-foot\">");
	                        out.println("<button class=\"button\">戻る</button>");
	                        out.println("</footer>");
	                    }
                    }else{
                        out.println("<section class=\"modal-card-body\">");
                        out.println("データを生成してください");
                        out.println("</section>");
                        out.println("<footer class=\"modal-card-foot\">");
                        out.println("<button class=\"button\">戻る</button>");
                        out.println("</footer>");
                    }
                    %>
                </div>
            </div>
            <script type="text/javascript">
            for (const element of document.querySelectorAll('.modal .delete, .show-modal')) {
                element.addEventListener('click', e => {
                    const modalId = element.dataset.target;
                    const modal = document.getElementById(modalId);
                    modal.classList.toggle('is-active');
                });
            }
            </script>

            <!-- create_table_modal -->
			<div class="modal" id="create-database-modal">
			    <div class="modal-background"></div>
			    <div class="modal-card">
			        <header class="modal-card-head">
			            <p class="modal-card-title">テーブルを作成しますか？</p>
			            <button name="create_btn" class="delete" aria-label="close" data-target="create-database-modal"></button>
			        </header>
			        <section class="modal-card-body">
			          <%
			          StringBuilder create_sentence = new StringBuilder();
			          create_sentence.append("<div>CREATE TABLE tableName" + randomValue + "(<br>");
                      if(session.getAttribute("item_check") != null){
                          if(session.getAttribute("item_check").equals(true)){
                              create_sentence.append("item int(3),");
                          }
                      }
                      if(session.getAttribute("name_check") != null){
				          if(session.getAttribute("name_check").equals(true)){
				             create_sentence.append("name varchar(255),");
				          }
                      }
                      if(session.getAttribute("lastname_check") != null){
	                      if(session.getAttribute("lastname_check").equals(true)){
	                         create_sentence.append("lastname varchar(255),");
	                      }
                      }
                      if(session.getAttribute("firstname_check") != null){
	                      if(session.getAttribute("firstname_check").equals(true)){
	                          create_sentence.append("firstname varchar(255),");
	                      }
                      }
                      if(session.getAttribute("phone_check") != null){
	                      if(session.getAttribute("phone_check").equals(true)){
	                          create_sentence.append("phone_number varchar(255),");
	                      }
                      }
                      if(session.getAttribute("mail_check") != null){
	                      if(session.getAttribute("mail_check").equals(true)){
	                          create_sentence.append("mail_address varchar(255),");
	                      }
                      }
                      if(session.getAttribute("birthday_check") != null){
	                      if(session.getAttribute("birthday_check").equals(true)){
	                          create_sentence.append("birthday varchar(255),");
	                      }
                      }
			          if(session.getAttribute("age_check") != null){
	                      if(session.getAttribute("age_check").equals(true)){
	                          create_sentence.append("age varchar(255),");
	                      }
			          }
			          if(session.getAttribute("address_check") != null){
	                      if(session.getAttribute("address_check").equals(true)){
	                          create_sentence.append("address varchar(255),");
	                      }
			          }
			          if(session.getAttribute("gender_check") != null){
	                      if(session.getAttribute("gender_check").equals(true)){
	                          create_sentence.append("gender varchar(255),");
	                      }
			          }
			          if(session.getAttribute("password_check") != null){
	                      if(session.getAttribute("password_check").equals(true)){
	                          create_sentence.append("password varchar(255),");
	                      }
			          }
			          if(session.getAttribute("credit_check") != null){
	                      if(session.getAttribute("credit_check").equals(true)){
	                          create_sentence.append("credit varchar(255),");
	                      }
			          }
		              //create_sentenceの最後のカンマを削除する
		              if (create_sentence.length() > 0) {
		                  create_sentence.setLength(create_sentence.length() - 1);
		              }
		              create_sentence.append(")</div>");
                      out.println(create_sentence);
                      session.setAttribute("Create",create_sentence.toString());
			          %>
			        </section>
			        <footer class="modal-card-foot">
			        <button type="submit" name="create_btn" class="button is-danger">はい</button>
			        <button class="button">戻る</button>
			        </footer>
			    </div>
			</div>
          </div>
        </div>

        </form>

      <!-- 全選択の処理 -->
      <script type="text/javascript">
        function AllChecked(){
          var check =  document.form.aaa.checked;
          document.form.elements['item_check'].checked = check;
          document.form.elements['phone_check'].checked = check;
          document.form.elements['mail_check'].checked = check;
          document.form.elements['birthday_check'].checked = check;
          document.form.elements['age_check'].checked = check;
          document.form.elements['address_check'].checked = check;
          document.form.elements['gender_check'].checked = check;
          document.form.elements['credit_check'].checked = check;
          document.form.elements['password_check'].checked = check;
          document.form.elements['name_check'].checked = check;
          document.form.elements['lastname_check'].checked = check;
          document.form.elements['firstname_check'].checked = check;
        }
      </script>
      <%
      if(request.getAttribute("dberror") != null){
          out.println("<div class=\"has-text-danger\">");
          out.println(request.getAttribute("dberror"));
          out.println("</div>");
      }
      %>

      <!-- 情報出力 -->
      <div class="table-contents box">
      <table class="item_tbl" id="table">
      <thead>
        <tr class="item_tr">
          <%
          try {
              if (session.getAttribute("item_check").equals(true))  {
                  out.println("<td class='handle'>項番</td>");
              } else if(request.getAttribute("item_check").equals(true)) {
                  out.println("<td>項番</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("name_check").equals(true))  {
                  out.println("<td class='handle'>名前</td>");
              } else if(request.getAttribute("name_check").equals(true)) {
                  out.println("<td>名前</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("lastname_check").equals(true))  {
                  out.println("<td class='handle'>姓</td>");
              } else if(request.getAttribute("lastname_check").equals(true)) {
                  out.println("<td>姓</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("firstname_check").equals(true))  {
                  out.println("<td class='handle'>名</td>");
              } else if(request.getAttribute("firstname_check").equals(true)) {
                  out.println("<td>名</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("address_check").equals(true))  {
                  out.println("<td class='handle'>郵便番号</td><td class='handle'>都道府県</td><td class='handle'>市町村</td><td class='handle'></td>");
              } else if(request.getAttribute("address_check").equals(true)) {
                  out.println("<td>郵便番号</td><td>都道府県</td><td>市町村</td><td></td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("phone_check").equals(true))  {
                  out.println("<td class='handle'>電話番号</td>");
              } else if(request.getAttribute("phone_check").equals(true)) {
                  out.println("<td>電話番号</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("mail_check").equals(true))  {
                  out.println("<td class='handle'>メールアドレス</td>");
              } else if(request.getAttribute("mail_check").equals(true)) {
                  out.println("<td>メールアドレス</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("birthday_check").equals(true))  {
                  out.println("<td class='handle'>生年月日</td>");
              } else if(request.getAttribute("birthday_check").equals(true)) {
                  out.println("<td>生年月日</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("age_check").equals(true))  {
                  out.println("<td class='handle'>年齢</td>");
              } else if(request.getAttribute("age_check").equals(true)) {
                  out.println("<td>年齢</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("gender_check").equals(true))  {
                  out.println("<td class='handle'>性別</td>");
              } else if(request.getAttribute("gender_check").equals(true)) {
                  out.println("<td>性別</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("password_check").equals(true))  {
                  out.println("<td class='handle'>パスワード</td>");
              } else if(request.getAttribute("password_check").equals(true)) {
                  out.println("<td>パスワード</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          try {
              if (session.getAttribute("credit_check").equals(true))  {
                  out.println("<td class='handle'>クレジットカード</td>");
              } else if(request.getAttribute("credit_check").equals(true)) {
                  out.println("<td>クレジットカード</td>");
              } else {
              }
          } catch (NullPointerException e) {
          }
          %>
        </tr>
        </thead>
        <!-- ここからjsonの中身の表示 -->
        <tbody>
        <tr>
          <%
              try {
                  if (session.getAttribute("item_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getId() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("name_check").equals(true)) {
                      out.println("<td>");
		                if (session.getAttribute("DTOList") != null) {
		                    for(int i = 0; i < DTOList.size(); i++) {
		                        out.println("<p>" + DTOList.get(i).getName() + "</p>");
		                    }
		                 }
		              out.println("</td>");
              }
              } catch (NullPointerException e) {
              }
              try {
                  if (session.getAttribute("lastname_check").equals(true)) {
                      out.println("<td>");
                if (session.getAttribute("DTOList") != null) {
                    for(int i = 0; i < DTOList.size(); i++) {
                        out.println("<p>" + DTOList.get(i).getLastname() + "</p>");
                    }
                 }
                      out.println("</td>");
              }
              } catch (NullPointerException e) {
              }
              try {
                  if (session.getAttribute("firstname_check").equals(true)) {
                      out.println("<td>");
                if (session.getAttribute("DTOList") != null) {
                    for(int i = 0; i < DTOList.size(); i++) {
                        out.println("<p>" + DTOList.get(i).getFirstname() + "</p>");
                    }
                 }
                      out.println("</td>");
              }
              } catch (NullPointerException e) {
              }
              try {
                  if (session.getAttribute("address_check").equals(true)) {
	                if (session.getAttribute("DTOList") != null) {
	                    out.println("<td>");
	                    for(int i = 0; i < DTOList.size(); i++) {
	                        out.println("<p>" + DTOList.get(i).getZip() + "</p>");
	                    }
	                    out.println("</td>");
	                    out.println("<td>");
	                    for(int i = 0; i < DTOList.size(); i++) {
	                        out.println("<p>" + DTOList.get(i).getPref() + "</p>");
	                    }
	                    out.println("</td>");
	                    out.println("<td>");
	                    for(int i = 0; i < DTOList.size(); i++) {
	                        out.println("<p>" + DTOList.get(i).getCity() + "</p>");
	                    }
	                    out.println("</td>");
	                    out.println("<td>");
	                    for(int i = 0; i < DTOList.size(); i++) {
	                        out.println("<p>" + DTOList.get(i).getAddress() + "</p>");
	                    }
	                    out.println("</td>");
	                 }
	                }
              } catch (NullPointerException e) {
              }
              try {
                  if (session.getAttribute("phone_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getPhone() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("mail_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getEmail() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("birthday_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getBirthday() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("age_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getAge() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("gender_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getGender() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("password_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getPassword() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
              try {
                  if (session.getAttribute("credit_check").equals(true)) {
                      out.println("<td>");
                      if (session.getAttribute("DTOList") != null) {
                          for(int i = 0; i < DTOList.size(); i++) {
                          out.println("<p>" + DTOList.get(i).getCredit() + "</p>");
                      }
                      out.println("</td>");
                      }
                }
              } catch (NullPointerException e) {
                }
          %>
        </tr>
        </tbody>
      </table>

      </div>
    </div>
    <!-- 右のサイドバー -->
    <div class="column">
        </div>
  </div>
<footer class="footer">
  <div class="content has-text-centered">
    <p>
        copyright &copy; 2021 Generator. All rights reserved
    </p>
  </div>
</footer>

</body>
</html>