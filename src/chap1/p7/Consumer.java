package chap1.p7;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Consumer {

	public static void main(String[] args) {
		try {
		String interfacename=SayHelloService.class.getName();
		Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);
		Object[] arguments = {"hello"};
		Socket socket = new Socket("127.0.0.1", 9001);
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		output.writeUTF(interfacename);
		output.writeUTF(method.getName());
		output.writeObject(method.getParameterTypes());
		output.writeObject(arguments);	
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		Object result = input.readObject();
		System.out.println(result);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception in consumer");
			e.printStackTrace();
		}
	}
}
