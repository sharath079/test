package com.vw.runtime.jms;
import java.util.Date;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
/*@MessageDriven(activationConfig = 
{
 @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
 @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/MyQueue") 
})*/
public class VWJMSConsumer implements MessageListener 
{
    public VWJMSConsumer() 
    {
    }
    public void onMessage(Message message) 
    {
        try {
            if (message instanceof TextMessage) 
            {
                System.out.println("Queue: I received a TextMessage at "
                        + new Date());
                TextMessage msg = (TextMessage) message;
                System.out.println("Message is : " + msg.getText());
            } else if (message instanceof ObjectMessage) 
            {
                System.out.println("Queue: I received an ObjectMessage at "+ new Date());
                System.out.println("Employee Details: ");
            } else {
                System.out.println("Not valid message for this Queue MDB");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
