package helpers

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import jakarta.ws.rs.core.MultivaluedMap
import jakarta.ws.rs.ext.Provider

@Provider
class CorsFilter : ContainerResponseFilter {
    override fun filter(p0: ContainerRequestContext?, responseContext: ContainerResponseContext?) {
        val responseHeaders: MultivaluedMap<String, Any> = responseContext!!.headers

        responseHeaders.add("Access-Control-Allow-Origin", "*")
        responseHeaders.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        responseHeaders.add("Access-Control-Allow-Credentials", "true")
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        responseHeaders.add("Access-Control-Max-Age", "1209600")
    }

}