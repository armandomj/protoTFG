package proconex.game.conversor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Conversor {
    public byte[] passObjectToByte(Object obj){      
    	try {
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		ObjectOutputStream oos = new ObjectOutputStream(bos);
    		oos.writeObject(obj);
    		oos.flush();
    		oos.close();
    		return bos.toByteArray();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}       
        return null;
    }
    
    public Object passByteToObject(byte[] objB){
    	try {
            ByteArrayInputStream bais = new ByteArrayInputStream(objB);
            ObjectInputStream ins = new ObjectInputStream(bais);
			ins.close();
			return ins.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

}
