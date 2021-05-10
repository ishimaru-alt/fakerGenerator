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

@WebServlet("/Mypage")

public class MyPage extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();

        UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
        if(userInfoDto == null) {
            resp.sendRedirect("Login");
            return;
        }

        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/MyPageView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();
        UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");

        if(userInfoDto == null) {
            resp.sendRedirect("Login");
            return;
        }else {

        }
        String username = userInfoDto.getUserId();

        //入力したものからデータベースに合致するものを抽出
        BusinessLogic logic = new BusinessLogic();
        UserDataBaseDto dto = logic.excuteSelectUserDataBase(username);

        if (dto.getDataBaseId() != null) {
            session.setAttribute("DATABASE_INFO", dto);
        }

        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/MyPageView.jsp");
        dispatcher.forward(req, resp);
    }

}
