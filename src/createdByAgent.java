
/*****************************************************************

	createdByAgent.java:    Program which creates another Agent and sends 
	------------     it some messages
	
    Author:  Jean Vaucher
    Date:    Aug 10 2003 
	
*****************************************************************/


import jade.core.Agent;
import jade.core.behaviours.*;

import jade.core.AID;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import jade.lang.acl.*;


public class createdByAgent extends Agent 
{
	
	String name = "Alice" ;//local name of the newly generated agent
        //use name to create agent ID
	AID alice = new AID( name, AID.ISLOCALNAME );

    protected void setup() 
    {
    	AgentContainer c = getContainerController();
    	try {
            //main agent creates a pong agent in its own container
    		AgentController a = c.createNewAgent( name, "Receiver", null );
    		a.start();
    		System.out.println("+++ Created: " + alice);
    	}
    	catch (Exception e){}
    	
		addBehaviour(new SimpleBehaviour(this) 
		  {
			 int n = 0;
			 
			 public void action() 
			 {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setContent("Message #" + n );
				msg.addReceiver(alice);
				System.out.println("+++ Sending: " + n);
				send(msg);
				block( 1000 );
			 }
			 
			 public  boolean done() {  return ++n > 3;  }
	
		  });
	}
}
