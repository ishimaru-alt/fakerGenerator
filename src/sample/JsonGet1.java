package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import dao.CreateTableDao;
import login.BusinessLogic;
import model.JsonDto;
import model.UserInfoDto;

@WebServlet("/JsonGet")

public class JsonGet1 extends HttpServlet{
    private static final long serialVersionUID = 1L;

    //private static ArrayList<DataBaseDto> bookList = null;

    public static String radioCheck = null;

    //項番
    String item = "";
    int item_num;
    //件数
    String cord ;
    int cord_num;

    //生年月日
    String date;
    int date_num;

    //電話番号
    String phone;
    int phone_num;

    //メールアドレス
    String mail;
    int mail_num;

    //性別
    String gender;
    int gender_num;

    //年齢
    String age1;
    int age_num1;
    String age2;
    int age_num2;

    //JSON変換用のクラス
    ObjectMapper mapper = new ObjectMapper();

    JsonDto dto = new JsonDto();

    ArrayNode items = mapper.createArrayNode();

    String json = null;

    //空部屋DTOList作成
    List<JsonDto> DTOList = new ArrayList<JsonDto>();

    //データベースのエラー文
    String dberror;

    //create文の作成
    String create_sentence = "";

    String userId;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        //DTOList = new ArrayList<JsonDto>();
        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/JsonGetView.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ

        // HttpSessionインスタンスの取得
        HttpSession session = req.getSession();

        if(req.getParameter("create_btn") != null) {
            System.out.println("押したよ");
            Object obj = session.getAttribute("Create");
            create_sentence = obj.toString().replace("<div>", "").replace("</div>", "").replace("<br>", "");
            System.out.println(create_sentence);
            UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
            userId = userInfoDto.getUserId();
            CreateTableDao createTableDao = new CreateTableDao(create_sentence,userId);

        }

        if (req.getParameter("json_btn") != null) {
            radioCheck = req.getParameter("radiobutton");
            req.setAttribute("radioCheck", radioCheck);

            UserInfoDto userInfoDto = (UserInfoDto)session.getAttribute("LOGIN_INFO");
            userId = userInfoDto.getUserId();

            BusinessLogic logic = new BusinessLogic();
            boolean succesInsertDummy = logic.excuteInsertDummyData(DTOList,userId);

            //DB操作成功か失敗か
            if (succesInsertDummy == true) {
                System.out.println("ヽ(*´∀｀)ノ");
                dberror = "データベースの登録に成功しました。";
            }else {
                System.out.println("(￣_￣|||) どよ～ん");
                dberror = "データベースの登録に失敗しました。";
            }
            req.setAttribute("dberror", dberror);
        }

        //個人情報生成ボタンを押した場合
        if (req.getParameter("submit") != null | req.getParameter("reforming") != null) {

            DTOList = new ArrayList<JsonDto>();

            cord = req.getParameter("number");
            cord_num = Integer.parseInt(cord);

            date = req.getParameter("date_format");
            date_num =  Integer.parseInt(date);

            phone = req.getParameter("phone");
            phone_num =  Integer.parseInt(phone);

            mail = req.getParameter("mail_format");
            mail_num =  Integer.parseInt(mail);

            gender = req.getParameter("gender_format");
            gender_num =  Integer.parseInt(gender);

            age1 = req.getParameter("age1");
            age_num1 =  Integer.parseInt(age1);

            age2 = req.getParameter("age2");
            age_num2 =  Integer.parseInt(age2);

            //チェックしているか
            boolean item_check = req.getParameter("item_check") != null;
            req.setAttribute("item_check", item_check);

            boolean name_check = req.getParameter("name_check") != null;
            req.setAttribute("name_check", name_check);

            boolean lastname_check = req.getParameter("lastname_check") != null;
            req.setAttribute("lastname_check", lastname_check);

            boolean firstname_check = req.getParameter("firstname_check") != null;
            req.setAttribute("firstname_check", firstname_check);

            boolean phone_check = req.getParameter("phone_check") != null;
            req.setAttribute("phone_check", phone_check);

            boolean mail_check = req.getParameter("mail_check") != null;
            req.setAttribute("mail_check", mail_check);

            boolean birthday_check = req.getParameter("birthday_check") != null;
            req.setAttribute("birthday_check", birthday_check);

            boolean age_check = req.getParameter("age_check") != null;
            req.setAttribute("age_check", age_check);

            boolean address_check = req.getParameter("address_check") != null;
            req.setAttribute("address_check", address_check);

            boolean gender_check = req.getParameter("gender_check") != null;
            req.setAttribute("gender_check", gender_check);

            boolean password_check = req.getParameter("password_check") != null;
            req.setAttribute("password_check", password_check);

            boolean credit_check = req.getParameter("credit_check") != null;
            req.setAttribute("credit_check", credit_check);

            // セッションスコープにインスタンスを保存
            session.setAttribute("item_check", item_check);
            session.setAttribute("name_check", name_check);
            session.setAttribute("lastname_check", lastname_check);
            session.setAttribute("firstname_check", firstname_check);
            session.setAttribute("address_check", address_check);
            session.setAttribute("phone_check", phone_check);
            session.setAttribute("mail_check", mail_check);
            session.setAttribute("birthday_check", birthday_check);
            session.setAttribute("age_check", age_check);
            session.setAttribute("gender_check", gender_check);
            session.setAttribute("password_check", password_check);
            session.setAttribute("credit_check", credit_check);

            //URLの設定
            String strUrl = "http://localhost/faker/faker1.php?num=" + cord_num + "&date_form=" + date_num + "&gender=" + gender_num
                    + "&age1=" + age1 + "&age2=" + age2;
            System.out.println(strUrl);

            HttpURLConnection  urlConn = null;
            InputStream in = null;
            BufferedReader reader = null;

        try {
            //接続するURLを指定する
            URL url = new URL(strUrl);

            //コネクションを取得する
            urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setRequestMethod("GET");

            urlConn.connect();

            int status = urlConn.getResponseCode();

            System.out.println("HTTPステータス:" + status);
            if (status == HttpURLConnection.HTTP_OK) {

                in = urlConn.getInputStream();

                reader = new BufferedReader(new InputStreamReader(in));

                StringBuilder output = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
                //Stringに変換
                String output_string = output.toString();

                JsonNode node = mapper.readValue(output_string, JsonNode.class);

                ArrayNode items = mapper.createArrayNode();

                for (int i = 0; i < cord_num; i++) {
                    JsonDto dummydto = new JsonDto();
                    if(item_check == true) {
                    String id = node.get(i).get("id").toString();
                    //jsonに再変換
                    json = mapper.writeValueAsString(id);
                    dummydto.setId(trimDoubleQuot(json));
                    }
                    if(name_check == true) {
                    String name = node.get(i).get("name").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(name));
                    dummydto.setName(trimDoubleQuot(json));
                    }
                    if(lastname_check == true) {
                    String lastname = node.get(i).get("lastname").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(lastname));
                    dummydto.setLastname(trimDoubleQuot(json));
                    }
                    if(firstname_check == true) {
                    String firstname = node.get(i).get("firstname").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(firstname));
                    dummydto.setFirstname(trimDoubleQuot(json));
                    }
                    if(address_check == true) {
                    String zip = node.get(i).get("zip").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(zip));
                    dummydto.setZip(trimDoubleQuot(json));

                    String pref = node.get(i).get("pref").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(pref));
                    dummydto.setPref(trimDoubleQuot(json));

                    String city = node.get(i).get("city").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(city));
                    dummydto.setCity(trimDoubleQuot(json));

                    String address = node.get(i).get("address").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(address));
                    dummydto.setAddress(trimDoubleQuot(json));
                    }
                    if(phone_check == true) {
                        if (phone_num == 1) {
                            String phone = node.get(i).get("phone").toString();
                            json = mapper.writeValueAsString(trimDoubleQuot(phone));
                            dummydto.setPhone(trimDoubleQuot(json));
                        }else if (phone_num == 2) {
                            String cellphoneNumber = node.get(i).get("cellphoneNumber").toString();
                            json = mapper.writeValueAsString(trimDoubleQuot(cellphoneNumber));
                            dummydto.setPhone(trimDoubleQuot(json));
                        }
                    }
                    if(mail_check == true) {
                        if (mail_num == 1) {
                            String email = node.get(i).get("email").toString();
                            json = mapper.writeValueAsString(trimDoubleQuot(email));
                            dummydto.setEmail(trimDoubleQuot(json));
                        }else if (mail_num == 2) {
                            String freeEmail = node.get(i).get("freeEmail").toString();
                            json = mapper.writeValueAsString(trimDoubleQuot(freeEmail));
                            dummydto.setEmail(trimDoubleQuot(json));
                        }else if (mail_num == 3) {
                            String companyEmail = node.get(i).get("companyEmail").toString();
                            json = mapper.writeValueAsString(trimDoubleQuot(companyEmail));
                            dummydto.setEmail(trimDoubleQuot(json));
                        }
                    }
                    if(birthday_check == true) {
                    String birthday = node.get(i).get("birthdate").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(birthday));
                    dummydto.setBirthday(trimDoubleQuot(json));
                    }
                    if(age_check == true) {
                    String age = node.get(i).get("age").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(age));
                    dummydto.setAge(trimDoubleQuot(json));
                    }
                    if(gender_check == true) {
                    String gender = node.get(i).get("gender").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(gender));
                    dummydto.setGender(trimDoubleQuot(json));
                    }
                    if(password_check == true) {
                    String password = node.get(i).get("password").toString();
                    password = password.replace(",", "i").replace("\"\\", "i").replace("'", "i");
                    json = mapper.writeValueAsString(trimDoubleQuot(password));
                    dummydto.setPassword(trimDoubleQuot(json));
                    }
                    if(credit_check == true) {
                    String credit = node.get(i).get("credit").toString();
                    json = mapper.writeValueAsString(trimDoubleQuot(credit));
                    dummydto.setCredit(trimDoubleQuot(json));
                    }

                    DTOList.add(dummydto);

                    json = mapper.writeValueAsString(dto);
                    items.add(json);
                }

                session.setAttribute("DTOList", DTOList);

              }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (urlConn != null) {
                    urlConn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }

        //フォワード
        RequestDispatcher dispatcher =
           req.getRequestDispatcher("/WEB-INF/sample/view/JsonGetView.jsp");
        dispatcher.forward(req, resp);


    }

    public static String trimDoubleQuot(String str) {
        char c = '"';
        if(str.charAt(0) == c && str.charAt(str.length()-1) == c) {
          return str.substring(1, str.length()-1);
        }else {
          return str;
        }
      }

}


