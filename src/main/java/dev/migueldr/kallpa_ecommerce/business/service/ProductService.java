package dev.migueldr.kallpa_ecommerce.business.service;

import dev.migueldr.kallpa_ecommerce.business.dto.ProductDto;
import dev.migueldr.kallpa_ecommerce.persistence.entity.ProductEntity;
import dev.migueldr.kallpa_ecommerce.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true) // Optimiza el rendimiento para solo lectura
    public List<ProductDto> findAllProducts() {
        List<ProductEntity> entities = productRepository.findAll();

        // Convertimos de Entity -> DTO usando Streams de Java
        return entities.stream()
                .map(this::mapToDto)
                .toList();
    }

    // Mapeo manual (Más adelante podemos usar MapStruct)
    private ProductDto mapToDto(ProductEntity entity) {
        return new ProductDto(
                entity.getId(),
                entity.getName(),
                entity.getSlug(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImageUrl(),
                entity.getBrand(),
                entity.getCategory() != null ? entity.getCategory().getName() : "Sin Categoría"
        );
    }
}
