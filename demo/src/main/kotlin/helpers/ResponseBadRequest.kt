package helpers

import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.ResponseBuilder
import org.jboss.resteasy.reactive.RestResponse

class ResponseBadRequest {
    fun resourceNotCreateResponse(): Response {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("O recurso não foi criado")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}