package com.example.iphonedropp;

import com.example.iphonedropp.models.*;
import com.example.iphonedropp.models.utils.GenerateOrderNumber;
import com.example.iphonedropp.repository.CategoryRepository;
import com.example.iphonedropp.repository.ClientRepository;
import com.example.iphonedropp.repository.OrderRepository;
import com.example.iphonedropp.repository.ProductRepository;
import io.github.cdimascio.dotenv.Dotenv;
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
		// 🔹 Cargar las variables del archivo .env antes de iniciar Spring
//		io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.load();
//		System.setProperty("DB_URL", dotenv.get("DB_URL"));
//		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
//		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		// ⚙️ Solo cargar .env localmente (no en Render)
		String environment = System.getenv("ENVIRONMENT");

		if (environment == null || !"production".equalsIgnoreCase(environment)) {
			// ✅ Ignora error si el archivo .env no existe
			Dotenv dotenv = Dotenv.configure()
					.ignoreIfMissing()
					.load();
			System.out.println("🔹 Archivo .env cargado correctamente (modo local)");
		} else {
			System.out.println("🚀 Modo producción detectado (Render) — no se carga .env");
		}

		// 🔹 Ahora sí, iniciar la app
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

			luis.setClientRol(ClientRol.ADMIN);
			anna.setClientRol(ClientRol.CLIENT);
			clientRepository.save(luis);
			clientRepository.save(anna);
			//------------------------------------------------CREO A LOS CLIENTES------------------------------------------------


			//------------------------------------------------CREO LAS CATEGORIAS------------------------------------------------
			Category macs = new Category("Mac", "https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/mac_zo05nf.jpg", SectionCategory.MINORISTA);
			Category iPhones = new Category("iPhone", "https://res.cloudinary.com/dlyoighih/image/upload/v1755648272/16proMaxCategoria_vybima.jpg", SectionCategory.MINORISTA);
			Category iPads = new Category("iPad", "https://res.cloudinary.com/dlyoighih/image/upload/v1747971933/iPadCategory_z98mfx.jpg", SectionCategory.MINORISTA);
			Category watches = new Category("Watch", "https://res.cloudinary.com/dlyoighih/image/upload/v1747971933/watchCategory_wrdcne.jpg", SectionCategory.MINORISTA);

			categoryRepository.save(iPhones);
			categoryRepository.save(macs);
			categoryRepository.save(iPads);
			categoryRepository.save(watches);

			//------------------------------------------------CREO LAS CATEGORIAS------------------------------------------------


			//------------------------------------------------CREO LOS PRODUCTOS------------------------------------------------
			Product macBookAir = new Product("MackBook Air",
					4, Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/macAirlist1_xcozv0.png","https://www.youtube.com/embed/Mt7vLnKzH60?si=f6K82-0To_4YU2FR",
					"https://res.cloudinary.com/dlyoighih/image/upload/v1747970293/cld-sample-5.jpg","https://res.cloudinary.com/dlyoighih/video/upload/v1756352013/file_RojpET4s_1752511045388_dxoajg.mp4",
					"https://res.cloudinary.com/dlyoighih/video/upload/v1747970285/samples/sea-turtle.mp4",
					"https://res.cloudinary.com/dlyoighih/image/upload/v1747970561/macAirlist2_n38ed2.png"));
			macBookAir.setPrice(119.99);
			macBookAir.setDescription("<p>El MacBook Air redefine lo que un portátil delgado y ligero puede hacer. Con su diseño elegante de aluminio, es increíblemente delgado y ligero, pero también increíblemente potente. Está diseñado para hacerlo todo, desde navegar por la web hasta editar videos 4K, con facilidad.</p><p>Equipado con el chip M2, el MacBook Air ofrece un rendimiento impresionante y una eficiencia energética excepcional. Con hasta 18 horas de batería, puedes trabajar todo el día sin necesidad de buscar un enchufe. La pantalla Retina de 13.6 pulgadas con tecnología Liquid Retina ofrece colores vibrantes y detalles nítidos que te sumergirán en todo lo que hagas.</p><p>Equipado con el chip M2, el MacBook Air ofrece un rendimiento impresionante y una eficiencia energética excepcional. Con hasta 18 horas de batería, puedes trabajar todo el día sin necesidad de buscar un enchufe. La pantalla Retina de 13.6 pulgadas con tecnología Liquid Retina ofrece colores vibrantes y detalles nítidos que te sumergirán en todo lo que hagas.</p><p>Equipado con el chip M2, el MacBook Air ofrece un rendimiento impresionante y una eficiencia energética excepcional. Con hasta 18 horas de batería, puedes trabajar todo el día sin necesidad de buscar un enchufe. La pantalla Retina de 13.6 pulgadas con tecnología Liquid Retina ofrece colores vibrantes y detalles nítidos que te sumergirán en todo lo que hagas.</p><p>Equipado con el chip M2, el MacBook Air ofrece un rendimiento impresionante y una eficiencia energética excepcional. Con hasta 18 horas de batería, puedes trabajar todo el día sin necesidad de buscar un enchufe. La pantalla Retina de 13.6 pulgadas con tecnología Liquid Retina ofrece colores vibrantes y detalles nítidos que te sumergirán en todo lo que hagas.</p><br/><p>Equipado con el chip M2, el MacBook Air ofrece un rendimiento impresionante y una eficiencia energética excepcional. Con hasta 18 horas de batería, puedes trabajar todo el día sin necesidad de buscar un enchufe. La pantalla Retina de 13.6 pulgadas con tecnología Liquid Retina ofrece colores vibrantes y detalles nítidos que te sumergirán en todo lo que hagas.</p><p>Equipado con el chip M2, el MacBook Air ofrece un rendimiento impresionante y una eficiencia energética excepcional. Con hasta 18 horas de batería, puedes trabajar todo el día sin necesidad de buscar un enchufe. La pantalla Retina de 13.6 pulgadas con tecnología Liquid Retina ofrece colores vibrantes y detalles nítidos que te sumergirán en todo lo que hagas.</p>");
			macBookAir.setCategory(macs);
			macs.addProduct(macBookAir);
			productRepository.save(macBookAir);

			Product iMac = new Product("iMac", 2,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748105605/iMac_n90y4j.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748105606/iMac1_shxm4q.png"));

			iMac.setPrice(234.55);
			iMac.setDescription("<p>tu mama</p>");
			iMac.setCategory(macs);
			macs.addProduct(iMac);
			productRepository.save(iMac);



			Product iPhone16ProMax = new Product("iPhone 16 Pro Max", 6,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748105856/iphon161_m9dvpa.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748105857/iphon162_vt1coo.jpg"));
			iPhone16ProMax.setPrice(1020.99);
			iPhone16ProMax.setDescription("");
			iPhone16ProMax.setCategory(iPhones);
			iPhones.addProduct(iPhone16ProMax);
			productRepository.save(iPhone16ProMax);

			Product iPhone15 = new Product("iPhone 15", 6,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748105856/iphon151_uvceqa.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748105857/iphon152_y8ecud.png"));
			iPhone15.setPrice(990.99);
			iPhone15.setDescription("");
			iPhone15.setCategory(iPhones);
			iPhones.addProduct(iPhone15);
			productRepository.save(iPhone15);



			Product iPadPro = new Product("iPad Pro", 4,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106383/iPadPro1_hfqztr.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748106383/iPadPro2_pthxwh.jpg"));
			iPadPro.setPrice(500);
			iPadPro.setDescription("");
			iPadPro.setCategory(iPads);
			iPads.addProduct(iPadPro);
			productRepository.save(iPadPro);

			Product iPadAir = new Product("iPad Air", 4,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106382/iPadAir1_hetfjw.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748106382/iPadAir2_ntoqdo.jpg"));
			iPadAir.setPrice(600.50);
			iPadAir.setDescription("");
			iPadAir.setCategory(iPads);
			iPads.addProduct(iPadAir);
			productRepository.save(iPadAir);




			Product wathSeries10 = new Product("Apple Watch Series 10", 2,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106709/watch101_f8jomo.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748106710/watch102_gp5iu3.png"));
			wathSeries10.setPrice(200.34);
			wathSeries10.setDescription("");
			wathSeries10.setCategory(watches);
			watches.addProduct(wathSeries10);
			productRepository.save(wathSeries10);


			Product watchUltra = new Product("Apple Watch Ultra", 3,
					Arrays.asList("https://res.cloudinary.com/dlyoighih/image/upload/v1748106719/watchUltra1_yugkfb.jpg","https://res.cloudinary.com/dlyoighih/image/upload/v1748106720/watchUltra2_fdhlkd.png"));
			watchUltra.setPrice(435);
			watchUltra.setDescription("<p>Ultra</p>");
			watchUltra.setCategory(watches);
			watches.addProduct(watchUltra);
			productRepository.save(watchUltra);

			//------------------------------------------------CREO LOS PRODUCTOS------------------------------------------------

		};
	}
}
