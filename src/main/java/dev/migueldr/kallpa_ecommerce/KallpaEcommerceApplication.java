package dev.migueldr.kallpa_ecommerce;

import dev.migueldr.kallpa_ecommerce.persistence.CategoryEntity;
import dev.migueldr.kallpa_ecommerce.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KallpaEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KallpaEcommerceApplication.class, args);
	}

	@Bean
	CommandLineRunner initData (CategoryRepository categoryRepository) {
		return args -> {
			if (categoryRepository.count() > 1) {
				// Aquí podríamos inicializar datos de prueba si es necesario
				System.out.println("Inicializando datos de prueba...");

				CategoryEntity tech = CategoryEntity.builder()
						.name("Tecnología")
						.slug("tecnologia-demo")
						.active(true)
						.build();
				CategoryEntity saveTech = categoryRepository.save(tech);

				System.out.println("Categoría guardada: " + saveTech.getName());

				CategoryEntity celulares = CategoryEntity.builder()
						.name("Celulares")
						.slug("celulares")
						.active(true)
						.parentId(saveTech.getId())
						.build();
				categoryRepository.save(celulares);

				System.out.println("Categoría guardada: " + celulares.getName());
			}else {
				System.out.println("--- YA EXISTEN DATOS EN LA DB, OMITIENDO CARGA ---");
			}
		};
	}

}
