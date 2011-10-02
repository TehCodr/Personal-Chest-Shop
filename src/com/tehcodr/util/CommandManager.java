/**  
 * CommandManager.java - The handler for minecraft commands, loosely based off of sk89q's command system.
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
 * The handler for minecraft commands, loosely based off of sk89q's command system.
 */ 

package com.tehcodr.util;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;
import org.bukkit.command.*;

import com.tehcodr.util.Command;

public class CommandManager implements CommandExecutor {
	
	/**
	 * The manager for player. This sends/receives data to/from the player.
	 */
	Player player;

	/**
	 * The array that contains all of the commands.
	 */
	com.tehcodr.util.Command commands[];
	
	/**
	 * The number of commands in the array, 0 inclusive.
	 */
	int numCommands = 0;
	
	void register(com.tehcodr.util.Command command) {
		commands[numCommands + 1] = command;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
			if (sender instanceof Player)
				this.player = (Player)sender;
			else
				return false;
		return true;
	}
	
}
