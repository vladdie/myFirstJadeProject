
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fan
 */
public class testBuyerAgent extends Agent {
    
    private String targetTitle;
    private AID[] sellerAgents = {new AID("seller1", AID.ISLOCALNAME),
                                  new AID("seller2", AID.ISLOCALNAME)};
    
    protected void setup(){
        
        System.out.println("Hello! Buyer agent " + getAID().getName() + " is ready.");
        
        // Get the title of the book to buy as a start-up argument
        Object[] args = getArguments();
        if(args != null && args.length > 0){
            targetTitle = (String) args[0];
            System.out.println("Trying to buy " + targetTitle);
            
            // Add a TickerBehaviour that schedules a request to seller agents every minute
//            addBehaviour(new TickerBehaviour(this,60000){
//                protected void onTick(){
//                    //Note the use of the myAgent protected variable: each behavior has a pointer to the agent that is
//                    //executing it.
//                    myAgent.addBehaviour(new RequestPerformer());
//                }
//            });
        }else{
            System.out.println("No buying item specified!");
            doDelete();
        }
    }
    
    protected void takeDown(){
        System.out.println("Buyer agent " + getAID().getName() + " terminating");
    }
    
}
