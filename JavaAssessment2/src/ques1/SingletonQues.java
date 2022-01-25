package ques1;

import java.io.Serializable;

enum MySingleton {
	INSTANCE;
	// your logic here....
}

class Singleton implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	private static volatile Singleton singleton = null;	//lazy instantiation

	private Singleton() {
		if (singleton != null) {
			//REFLECTION ISSUE - if someone tries to access our private constructor
			//throw exception if some one tries to break singleton pattern or use reflection
			throw new IllegalStateException();
		}
	}

	public static Singleton getSingleton10() {
		if (singleton == null) {
			if (singleton == null) {
				//multithreading issue-use double lock checking in synchronized block to handle this issue.
				synchronized (Singleton.class) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	//SERIALIZATION ISSUE
	//By defining readResolve(), we force JVM to not deserialize but invoke
	//the readResolve() method when someone tries to deserialize our object.
	//Hence, returning the same singleton object as generated.
	private Object readResolve() {
		return singleton;
	}

	//CLONING ISSUE 
	//override the clone method and return the singleton object explicitely while implemention for cloneable
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return singleton;
	}
}

public class SingletonQues {
	public static void main(String[] args) {
		//Enum singleton pattern -- by default enum is singleton
		
		MySingleton singletonEnum = MySingleton.INSTANCE;
		System.out.println(singletonEnum.hashCode());
		
		MySingleton singletonEnum2 = MySingleton.INSTANCE;
		System.out.println(singletonEnum2.hashCode());
	}
}
