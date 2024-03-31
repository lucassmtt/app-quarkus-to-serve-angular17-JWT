package helpers

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import jakarta.ws.rs.ext.Provider

@Provider
class CorsFilter : ContainerResponseFilter {
    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {


        val requestHeaders = requestContext.headers;
        val responseHeaders = responseContext!!.headers

        val origin = requestHeaders.getFirst("Origin")

        var front = "http://localhost:4200";

        responseHeaders.add("Access-Control-Allow-Origin", front);
        responseHeaders.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        responseHeaders.add("Access-Control-Allow-Credentials", "true")
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        responseHeaders.add("Access-Control-Max-Age", "1209600")
    }

}