package cn.it-man.demo;

import java.util.Map;

import org.jolokia.client.J4pClient;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;


/*
 *@author chen.bin
 *@version 2015年4月15日下午2:09:08
 */
public class JolokiaDemo {
    
    public static void main(String[] args) throws Exception {
        J4pClient j4pClient = new J4pClient("http://200.200.202.67:8080/jolokia");
        //堆使用率
        J4pReadResponse resp = j4pClient.execute(new J4pReadRequest("java.lang:type=Memory",
                "HeapMemoryUsage"));//max commited init used
        Map<String,Object> heapMemoryUsage = resp.getValue();

        long used = (Long) heapMemoryUsage.get("used");
        long max = (Long) heapMemoryUsage.get("max");
        long usage = (long) (used * 100 / max);
        System.out.println("Memory usage: used: " + used + 
                           " / max: " + max + " = " + usage + "%");
        
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=Memory",
                "NonHeapMemoryUsage"));//max commited init used
        Map<String,Object> nonheapMemoryUsage  = resp.getValue();
        for(Map.Entry<String, Object> entry:nonheapMemoryUsage.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        /*
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=MemoryPool,name=Code Cache",
                "Usage"));//max commited init used
        Map<String,Object> codeCache  = resp.getValue();
        for(Map.Entry<String, Object> entry:codeCache.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=MemoryPool,name=PS Eden Space",
                "Usage"));//max commited init used
        Map<String,Object> edenSpace  = resp.getValue();
        for(Map.Entry<String, Object> entry:edenSpace.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=MemoryPool,name=PS Old Gen",
                "Usage"));//max commited init used
        Map<String,Object> oldGen  = resp.getValue();
        for(Map.Entry<String, Object> entry:oldGen.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=MemoryPool,name=PS Perm Gen",
                "Usage"));//max commited init used
        Map<String,Object> permGen  = resp.getValue();
        for(Map.Entry<String, Object> entry:permGen.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=MemoryPool,name=PS Survivor Space",
                "Usage"));//max commited init used
        Map<String,Object> survivorSpace  = resp.getValue();
        for(Map.Entry<String, Object> entry:survivorSpace.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        resp = j4pClient.execute(new J4pReadRequest("java.lang:type=Runtime"));
        Map<String,Object> runtime  = resp.getValue();
        for(Map.Entry<String, Object> entry:runtime.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        */
        System.out.println();
        System.out.println("catalina");
        resp = j4pClient.execute(new J4pReadRequest("Catalina:type=Manager,*"));
        Map<String,Object> catalina  = resp.getValue();
        for(Map.Entry<String, Object> entry:catalina.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        System.out.println();
        System.out.println("ThreadPool");
        //resp = j4pClient.execute(new J4pReadRequest("Catalina:context=/,host=localhost,type=Manager"));
        resp = j4pClient.execute(new J4pReadRequest("Catalina:type=Manager,*"));
        System.out.println(resp.getValue());
        
        Map<String,Object> threadPool = resp.getValue();
        System.out.println(threadPool.get("maxActiveSessions"));
       
        for(Map.Entry<String, Object> entry:threadPool.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
        System.out.println();
        System.out.println("test");
        resp = j4pClient.execute(new J4pReadRequest("Catalina:type=ThreadPool,name=\"http-nio-8080\"","maxThreads,currentThreadsBusy,currentThreadCount"));
        //resp = j4pClient.execute(new J4pReadRequest("Catalina:name=http-8080,type=ThreadPool"));
        System.out.println(resp.getValue());
        
        Map<String,Object> threadPool2 = resp.getValue();
      
       
        for(Map.Entry<String, Object> entry:threadPool2.entrySet()){
        	System.out.println(entry.getKey());
        	System.out.println(entry.getValue());
        }
        
    }
}

