package lotus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import lotus.types.Bond;

public class LotusClient {

    //networking variables
    private Socket exch;
    private BufferedReader from_exch;
    private PrintWriter to_exch;

    //market data
    private Bond bondData;

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

        bondData = Bond.getInstance();
    }

    public void command(String cmd) {
        try {
            to_exch.println(cmd);
            String reply = from_exch.readLine().trim();
            System.out.println("Sent:" + cmd);
            System.out.println("Exchange reply: " + reply);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error in LotusClient.command: " + e.getMessage());
        }
    }

    public void run() {
        long lastMillis = System.currentTimeMillis();
        boolean traded = false;

        try {
            String line;
            String comps[];
            while (true) {
                line = from_exch.readLine().trim();
                comps = line.split(" ");
                if (comps[1].equals("BOND")) {
                    if (comps[0].equals("BOOK")) {
                        bondData.appendBook(comps);
                    } else if (comps[0].equals("TRADE")) {
                        bondData.appendTrade(comps[2], comps[3]);
                        traded = true;
                    }
                }

                if (System.currentTimeMillis() - lastMillis > 5 * 1000 && traded) {
                    traded = false;
                    lastMillis = System.currentTimeMillis();
                    System.out.println("running algorithm on this data:");
                    Bond.getInstance().print();
                }
            }
        } catch (Exception e) {
            System.out.println("Error in LotusClient.run: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            try {
                LotusClient client = new LotusClient();
                client.run();
            } catch (Exception e) {
                System.out.println("LotusClient crashed! Restarting...");
            }
        }
    }
}
