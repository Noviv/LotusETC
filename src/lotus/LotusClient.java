package lotus;

import lotus.algo.BondAlgo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import lotus.types.Bond;
import lotus.types.Symbol;
import lotus.util.DirType;
import lotus.util.LotusUtil;

public class LotusClient {

    //networking variables
    private Socket exch;
    private BufferedReader from_exch;
    private PrintWriter to_exch;

    //market data
    private int orderID;
    private Bond bondData;

    private BondAlgo algo;

    public LotusClient() throws Exception {
        exch = new Socket("test-exch-lotus", 20000);
//        exch = new Socket("production", 20000);
        from_exch = new BufferedReader(new InputStreamReader(exch.getInputStream()));
        to_exch = new PrintWriter(exch.getOutputStream(), true);

        orderID = 0;
        bondData = Bond.getInstance();
        algo = new BondAlgo();

        command("HELLO", "LOTUS");
    }

    public final void add(Symbol symb, DirType dir, int price, int size) {
        orderID++;
        command("ADD", orderID, symb.getSymbol(), dir, price, size);
    }

    public final void command(Object... cmdComps) {
        String cmd = LotusUtil.buildCommand(cmdComps);

        try {
            to_exch.println(cmd);
            String reply = from_exch.readLine().trim();
            System.out.println("Sent: " + cmd);
            System.out.println("Exchange reply: " + reply);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error in LotusClient.command: " + e.getMessage());
        }
    }

    public final void run() {
        try {
            String line;
            String[] comps;
            while (true) {
                line = from_exch.readLine().trim();
                comps = line.split(" ");
                if (comps[1].equals("BOND")) {
                    switch (comps[0]) {
                        case "BOOK":
                            bondData.appendBook(comps);
                            break;
                        case "TRADE":
                            bondData.appendTrade(comps[2], comps[3]);
                            break;
                    }
                }

                bondData.calc(this);
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
