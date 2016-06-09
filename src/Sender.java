
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/*****************************************************************

	Sender.java:    Program which sends messages to agents  
	------------     to agents whose local names are "a1" and "a2"
	
  Test1:  % java jade.Boot main:Sender a1:Receiver a2:Pong
  
  Test2: with "a1" and "a2" in a different container
  
  	    %java jade.Boot a1:Receiver a2:Pong 
  	
   and in another window do:

  	    %java jade.Boot -container main:Sender
	
*****************************************************************/
public class Sender extends Agent 
{
    protected void setup() 
    {
        System.out.println("Agent "+ getLocalName() + " is initializing..."); 
	// First set-up answering behaviour
	
		addBehaviour(new CyclicBehaviour(this) 
		{
			 public void action() {
				ACLMessage msg= receive();
				if (msg!=null)
					System.out.println( "== Answer" + " <- " 
					 +  msg.getContent() + " from "
					 +  msg.getSender().getName() );
				block();
			 }
		});
		
	// Send messages to "a1" and "a2"
	
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent( "Ping" );
                for (int i = 1; i<=2; i++)
                    msg.addReceiver( new AID( "receiver" + i, AID.ISLOCALNAME) );

		send(msg);
                System.out.println("Message sent out sucessfully!"); 
                
         System.out.println("Set up finished. Agent "+ getLocalName() + " is ready."); 
		
    }
}
