
import jade.core.Agent;
import jade.core.behaviours.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fan
 */
public class helloAgent extends Agent{
      protected void setup() 
      { 
          System.out.println("Hello World. ");
          System.out.println("Agent "+ getLocalName() + " is ready."); 
          addBehaviour(new myBehaviour(this));
      }
      
      class myBehaviour extends SimpleBehaviour 
        {   
            public myBehaviour(Agent a) { 
                super(a);  
            }
            
            public void action() 
            {
               //...this is where the real programming goes !!
            }
            
            private boolean finished = false;
            
            public boolean done() {  
                return finished;  
            }
            
        } // ----------- End myBehaviour
}
