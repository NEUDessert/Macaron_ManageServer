import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by killeryuan on 2016/9/10.
 */
public class Test {
    public static void main (String args[])throws Exception{
//
//        Connection conn = DB.getConn("jdbc:mysql://115.159.205.225:3306/macaroon", "yuan","424200");
//        Statement statement = conn.createStatement();
//        String sql = "INSERT user_info (user_id,username,password,email,phone,devnum) VALUES (1, 'killeryuan', 'wang200424', '497112006@qq.com','15734003447',3 )";
//        statement.execute(sql);
//        ResultSet resultSet = statement.getResultSet();
//        Integer integer = new Integer(55);
//        String a = integer.toString()+"sfsea";
//        System.out.println(a);
//        Socket socket = new Socket("192.168.50.177",2333);
//        System.out.println(222);
//        InputStream inputStream = socket.getInputStream();
//        int b ;
//        while ((b=inputStream.read())!=-1){
//            System.out.println(b);
//        }
//        System.out.println(br.readLine());

        ServerSocket serverSocket = new ServerSocket(10001);
        Socket s = serverSocket.accept();
        System.out.println();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(br.readLine());
    }
}
