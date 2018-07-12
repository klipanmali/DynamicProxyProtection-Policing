import java.lang.reflect.Proxy;


/*
 * Example from Head First Design Patterns
 * 
 * Java lets you create a proxy class on the fly that implements one or more interfaces and
 * forwards method invocations to a class that you specify.Because the actual proxy class is 
 * created at runtime, we refer to this Java technology as a dynamic proxy  
 * Because Java creates the Proxy class for you, you need a way to tell the Proxy class whatg to do.
 * You cant' put that code into the Proxy class like we did before. So if you cant put this code in
 * the Proxy class,where do you put it? In an InvocationHandler. The job of the InvocationHandler
 * is to respond to any method calls on the Proxy. Think of InvocationHandler as the object the Proxy 
 * asks to do all the real work after it's received the method calls
 */
public class ProtectionProxyTest {

	private IPerson person1;
	private IPerson person2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProtectionProxyTest test = new ProtectionProxyTest();
		test.start();

	}
	
	public ProtectionProxyTest(){
		
		person1 = new PersonImpl("Joe",134);
		person2 = new PersonImpl("Alice",234);
		
	}
	
	public void start(){
		
		IPerson ownerProxy = getOwnerProxy(person1);
		try{
			System.out.println("Owner name is " + ownerProxy.getName());
		}catch (Exception e){
			e.printStackTrace();
		}

		try{
			System.out.println(ownerProxy.getName()+" rating is "+ownerProxy.getHotOrNotRating());			
		}catch (Exception e){
			e.printStackTrace();
		}

		try{
			System.out.println("Changeing name to Peter");
			ownerProxy.setName("Peter");
		}catch (Exception e){
			e.printStackTrace();
		}

		try{
			System.out.println("Setting rating for the owner");
			ownerProxy.setRating(20);			
		}catch (Exception e){
			e.printStackTrace();
		}

		try{
			System.out.println(ownerProxy.getName()+" new rating is "+ownerProxy.getHotOrNotRating());			
		}catch (Exception e){
			e.printStackTrace();
		}

		
		IPerson notOwnerProxy = getNotOwnerProxy(person2);
		
		try{
			System.out.println("notOwner name is " + notOwnerProxy.getName());
			System.out.println(notOwnerProxy.getName()+" rating is "+notOwnerProxy.getHotOrNotRating());
			System.out.println("Setting rating for the notOwner");
			notOwnerProxy.setRating(20);
			System.out.println(notOwnerProxy.getName()+" new rating is "+notOwnerProxy.getHotOrNotRating());
			System.out.println("Changeing name to Mona");
			notOwnerProxy.setName("Mona");
			System.out.println("notOwner name is " + notOwnerProxy.getName());
			
		}catch (Exception e){
//			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	
	private IPerson getOwnerProxy(IPerson person){
		
		OwnerInvocationHandler invocationHandler = new OwnerInvocationHandler(person);
		
		return (IPerson) Proxy.newProxyInstance(person.getClass().getClassLoader(),
				person.getClass().getInterfaces(), 
				invocationHandler);
	}
	
	private IPerson getNotOwnerProxy(IPerson person){
		NotOwnerInvocationHandler invocationHandler = new NotOwnerInvocationHandler(person);
		
		return (IPerson) Proxy.newProxyInstance(person.getClass().getClassLoader(),
				person.getClass().getInterfaces(), 
				invocationHandler);
		
	}

}
