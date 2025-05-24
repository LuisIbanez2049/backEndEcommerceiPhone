package com.example.iphonedropp;

import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.Client;
import com.example.iphonedropp.models.Product;
import com.example.iphonedropp.models.utils.GenerateOrderNumber;
import com.example.iphonedropp.repository.CategoryRepository;
import com.example.iphonedropp.repository.ClientRepository;
import com.example.iphonedropp.repository.OrderRepository;
import com.example.iphonedropp.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class IphonedroppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IphonedroppApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, OrderRepository orderRepository,
									  ProductRepository productRepository, CategoryRepository categoryRepository){
		return (args) -> {

			//------------------------------------------------CREO A LOS CLIENTES------------------------------------------------
			Client luis = new Client("Luis Ibañez", "luis@gmail.com", "1134343434");
			Client anna = new Client("Anna Steve", "anna@gmail.com", "1132323232");

			clientRepository.save(luis);
			clientRepository.save(anna);
			//------------------------------------------------CREO A LOS CLIENTES------------------------------------------------


			//------------------------------------------------CREO LAS CATEGORIAS------------------------------------------------
			Category macs = new Category("Mac", "https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/mac_zo05nf.jpg");
			Category iPhones = new Category("iPhone", "https://res.cloudinary.com/dlyoighih/image/upload/v1747971805/iPhoneCategory_bmrkxf.jpg");
			Category iPads = new Category("iPad", "https://res.cloudinary.com/dlyoighih/image/upload/v1747971933/iPadCategory_z98mfx.jpg");
			Category watches = new Category("Watch", "https://res.cloudinary.com/dlyoighih/image/upload/v1747971933/watchCategory_wrdcne.jpg");

			categoryRepository.save(macs);
			categoryRepository.save(iPhones);
			categoryRepository.save(iPads);
			categoryRepository.save(watches);

			//------------------------------------------------CREO LAS CATEGORIAS------------------------------------------------


			//------------------------------------------------CREO LOS PRODUCTOS------------------------------------------------
			Product macBookAir = new Product("MackBook Air", "https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/macAirlist1_xcozv0.png",
					4, Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/macAirlist1_xcozv0.png",
					"https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/macAirlist2_n38ed2.png"));

			macBookAir.setCategory(macs);
			macs.addProduct(macBookAir);
			productRepository.save(macBookAir);

			//------------------------------------------------CREO LOS PRODUCTOS------------------------------------------------

		};
	}
}
