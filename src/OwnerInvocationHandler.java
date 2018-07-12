import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class OwnerInvocationHandler implements InvocationHandler {
	
	private IPerson person;

	public OwnerInvocationHandler(IPerson person){
		this.person = person;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Exception {
	
		try{
			if (method.getName().equals("setRating")){
				throw new IllegalAccessException();
			}else if (method.getName().startsWith("set")){
				return method.invoke(person, args);
			}else if (method.getName().startsWith("get")){
				return method.invoke(person, args);
			}
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}
		return null;
	}

}
