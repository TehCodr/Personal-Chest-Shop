/**  
 * Command.java - The framework for minecraft commands by TehCodr, loosely based off of sk89q's command system.  
 * @author  Omri Barak
 * @version 1.0
 * @since 2010-09-30
 */ 

package com.tehcodr.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	
	/**
	 * Possible names for the command.
	 * 
	 * The names[0] is the command's main name, and what it's referred to.
	 * Names past names[0] are aliases.
	 */
	String[] names();
	
	/**
	 * Possible flags for the command.
	 * 
	 * When called, flags automatically have the dash behind them,
	 * so don't put a dash behind a flag, unless you want it to start with '--'
	 */
	String[] flags() default {""};
	
	/**
	 * Text block for how to use a command.
	 * 
	 * Format variables:
	 * $c - Command name (has forward slash)
	 * $f [0 -1] - Flag name (Requires array number; supports slicing)
	 * 
	 * Better example:
	 * $f [0 -1] - Lists all flags, from first to last.
	 * $f [1 4] - Lists the second to fifth flag. 
	 */
	String usage() default "&FUsage: &7$c &4<flags> \n&FAvailable Flags:&4 $f[0 -1]";
	
	/**
	 * A short description of the command.
	 */
	String description() default "";
	
	/**
	 * Minimum number of flags when calling the command.
	 */
	int min() default 0;
	
	/**
	 * Maximum number of flags when calling the command. -1 means unlimited.
	 */
	int max() default -1;
}
