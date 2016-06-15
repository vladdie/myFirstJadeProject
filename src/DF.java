/*****************************************************************

    DF1.java:    First test of DF (Directory Facilitator),This doesn't specify anything about the agent; just that it is available.  
    DF2.java:    Add service name and service type 
    * -----------     
    
    Author:  Jean Vaucher
    Date:    Aug 12 2003     
********************************4********************************/


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;

import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

public class DF extends Agent 
{

//    test1:
//    protected void setup() 
//    {
//        //use DFD with an agentID
//	DFAgentDescription dfd = new DFAgentDescription();
//        dfd.setName( getAID() );
//        register( dfd );
//        
//	System.exit(0);
//    }
//    
//    protected void register( DFAgentDescription dfd ) 
//    {
//        try {  
//            //register requires 2 references: one to the agent doing registration, one to the DFD
//            DFService.register(this, dfd );  
//        }catch (FIPAException fe) {
//            fe.printStackTrace(); 
//	}//incorrect DFD or duplicate entries
//    }
    
//     test2:   
//       protected void setup() 
//       {
//        ServiceDescription sd  = new ServiceDescription();
//        sd.setType( "buyer" );
//        sd.setName( getLocalName() );//specify a name for the service: use agent's local name
//        register( sd );
//    }
//    
//    void register( ServiceDescription sd)
//    {
//        DFAgentDescription dfd = new DFAgentDescription();
//        dfd.setName(getAID());
//        dfd.addServices(sd);
//
//        try {  
//            DFService.register(this, dfd );  
//        }
//        catch (FIPAException fe) { fe.printStackTrace(); }
//    }
    
    
//     test3:   
     protected void setup() 
    {
        ServiceDescription sd  = new ServiceDescription();
        sd.setType( "buyer" );
        sd.setName( getLocalName() );
        register( sd );
        
        try {
                DFAgentDescription dfd = new DFAgentDescription();
                DFAgentDescription[] result = DFService.search(this, dfd);

                System.out.println("Search returns: " + result.length + " elements" );
                if (result.length>0)
                        System.out.println(" " + result[0].getName() );


                sd  = new ServiceDescription();
                sd.setType( "buyer" );
                dfd.addServices(sd);
                result = DFService.search(this, dfd);
                System.out.println("Search for BUYER: " + result.length + " elements" );
                if (result.length>0)
                        System.out.println(" " + result[0].getName() );

                sd.setType( "seller" );
                result = DFService.search(this, dfd);
                if (result==null) System.out.println("Search1 returns null");
                else {
                        System.out.println("Search for SELLER: " + result.length + " elements" );
                        if (result.length>0)
                                System.out.println(" " + result[0].getName() );
                }
        }
        catch (FIPAException fe) { fe.printStackTrace(); }

		System.exit(0);
    }
    
    void register( ServiceDescription sd)
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);

        try {  
            DFService.register(this, dfd );  
        }
        catch (FIPAException fe) { fe.printStackTrace(); }
    }
    
	AID getService( String service )
	{
            DFAgentDescription dfd = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType( service );
            dfd.addServices(sd);
            try
            {
                    DFAgentDescription[] result = DFService.search(this, dfd);
                    if (result.length>0)
                            return result[0].getName() ;
            }
            catch (Exception fe) {}
            return null;
	}
}
