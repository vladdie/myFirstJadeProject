
/*****************************************************************

	createdByAgent.java:    Program which creates another Agent and sends 
	------------     it some messages
	

*****************************************************************/


import jade.core.Agent;
import jade.core.behaviours.*;

import jade.core.AID;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import jade.lang.acl.*;


public class createdByAgent extends Agent 
{
	
	//String name = "Alice" ;//local name of the newly generated agent
        //use name to create agent ID
	AID newAID1 = new AID( "Alice", AID.ISLOCALNAME );

    protected void setup() 
    {
    	AgentContainer c = getContainerController();
    	try {
            //main agent creates a pong agent in its own container
            //an array of arguments can be provided for new agents in the third parameter of createNewAgent
    		AgentController a = c.createNewAgent( "Alice", "Receiver", null );
    		a.start();
    		System.out.println("+++ Created: " + newAID1);
    	}
    	catch (Exception e){}
    	
		addBehaviour(new SimpleBehaviour(this) 
		  {
			 int n = 0;
			 
			 public void action() 
			 {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setContent("Message #" + n );
				msg.addReceiver(newAID1);
				System.out.println("+++ Sending: " + n);
				send(msg);
				block( 1000 );
			 }
			 
			 public  boolean done() {  return ++n > 3;  }
	
		  });
            
            Object [] args = new Object[2];
            args[0] = "3";
            args[1] = "Hello there";
	 
            String name = "Fred" ;
            AID newAID2 = new AID( name, AID.ISLOCALNAME );
            try {
                AgentController a = c.createNewAgent( name, "ParamAgent", args );
                a.start();
                System.out.println("+++ Created: " + newAID2);
            }
            catch (Exception e){}

        
	}
}
