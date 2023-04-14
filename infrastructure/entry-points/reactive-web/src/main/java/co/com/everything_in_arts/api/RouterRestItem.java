package co.com.everything_in_arts.api;

import co.com.everything_in_arts.model.category.Category;
import co.com.everything_in_arts.model.item.Item;
import co.com.everything_in_arts.usecase.category.categorygetall.CategoryGetAllUseCase;
import co.com.everything_in_arts.usecase.item.itemdecreasestock.ItemDecreaseStockUseCase;
import co.com.everything_in_arts.usecase.item.itemdelete.ItemDeleteUseCase;
import co.com.everything_in_arts.usecase.item.itemgetByCategory.ItemGetByCategoryUseCase;
import co.com.everything_in_arts.usecase.item.itemgetall.ItemGetAllUseCase;
import co.com.everything_in_arts.usecase.item.itemgetbyid.ItemGetByIdUseCase;
import co.com.everything_in_arts.usecase.item.itemsave.ItemSaveUseCase;
import co.com.everything_in_arts.usecase.item.itemupdate.ItemUpdateUseCase;
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
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestItem {
    @Bean
    @RouterOperation(path = "/items", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemGetAllUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllItems", tags = "Items usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "204", description = "Nothing to show")
                    }))
    public RouterFunction<ServerResponse> getAllItems (ItemGetAllUseCase itemGetAllUseCase){
        return route(GET("/items"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(itemGetAllUseCase.get(), Item.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }


    @Bean
    @RouterOperation(path = "/items/categories", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = CategoryGetAllUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getAllCategories", tags = "Category usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Category.class))),
                            @ApiResponse(responseCode = "204", description = "Nothing to show")
                    }))
    public RouterFunction<ServerResponse> getAllCategories (CategoryGetAllUseCase categoryGetAllUseCase){
        return route(GET("/items/categories"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(categoryGetAllUseCase.get(), Category.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/items/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemGetByIdUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getItemById", tags = "Items usecases",
                    parameters = {
                    @Parameter(name = "id", description = "Item ID", required = true, in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "404", description = "Not Found")
                    }))
    public RouterFunction<ServerResponse> getItemById(ItemGetByIdUseCase itemGetByIdUseCase){
        return route(GET("items/{id}"),
                request -> itemGetByIdUseCase.apply( request.pathVariable("id"))
                        .flatMap(item -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(item))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/items/category/{category}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemGetByCategoryUseCase.class, method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getItemsByCategory", tags = "Items usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "204", description = "Nothing to show")
                    }))
    public RouterFunction<ServerResponse> getItemsByCategory (ItemGetByCategoryUseCase itemGetByCategoryUseCase) {
        return route(GET("/items/category/{category}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(itemGetByCategoryUseCase.apply(request.pathVariable("category")), Item.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/items", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemSaveUseCase.class, method = RequestMethod.POST,
            beanMethod = "apply",
            operation = @Operation(operationId = "saveItem", tags = "Items usecases",
                    parameters = {
                            @Parameter(name = "item", in = ParameterIn.PATH, schema = @Schema(implementation = Item.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success", content = @Content(schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable. Try again")},
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Item.class)))))

    public RouterFunction<ServerResponse> saveItem (ItemSaveUseCase itemSaveUseCase){
        return route(POST("/items").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Item.class)
                        .flatMap(item -> itemSaveUseCase.apply(item)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    @RouterOperation(path = "/items/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemUpdateUseCase.class, method = RequestMethod.PUT, beanMethod = "apply",
            operation = @Operation(operationId = "updateItem", tags = "Items usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Item ID", required = true, in = ParameterIn.PATH),
                            @Parameter(name = "item", in = ParameterIn.PATH, schema = @Schema(implementation = Item.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable. Try again")},
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Item.class)))))
    public RouterFunction<ServerResponse> updateItem(ItemUpdateUseCase itemUpdateUseCase){
        return route(PUT("/items/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Item.class)
                        .flatMap(item -> itemUpdateUseCase.apply(request.pathVariable("id"),
                                        item)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                        .bodyValue(throwable.getMessage()))
                        )
        );
    }

    @Bean
    @RouterOperation(path = "/items/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemDeleteUseCase.class, method = RequestMethod.DELETE,
            beanMethod = "apply",
            operation = @Operation(operationId = "deleteItemById", tags = "Items usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Item ID", required = true, in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "404", description = "Item Not Found")
                    }))
    public RouterFunction<ServerResponse> deleteItem (ItemDeleteUseCase itemDeleteUseCase){
        return route(DELETE("/items/{id}"),
                request -> itemDeleteUseCase.apply(request.pathVariable("id"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(""))
                        .flatMap(serverResponseMono -> serverResponseMono)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(path = "/items/stock/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = ItemDecreaseStockUseCase.class, method = RequestMethod.DELETE,
            beanMethod = "apply",
            operation = @Operation(operationId = "reduceStockById", tags = "Items usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Item ID", required = true, in = ParameterIn.PATH)
                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content (schema = @Schema(implementation = Item.class))),
                            @ApiResponse(responseCode = "404", description = "Item Not Found")
                    }))
    public RouterFunction<ServerResponse> reduceStockItem (ItemDecreaseStockUseCase itemDecreaseStockUseCase){
        return route(PATCH("/items/stock/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> itemDecreaseStockUseCase.apply(request.pathVariable("id"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(""))
                        .flatMap(serverResponseMono -> serverResponseMono)
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }






}
