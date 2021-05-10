package sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;

@WebServlet("/Logout")

public class Logout extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();

        UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
        if(userInfoDto != null) {
            System.out.println("ログイン情報がある");
            session.invalidate();
            resp.sendRedirect("Login");
        }else {
            System.out.println("ログイン情報がない");
        }

    }

}
