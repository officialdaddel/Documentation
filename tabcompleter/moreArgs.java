package play.mcdev.tv.ultimatefeatues.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class UFTabcompleter implements TabCompleter {
    List<String> arg = new ArrayList<>();
    List<String> arg2 = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(arg.isEmpty()) {
            arg.add("changelog");
            arg.add("help");
            arg.add("admin");
            arg.add("plugin");
        }

        if(arg2.isEmpty()){
            arg2.add("1.2.1");
            arg2.add("history");
        }

        List<String> result = new ArrayList<String>();
        if(args.length == 1) {
            for(String a : arg) {
                if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }

        List<String> result2 = new ArrayList<>();
        if(args.length == 2) {
            if (args[0].equalsIgnoreCase("changelog")) {
                for (String a2 : arg2) {
                    if (a2.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result2.add(a2);
                    }
                }
                return result2;
            }
        }
        return null;
    }
}

