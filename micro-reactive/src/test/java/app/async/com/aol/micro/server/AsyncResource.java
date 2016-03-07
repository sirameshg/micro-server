package app.async.com.aol.micro.server;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.pcollections.ConsPStack;
import org.pcollections.PStack;
import org.springframework.stereotype.Component;

import com.aol.cyclops.control.ReactiveSeq;
import com.aol.cyclops.control.SimpleReact;
import com.aol.cyclops.types.futurestream.LazyFutureStream;
import com.aol.micro.server.auto.discovery.RestResource;
import com.aol.micro.server.testing.RestAgent;

@Path("/async")
@Component
public class AsyncResource implements RestResource{

	private final SimpleReact simpleReact =new SimpleReact();
	private final PStack<String> urls = ConsPStack.from(Arrays.asList("http://localhost:8080/async-app/async/ping2",
			"http://localhost:8080/async-app/async/ping",
			"http://localhost:8080/async-app/async/ping",
			"http://localhost:8080/async-app/async/ping"));
    
    	private final RestAgent client = new RestAgent();
    	
        @GET
        @Path("/expensive")
        @Produces("text/plain")
        public void expensive(@Suspended AsyncResponse asyncResponse){
  
        	LazyFutureStream.lazyFutureStreamFromIterable(urls)
					.then(it->client.get(it))
					.onFail(it -> "")
					.peek(it -> 
					System.out.println(it))
					.convertToSimpleReact()
					.<String,Boolean>allOf(data -> {
						System.out.println(data);
							return asyncResponse.resume(ReactiveSeq.fromIterable(data).join(";")); });
        }
        
        @GET
    	@Produces("text/plain")
    	@Path("/ping")
    	public String ping() {
    		return "test!";
    	}
    	
	
}
