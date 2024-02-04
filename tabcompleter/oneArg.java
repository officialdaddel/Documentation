package net.daddel.hideandseek.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class HideAndSeekTabCompleter implements TabCompleter {
        List<String> arg = new ArrayList<String>();

    @Override
        public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
            if(arg.isEmpty()) {
                arg.add("start");
                arg.add("stop");
                arg.add("setSeekerWaitingRoom");
                arg.add("setSeekerSpawn");
                arg.add("setHiderSpawn");
                arg.add("setSpecSpawn");
                arg.add("setSpawn");
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
            return null;
        }
    }
