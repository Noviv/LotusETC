package lotus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LotusClient {

    private Socket exch;
    private BufferedReader from_exch;
    private PrintWriter to_exch;

    public LotusClient() {
        try {
            exch = new Socket("test-exch-lotus", 20000);
            from_exch = new BufferedReader(new InputStreamReader(exch.getInputStream()));
            to_exch = new PrintWriter(exch.getOutputStream(), true);
        } catch (Exception e) {
            System.err.println("Error in LotusClient init: " + e.getMessage());
            System.exit(0);
        }

        try {
            to_exch.println("HELLO LOTUS");
            String reply = from_exch.readLine().trim();
            System.out.println("Exchange init:");
            System.out.println("\t" + reply);
        } catch (Exception e) {
            System.err.println("Error in LotusClient handshake: " + e.getMessage());
            System.exit(0);
        }
    }

    public void command(String cmd) {
        try {
            to_exch.println(cmd);
            String reply = from_exch.readLine().trim();
            System.out.println("Sent:" + cmd);
            System.out.println("Exchange reply: " + reply);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error in test: " + e.getMessage());
        }
    }

    public void test() {
        int i = 0;
        while (i++ < 10) {
            try {
                System.out.println(from_exch.readLine().trim());
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        LotusClient client = new LotusClient();
        client.test();
    }
}
