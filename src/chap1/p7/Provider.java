package chap1.p7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Provider {

	public static void main(String[] args) throws Exception{
		Map<String, Object>services = new HashMap<>();
		services.put(SayHelloService.class.getName(), new SayHelloServiceImpl());
		ServerSocket server=null;
		try {
			server = new ServerSocket(9001);
		}catch (Exception e) {
			System.out.println("1");
			e.printStackTrace();
			// TODO: handle exception
		}
		
		while(true) {
			Socket socket = server.accept();
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			String interfacename=input.readUTF();
			String methodName=input.readUTF();
			Class<?>[] parameterTypes=(Class<?>[])input.readObject();
			Object[] arguments=(Object[])input.readObject();
			Class serviceinterfaceclass=Class.forName(interfacename);
			Object service =services.get(interfacename); 
			Method method = serviceinterfaceclass.getMethod(methodName, parameterTypes);
			Object result = method.invoke(service, arguments);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(result);
		}
		
		
	}
}
