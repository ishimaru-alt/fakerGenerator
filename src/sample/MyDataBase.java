package sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.BusinessLogic;
import model.UserDataBaseDto;
import model.UserInfoDto;

@WebServlet("/MyDataBase")

public class MyDataBase extends HttpServlet{
    private static final long serialVersionUID = 1L;

    String user_name;
    String user_pass;
    String db_name;

    String errormsg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/MyDataBaseView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();
        UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
        String username = userInfoDto.getUserId();

        if (req.getParameter("dbchange_btn") != null) {
            System.out.println("押したよ");
            user_name = req.getParameter("user_name");
            user_pass = req.getParameter("user_pass");
            db_name = req.getParameter("db_name");

            UserDataBaseDto userDataBaseDto = new UserDataBaseDto();
            userDataBaseDto.setDataBaseId(user_name);
            userDataBaseDto.setDataBasePass(user_pass);
            userDataBaseDto.setDataBaseName(db_name);
            userDataBaseDto.setUserId(username);

            BusinessLogic logic = new BusinessLogic();
            boolean succesUpdate = logic.excuteDatabaseUpdate(userDataBaseDto);

            //DB操作成功か失敗か
            if (succesUpdate) {
                System.out.println("成功");
                errormsg = "データベース情報更新に成功しました。";
                req.setAttribute("errormsg", errormsg);
                session.setAttribute("errormsg", errormsg);
            }else {
                System.out.println("失敗");
            }

        }

        //入力したものからデータベースに合致するものを抽出
        BusinessLogic logic = new BusinessLogic();
        UserDataBaseDto dto = logic.excuteSelectUserDataBase(username);

        if (dto.getDataBaseId() != null) {
            session.setAttribute("DATABASE_INFO", dto);
        }

        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/MyDataBaseView.jsp");
        dispatcher.forward(req, resp);
    }

}