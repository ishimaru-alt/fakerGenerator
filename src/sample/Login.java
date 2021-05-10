package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.BusinessLogic;
import model.UserInfoDto;

@WebServlet("/Login")

public class Login extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ.
     */
    public Login() {
        super();
    }

    String userId = "";
    String userPassword  ="";

    //エラーメッセージ
    String errormsg = "";

    private List<String> arrayList;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        resp.setContentType("text/html;charset=UTF-8");

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();

        UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
        if(userInfoDto != null) {
            resp.sendRedirect("JsonGet");
            return;
        }else {

        }

        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/LoginView.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        resp.setContentType("text/html;charset=UTF-8");

        resp.setCharacterEncoding("UTF-8");

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();

        if (req.getParameter("login") != null) {

            if(req.getParameter("USER_ID").equals("") || req.getParameter("USER_PASSWORD").equals("")) {
                errormsg = "ユーザーID(メールアドレス)またはパスワードを入力してください";
                session.setAttribute("errormsg", errormsg);
                resp.sendRedirect("Login");
                return;
            }else {
                userId = req.getParameter("USER_ID");
                userPassword = req.getParameter("USER_PASSWORD");
            }

            //入力したものからデータベースに合致するものを抽出
            BusinessLogic logic = new BusinessLogic();
            UserInfoDto dto = logic.excuteSelectUserInfo(userId,userPassword);

            List<UserInfoDto> userList = new ArrayList<UserInfoDto>();

            //ログイン成功か失敗か
            if(dto.getUserId() != null) {
                session.setAttribute("LOGIN_INFO", dto);
                dto.setUserId(userId);
                System.out.println(dto.getUserId());

                userList.add(dto);

                System.out.println("ユーザーID:" + userList.get(0).getUserId());




                resp.sendRedirect("JsonGet");
            }else {
                errormsg = "ユーザーIDまたはパスワードが違います";
                req.setAttribute("errormsg", errormsg);
                session.setAttribute("errormsg", errormsg);
                resp.sendRedirect("Login");
            }

        }


    }
}
