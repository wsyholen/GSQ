package com.sixtyrobbers.GSQ.fourm.common.redis;


import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <pre>
 * Explain: Hessian序列化
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 13:59
 * Version: V1.0
 * </pre>
 */
public class HessianRedisSerializer implements RedisSerializer<Object>{

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		if (t == null) {
			return new byte[0];
		} 
	      
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    HessianOutput ho = new HessianOutput(os);  
	    
	    try {
			ho.writeObject(t);
		} catch (IOException e) {
			throw new SerializationException("Cannot deserialize", e);
		}  
	    return os.toByteArray(); 
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
			if (bytes == null || bytes.length == 0) {
				return null;
			} 
		    ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		    HessianInput hi = new HessianInput(is);  
		    try {
				return hi.readObject();
			} catch (IOException e) {
				throw new SerializationException("Cannot serialize", e);
			} 
	}

}