// ------------------------------------------------------------
//   ParamAgent:   An Agent receiving parameters  
//                 how arguments could be given to an agent created from command line
//
//   Usage:    % javac ParamAgent.java
//             % java jade.Boot  fred:ParamAgent(3 "Allo there")
//
// ... on UNIX, the agent specifier and arguments must be quoted:
//
//             % java jade.Boot 'fred:ParamAgent(3 "Allo there")'
// ------------------------------------------------------------

 import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

 public class ParamAgent extends Agent 
 { 
	protected void setup() 
	{ 
            //arguments are passed as Objects: simple types must be converted to Strings or Wrapper classes.
		Object[] args = getArguments();
		String s;
		if (args != null) {
			for (int i = 0; i<args.length; i++) {
				s = (String) args[i];
				System.out.println("p" + i + ": " + s);
			}
			
			int i = Integer.parseInt( (String) args[0] );
			s     = (String) args[1];
			
			System.out.println("i*i= " + i*i);
			//System.exit(1);
		}
            
            
	}
 }

