/**  
 * Command.java - The framework for minecraft commands by TehCodr, loosely based off of sk89q's command system.
 * @file   
 * @author  Omri Barak, TehCodr
 * @version 1.0
 * 
 * @section LICENSE
 * 
 * Permission is hereby granted, free of charge, to
 * any person obtaining a copy of this software and
 * associated documentation files (the "Software"),
 * to deal in the Software without restriction,
 * including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @section DESCRIPTION
 * 
 * The manager for minecraft commands, loosely based off of sk89q's command system.
 */  

package com.tehcodr.util;

import java.util.List;

import com.tehcodr.util.CommandManager;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.permissions.Permissible;

public abstract class Command {
	
	/**
	 * The CommandManager that events are registered to. 
	 */
	CommandManager parent;
	
	/**
	 * Possible names for the command - name[0] is the main name of the command.
	 * Names past names[0] are aliases.
	 */
	List<String> name;
	
	/**
	 * Possible flags for the command.
	 * When called, flags already have the dash behind them.
	 */
	List<String> flags;
	
	/**
	 * Text block for how to use a command.
	 * Format variables -
	 * \$c - Command name (has forward slash)
	 * \$f[0] || \$f - Flag name (Index number (Array) optional).
	 */
	String usage;
	
	/**
	 * A short description of the command.
	 */
	String description;
	
	/**
	 * Minimum number of flags when calling the command.
	 */
	int min;
	
	/**
	 * Maximum number of flags when calling the command. -1 means unlimited.
	 */
	int max;
	
	/**
	 * Parses a usage string for commands.
	 * @param input Get input string
	 * @return str String after parsing
	 */
	final void parseUsageString() {
		/* Put flags list into an ordered list, separated by commas */
		String FlagList = "";
		for (int i=0; i < flags.size(); i++) {
			FlagList += flags.get(i).concat(", ");
		}
		usage.replaceAll("$f", FlagList);
		/* Flag index indexing */
		for(int i=0; i < flags.size(); i++) {
			usage.replaceAll("$f["+ i + "]", flags.get(i));
		}
		usage.replaceAll("$c", name.get(0));
	}
	
	/**
	 * Function to call when the slash command is typed.
	 */
	public abstract boolean execute(String flags);
	
	/**
	 * Constructor class
	 * @param parent       The CommmandManager instance used to initialize the command.
	 * @param name[]       REQUIRED - Possible names for the command.
	 * @param flags[]      Possible flags for the command.
	 * @param usage        Text block for how to use a command.
	 * @param description  A short description of the command.
	 * @param min          Minimum number of flags when calling the command.
	 * @param max          Maximum number of flags when calling the command.
	 * @return true if name[0] is filled out, otherwise false.
	 */
	public Command(CommandManager parent, List<String> name, List<String> flags, String usage, String description, int min, int max) {
		assert(name.indexOf("") == -1);
		this.name = name;
		this.flags = flags;
		if(usage == "") usage = "Usage: $c <flags> \nAvailable Flags: $f[0 -1]";
		this.usage = usage;
		this.parseUsageString();
		
		if (description == "") description = "/" + name.get(0);
		this.description = description;
		
		this.min = min;
		this.max = max;
		
		parent.register(this);
	}
	
	/**
	 * Gets the command's name.
	 * @return name[0]
	 */
	public final String getName() {
		return name.get(0);
	}
	
	/**
	 * Gets the name of the command, at a certain point in the array.
	 * @param i
	 * @return name[i]
	 */
	public final String getName(int i) {
		return name.get(i);
	}
	
	/**
	 * Gets all of the names in the array name[].
	 * @return name[]
	 */
	public final List<String> getNames() {
		return name;
	}
	
	/**
	 * Gets the names in an array, from a beginning point/ending point, all-inclusive.
	 * @param index array item number
	 * @param first beginning index or ending index.
	 * @return name.subList() list of names, or all if something happens. 
	 */
	public final List<String> getNames(int index, boolean first) {
		if(first) {
			return name.subList(index, name.size() +1);
		}
		else if(!first) {
			return name.subList(0, index + 1);
		}
		return name;
	}
	
	/**
	 * Gets the names in an array, from a beginning point, to an end point.
	 * @param beginIndex
	 * @param endIndex
	 * @return name.subList(...) list of names starting from the beginIndex, and ending at the endIndex. 
	 */
	public final List<String> getNames(int beginIndex, int endIndex) {
		assert(name.size() >= beginIndex && name.size() >= endIndex && endIndex >= beginIndex);
		return name.subList(beginIndex, endIndex);
	}
	
	/**
	 * Gets the name of the flag, at a certain point in the array.
	 * @param i
	 * @return flags.get(i)
	 */
	public final String getFlag(int i) {
		return flags.get(i);
	}
	
	/**
	 * Gets all of the flags in the list flags.
	 * @return flags
	 */
	public final List<String> getFlags() {
		return flags;
	}
	
	/**
	 * Gets the flags in an array, from a beginning point/ending point, all-inclusive.
	 * @param index array item number
	 * @param first beginning index or ending index.
	 * @return flags.subList(...) list of flags, or all if something happens. 
	 */
	public final List<String> getFlags(int index, boolean first) {
		if(first) {
			return flags.subList(index, flags.size() +1);
		}
		else if (!first) {
			return flags.subList(0, index + 1);
		}
		return flags;
	}
	
	/**
	 * Gets the names in an array, from a beginning point, to an end point.
	 * @param beginIndex
	 * @param endIndex
	 * @return flags.subList(...) list of names starting from the beginIndex, and ending at the endIndex. 
	 */
	public final List<String> getFlags(int beginIndex, int endIndex) {
		assert(flags.size() >= beginIndex && flags.size() >= endIndex && endIndex >= beginIndex);
		return flags.subList(beginIndex, endIndex);
	}
}
