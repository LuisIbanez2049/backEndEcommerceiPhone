package com.example.iphonedropp;

import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.Client;
import com.example.iphonedropp.models.Product;
import com.example.iphonedropp.models.utils.GenerateOrderNumber;
import com.example.iphonedropp.repository.CategoryRepository;
import com.example.iphonedropp.repository.ClientRepository;
import com.example.iphonedropp.repository.OrderRepository;
import com.example.iphonedropp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class IphonedroppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IphonedroppApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, OrderRepository orderRepository,
									  ProductRepository productRepository, CategoryRepository categoryRepository){
		return (args) -> {

			//------------------------------------------------CREO A LOS CLIENTES------------------------------------------------
			Client luis = new Client("Luis Ibañez", "luis@gmail.com", "1134343434", passwordEncoder.encode("123"));
			Client anna = new Client("Anna Steve", "anna@gmail.com", "1132323232", passwordEncoder.encode("123"));

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

			Product iMac = new Product("iMac", "https://res.cloudinary.com/dlyoighih/image/upload/v1748105605/iMac_n90y4j.jpg", 2,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748105606/iMac1_shxm4q.png"));

			iMac.setCategory(macs);
			macs.addProduct(iMac);
			productRepository.save(iMac);



			Product iPhone16ProMax = new Product("iPhone 16 Pro Max", "https://res.cloudinary.com/dlyoighih/image/upload/v1748105856/iphon161_m9dvpa.jpg", 6,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748105857/iphon162_vt1coo.jpg"));
			iPhone16ProMax.setCategory(iPhones);
			iPhones.addProduct(iPhone16ProMax);
			productRepository.save(iPhone16ProMax);

			Product iPhone15 = new Product("iPhone 15", "https://res.cloudinary.com/dlyoighih/image/upload/v1748105856/iphon151_uvceqa.jpg", 6,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748105857/iphon152_y8ecud.png"));
			iPhone15.setCategory(iPhones);
			iPhones.addProduct(iPhone15);
			productRepository.save(iPhone15);



			Product iPadPro = new Product("iPad Pro", "https://res.cloudinary.com/dlyoighih/image/upload/v1748106383/iPadPro1_hfqztr.jpg", 4,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106383/iPadPro2_pthxwh.jpg"));
			iPadPro.setCategory(iPads);
			iPads.addProduct(iPadPro);
			productRepository.save(iPadPro);

			Product iPadAir = new Product("iPad Air", "https://res.cloudinary.com/dlyoighih/image/upload/v1748106382/iPadAir1_hetfjw.jpg", 4,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106382/iPadAir2_ntoqdo.jpg"));
			iPadAir.setCategory(iPads);
			iPads.addProduct(iPadAir);
			productRepository.save(iPadAir);




			Product wathSeries10 = new Product("Apple Watch Series 10", "https://res.cloudinary.com/dlyoighih/image/upload/v1748106709/watch101_f8jomo.jpg", 2,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106710/watch102_gp5iu3.png"));
			wathSeries10.setCategory(watches);
			watches.addProduct(wathSeries10);
			productRepository.save(wathSeries10);


			Product watchUltra = new Product("Apple Watch Ultra", "https://res.cloudinary.com/dlyoighih/image/upload/v1748106719/watchUltra1_yugkfb.jpg", 3,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106720/watchUltra2_fdhlkd.png"));
			watchUltra.setCategory(watches);
			watches.addProduct(watchUltra);
			productRepository.save(watchUltra);

			//------------------------------------------------CREO LOS PRODUCTOS------------------------------------------------

		};
	}
}
