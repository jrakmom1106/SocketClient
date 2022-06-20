import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    final static String SERVER_IP = "127.0.0.1";
    final static int SERVER_PORT = 9999;
    static String MESSAGE_TO_SERVER = "Hi, Server";


    public static void main(String... args){

        Socket socket = null;

        Scanner scanner = new Scanner(System.in);


        try{
            while(true){

            socket = new Socket(SERVER_IP,SERVER_PORT);
            System.out.println("socket connect");


            MESSAGE_TO_SERVER = scanner.nextLine();

            OutputStream os = socket.getOutputStream();

            InputStream is = socket.getInputStream();

            //서버에 전송
            os.write( MESSAGE_TO_SERVER.getBytes());
            os.flush();

            //전송후 받은값
            /*byte[] data = new byte[16];
            int n = is.read(data);
            final String resultFromServer = new String(data,0,n);

            System.out.println(resultFromServer);*/


            BufferedReader mIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String recive = mIn.readLine();
            System.out.println("result");
            System.out.println(recive);
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}