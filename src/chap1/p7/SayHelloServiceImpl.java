package chap1.p7;

public class SayHelloServiceImpl implements SayHelloService{

	@Override
	public String sayHello(String helloArg) {
		if(helloArg.equals("hello")) {
			return "hello1";
		}else {
			return "bye bye";
		}
	}

}
