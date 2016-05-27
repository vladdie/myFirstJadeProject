/*****************************************************************

    FSMAgent.java:  Better combination of sequential actions with a 
    -----------     cyclic Looper behaviour
 
*****************************************************************/


import jade.core.Agent;
import jade.core.behaviours.*;


public class FSMAgent extends Agent 
{
    
    protected void setup() 
    {
        System.out.println("Agent "+ getLocalName() + " is initializing..."); 
        addBehaviour( new simpleFSM() );
        addBehaviour( new Looper( this, 300 ) );
        System.out.println("Set up finished. Agent "+ getLocalName() + " is ready."); 
    }
    
    protected void takeDown()
    {
        //stopped the container and terminate the program with "System.exit(0)"
        System.exit(0); 
    }


    class simpleFSM extends SimpleBehaviour
    {   
        int state = 1;
        
        public void action() 
        {
            switch( state) {
            case 1:
                block( 200 );
                System.out.println("200ms block started..."); 
                break;
                
            case 2:
               // System.out.println("200ms block finished!"); 
                System.out.println( "--- Message 1 --- " );
                block( 800 );
                System.out.println("800ms block started..."); 
                break;
            
            case 3:
               // System.out.println("800ms block finished!"); 
                System.out.println( "  -- message 2 --" );
                finished = true;
                doDelete();   // applies to the Agent
            }
            state++;
        }
        
        private boolean finished = false;
        public  boolean done() {  return finished;  }
    }
    
}

