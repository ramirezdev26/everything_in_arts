package co.com.everything_in_arts.api;

import co.com.everything_in_arts.model.invoice.Invoice;
import co.com.everything_in_arts.usecase.invoice.invoicedelete.InvoiceDeleteUseCase;
import co.com.everything_in_arts.usecase.invoice.invoicegetall.InvoiceGetAllUseCase;
import co.com.everything_in_arts.usecase.invoice.invoicegetbyid.InvoiceGetByIdUseCase;
import co.com.everything_in_arts.usecase.invoice.invoicesave.InvoiceSaveUseCase;
import co.com.everything_in_arts.usecase.invoice.invoiceupdate.InvoiceUpdateUseCase;
import co.com.everything_in_arts.usecase.item.itemdelete.ItemDeleteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestInvoice {


    @Bean
    @RouterOperation(path = "/invoices", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = InvoiceGetAllUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllInvoice", tags = "Invoice usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Invoice.class))),
                            @ApiResponse(responseCode = "204", description = "Nothing to show")
                    }))
    public RouterFunction<ServerResponse> getAllInvoices (InvoiceGetAllUseCase invoiceGetAllUseCase){
        return route(GET("/invoices"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(invoiceGetAllUseCase.get(), Invoice.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/invoices/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = InvoiceGetByIdUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getInvoiceById", tags = "Invoice usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Invoice ID", required = true, in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Invoice.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getInvoiceById(InvoiceGetByIdUseCase invoiceGetByIdUseCase){
        return route(GET("invoices/{id}"),
                request -> invoiceGetByIdUseCase.apply( request.pathVariable("id"))
                        .flatMap(invoice -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(invoice))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/invoices", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = InvoiceSaveUseCase.class, method = RequestMethod.POST,
            beanMethod = "apply",
            operation = @Operation(operationId = "saveInvoice", tags = "Invoice usecases",
                    parameters = {
                            @Parameter(name = "invoice", in = ParameterIn.PATH, schema = @Schema(implementation = Invoice.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success", content = @Content(schema = @Schema(implementation = Invoice.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable. Try again")},
                    requestBody = @RequestBody(required = true, description = "save an invoice", content = @Content(schema = @Schema(implementation = Invoice.class)))))

    public RouterFunction<ServerResponse> saveInvoice (InvoiceSaveUseCase invoiceSaveUseCase){
        return route(POST("/invoices").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Invoice.class)
                        .flatMap(invoice -> invoiceSaveUseCase.apply(invoice)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    @RouterOperation(path = "/invoices/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = InvoiceUpdateUseCase.class, method = RequestMethod.PUT, beanMethod = "apply",
            operation = @Operation(operationId = "updateInvoice", tags = "Invoice usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Invoice ID", required = true, in = ParameterIn.PATH),
                            @Parameter(name = "invoice", in = ParameterIn.PATH, schema = @Schema(implementation = Invoice.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = Invoice.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable. Try again")},
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Invoice.class)))))
    public RouterFunction<ServerResponse> updateInvoice(InvoiceUpdateUseCase invoiceUpdateUseCase){
        return route(PUT("/invoices/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Invoice.class)
                        .flatMap(invoice -> invoiceUpdateUseCase.apply(request.pathVariable("id"),
                                        invoice)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .bodyValue(throwable.getMessage()))
                        )
        );
    }

    @Bean
    @RouterOperation(path = "/invoices/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = InvoiceDeleteUseCase.class, method = RequestMethod.DELETE,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteInvoiceById", tags = "Invoice usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Invoice ID", required = true, in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Invoice.class))),
                            @ApiResponse(responseCode = "404", description = "Item Not Found")
                    }))
    public RouterFunction<ServerResponse> deleteInvoice (InvoiceDeleteUseCase invoiceDeleteUseCase){
        return route(DELETE("/invoices/{id}"),
                request -> invoiceDeleteUseCase.apply(request.pathVariable("id"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Invoice deleted"))
                        .flatMap(serverResponseMono -> serverResponseMono)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }



}
