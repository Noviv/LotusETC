package lotus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import lotus.types.Bond;
import lotus.types.Symbol;
import lotus.types.Valbz;
import lotus.util.DirType;
import lotus.util.LotusUtil;

public class LotusClient {

    //networking variables
    private Socket exch;
    private BufferedReader from_exch;
    private PrintWriter to_exch;

    //market data
    private int orderID;
    private ArrayList<Symbol> activeSymbols;

    public LotusClient() throws Exception {
        exch = new Socket("test-exch-lotus", 20000);
//        exch = new Socket("production", 20000);
        from_exch = new BufferedReader(new InputStreamReader(exch.getInputStream()));
        to_exch = new PrintWriter(exch.getOutputStream(), true);

        orderID = 0;
        activeSymbols = new ArrayList<>();
        activeSymbols.add(Bond.getInstance());

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
            while (true) {
                process(from_exch.readLine().trim());

                for (Symbol s : activeSymbols) {
                    s.calc(this);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in LotusClient.run: " + e.getMessage());
        }
    }

    private void process(String line) {
        String[] comps = line.split(" ");

        Symbol s = null;
        boolean book = false;

        if (comps[1].equals("BOND")) {
            s = Bond.getInstance();
        } else if (comps[1].equals("VALBZ")) {
            s = Valbz.getInstance();
        }

        switch (comps[0]) {
            case "BOOK":
                Bond.getInstance().appendBook(comps);
                break;
            case "TRADE":
                Bond.getInstance().appendTrade(comps[2], comps[3]);
                break;
        }

        process0(s, book, comps);
    }

    private void process0(Symbol s, boolean book, String[] comps) {
        if (book) {
            s.appendBook(comps);
        } else {
            s.appendTrade(comps[2], comps[3]);
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
