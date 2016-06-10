/*
  AMSDump:  Agent sends a Ping message to all running Jade Agents
  
    Version 1.0
    Jean Vaucher
    Aug 10 2003
  
  	Usage:  % java jade.Boot -container X:AMSDump
                jean% java jade.Boot a:Pong b:Pong xxx:Receiver
*/

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;

import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.ACLMessage;

public class AMSDump extends Agent 
{
    protected void setup() 
    {
	AMSAgentDescription [] agents = null;
        //search parameters:
        //1. the searching agent(this)
        //2. the AgentDescription which could be used filter agents
        //3. constraints on the number of agents returned. (-1:ALL)
        //returned value is AMSAgentDescriptions, use agents[i].getName() to extract AID
      	try 
        {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults (new Long(-1));
            agents = AMSService.search( this, new AMSAgentDescription (), c );
	}catch (Exception e) {
            System.out.println( "Problem searching AMS: " + e );
            e.printStackTrace();
	}
		
	AID myID = getAID();
	for (int i=0; i<agents.length;i++)
	{
            AID agentID = agents[i].getName();
            System.out.println(
		( agentID.equals( myID ) ? "*** " : "    ")//ATTENTION: use equal instead of ==
                    + i + ": " + agentID.getName() 
                );
	}
        
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.setContent( "Ping" );

	for (int i=0; i<agents.length;i++)
            msg.addReceiver( agents[i].getName() ); 

	send(msg);
		
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
        
	//doDelete();
	//System.exit(0);
    }
       
}//end class AMSDump