package com.example.apirest.crud.home

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

//sacado de https://www.youtube.com/watch?v=R5Fc-hP5QR8

//usando web https://crudcrud.com/
/*
* Here is what you can do
Action	HTTP	Payload	URL	Description
Create	POST	json	/<resource>	Create an entity represented by the JSON payload.
Read	GET	-	/<resource>	Get all entities from the resource.
Read	GET	-	/<resource>/<id>	Get a single entity.
Update	PUT	json	/<resource>/<id>	Update an entitiy with the JSON payload.
Delete	DELETE	-	/<resource>/<id>	Delete an entity.
*
* NOTA. Uso de postman
* */
interface ProductService {
    companion object {
        val instance =//instancia de retrofit

            //https://crudcrud.com/api/31800e26e0db4001a44ac908eb92e399/ //expired

//            Retrofit.Builder().baseUrl("https://crudcrud.com/api/ff95f67592fd427292f0868823ba3e8d/")//old

            Retrofit.Builder().baseUrl("https://crudcrud.com/api/31800e26e0db4001a44ac908eb92e399/")
                .addConverterFactory(MoshiConverterFactory.create())//conversor
                .client(OkHttpClient.Builder().build())//cliente
                .build().create(ProductService::class.java)
    }
//https://crudcrud.com/api/31800e26e0db4001a44ac908eb92e399/products
    /*
    1)(Create)Insertar-POST-body-raw-json
    {
    "name":"Producto 1",
    "price":23.34
}y SEND
    2)(Read)Consultar-GET
    {
        "_id": "6638a9393207ed03e8c92300",
        "name": "Producto 1",
        "price": 23.34
    }
    3)(Update)Modificar-PUT
    https://crudcrud.com/api/31800e26e0db4001a44ac908eb92e399/products/6638a9393207ed03e8c92300
{
    "name":"Producto 1",
    "price":55.00
}

    200OK->para que esté bien

    4)(Delete)Borrar-DELETE
    https://crudcrud.com/api/31800e26e0db4001a44ac908eb92e399/products/6638a9393207ed03e8c92300
    * */
    //paso de postman a Retrofit
    @GET("products")
    suspend fun getProducts(): List<Product>

    @POST("products")
    suspend fun insertProduct(@Body product: ProductDto): Product

    @PUT("products/{id}")
    suspend fun updateProduct(@Body product: ProductDto, @Path("id") id: String)

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: String)

    //añadir en AndroidManifest el permiso a internet
    //<uses-permission android:name="android.permission.INTERNET" />
}
