package MainTest;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import AssetTest.DBConnectionMgr;
 



public class Login {
//    public static void main(String[] args) {
//         
//        boolean test = loginTest("test", "1234");
//         
//        System.out.println("로그인 결과 :"+test);
//    }
 
    public static boolean loginTest(String ID, String PW) {
        boolean flag = false;
 
        DBConnectionMgr pool = DBConnectionMgr.getInstance();
 
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String getPass = null;
 
        try {
            con = pool.getConnection();
 
            // 문장생성
            sql = "select PW from user where ID=?";
 
            // 문장연결, 열차준비
            pstmt = con.prepareStatement(sql);
 
            // 빈칸채워주기
            pstmt.setString(1, ID);
 
            // 실행, 열차출바알~!
            rs = pstmt.executeQuery();
 
            if (rs.next()) {
                // 패스워드를 읽어온다.
                getPass = rs.getString("PW");
 
                // 데이터베이스에서 읽어온 문자열과 사용자가 입력한 비밀번호가 같을 경우에는
                // 참을 반환을 하겠죠?
                if (getPass.equals(PW)) {
                    System.out.println("받아온 비밀번호 :" + getPass);
                    flag = true;
                }
            }
 
        } catch (Exception e) {
            //원래 예외처리는 크게 잡으면 안되고, 따로 처리가 되어야 합니다.
            e.printStackTrace();
 
        } finally {
 
            // 자원반납
            pool.freeConnection(con, pstmt, rs);
        }
 
        // 결과값 반납
        return flag;
    }
}