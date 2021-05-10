package sample;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import login.BusinessLogic;
import model.UserInfoDto;

@WebServlet("/Register")

public class Register extends HttpServlet{
    private static final long serialVersionUID = 1L;

    String userId = "";
    String userName = "";
    String userPassword  ="";

    //エラーメッセージ
    String errormsg = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/RegisterView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        resp.setContentType("text/html:charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();

        if (req.getParameter("Register") != null) {
            //未入力チェック
            if(req.getParameter("USER_ID").equals("") || req.getParameter("USER_NAME").equals("") ||req.getParameter("USER_PASSWORD").equals("")){
                errormsg = "ユーザーID(メールアドレス)、名前、パスワードを入力してください";
                session.setAttribute("errormsg", errormsg);
                resp.sendRedirect("Register");
                return;
            }else {
                userId = req.getParameter("USER_ID");
                userName = req.getParameter("USER_NAME");
                userPassword = req.getParameter("USER_PASSWORD");

                //パスワード入力チェック
                Pattern p = Pattern.compile("^$|^(?=.*[a-z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])[!-~]*");
                Matcher m = p.matcher(userPassword);
                Boolean result = m.matches();
                if (!result) {
                    errormsg = "パスワードは小文字と数字と記号（!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~）を使用した８文字以上で入力してください";
                    req.setAttribute("errormsg", errormsg);
                    session.setAttribute("errormsg", errormsg);
                    resp.sendRedirect("Register");
                    return;
                }
                //ストレッチング処理
                for (int i = 0; i < 10; i++) {
                    // パスワードをハッシュ値に変換
                    userPassword = BCrypt.hashpw(req.getParameter("USER_PASSWORD"), BCrypt.gensalt());
                }

                UserInfoDto dto = new UserInfoDto();
                dto.setUserId(userId);
                dto.setUserName(userName);
                dto.setUserPassword(userPassword);

                BusinessLogic logic = new BusinessLogic();
                boolean succesInsert = logic.excuteInsertUserInfo(dto);

                boolean succesInsertDB = logic.excuteDatabaseInsert(dto);

                if(succesInsertDB) {
                    System.out.println("user情報成功");
                }else {
                    System.out.println("失敗");
                }

                //DB操作成功か失敗か
                if (succesInsert) {
                    resp.sendRedirect("Login");
                }else {
                    resp.sendRedirect("Register");
                }
            }
        }




    }
}
