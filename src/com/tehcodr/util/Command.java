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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	
	/**
	 * Possible names for the command.
	 * 
	 * The names[0] is the command's main name, and what it's referred to.
	 * 
	 * Names past names[0] are aliases.
	 */
	String[] name();
	
	/**
	 * Possible flags for the command.
	 * 
	 * When called, flags automatically have the dash behind them.
	 */
	String[] flags() default {""};
	
	/**
	 * Text block for how to use a command.
	 * 
	 * Format variables -
	 * \$c - Command name (has forward slash)
	 * \$f [0 -1] - Flag name (Requires array number; supports slicing).
	 * 
	 * Better example -
	 * \$f [0 -1] - Lists all flags, from first to last
	 * \$f [1 4] - Lists the second to fifth flag. 
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
