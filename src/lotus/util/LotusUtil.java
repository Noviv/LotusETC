package lotus.util;

import java.util.ArrayList;
import java.util.Map;

public class LotusUtil {

    public static <K, V> ArrayList<K> cvtMapToKeyArray(Map<K, V> map) {
        ArrayList<K> ret = new ArrayList<>();
        ret.addAll(map.keySet());
        return ret;
    }

    public static String buildCommand(Object... args) {
        String cmd = "";
        for (int i = 0; i < args.length - 1; i++) {
            cmd += args[i].toString() + " ";
        }
        cmd += args[args.length - 1].toString();

        return cmd;
    }

    private LotusUtil() {
    }
}
