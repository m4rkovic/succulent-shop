package com.m4rkovic.succulent_shop.mapper;

import com.m4rkovic.succulent_shop.dto.ProductDTO;
import com.m4rkovic.succulent_shop.entity.Plant;
import com.m4rkovic.succulent_shop.entity.Product;
import com.m4rkovic.succulent_shop.enumerator.PotSize;
import com.m4rkovic.succulent_shop.enumerator.PotType;
import com.m4rkovic.succulent_shop.enumerator.ProductType;
import com.m4rkovic.succulent_shop.enumerator.ToolType;
import com.m4rkovic.succulent_shop.service.PlantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductMapper {

    private final PlantService plantService;

    public ProductMapper(PlantService plantService) {
        this.plantService = plantService;
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Plant plant = dto.getPlantId() != null ?
                plantService.findById(dto.getPlantId()) : null;

        return Product.builder()
                .id(dto.getId())
                .productName(dto.getProductName())
                .productDesc(dto.getProductDesc())
                .potSize(StringUtils.isNotBlank(dto.getPotSize()) ?
                        PotSize.valueOf(dto.getPotSize().toUpperCase()) : null)
                .productType(StringUtils.isNotBlank(dto.getProductType()) ?
                        ProductType.valueOf(dto.getProductType().toUpperCase()) : null)
                .isPot(dto.isPot())
                .potType(StringUtils.isNotBlank(dto.getPotType()) ?
                        PotType.valueOf(dto.getPotType().toUpperCase()) : null)
                .toolType(StringUtils.isNotBlank(dto.getToolType()) ?
                        ToolType.valueOf(dto.getToolType().toUpperCase()) : null)
                .potNumber(dto.getPotNumber())
                .price(dto.getPrice())
                .plant(plant)
                .build();
    }

    public ProductDTO toDTO(Product entity) {
        if (entity == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .productDesc(entity.getProductDesc())
                .potSize(entity.getPotSize() != null ?
                        entity.getPotSize().name() : null)
                .productType(entity.getProductType() != null ?
                        entity.getProductType().name() : null)
                .isPot(entity.isPot())
                .potType(entity.getPotType() != null ?
                        entity.getPotType().name() : null)
                .toolType(entity.getToolType() != null ?
                        entity.getToolType().name() : null)
                .potNumber(entity.getPotNumber())
                .price(entity.getPrice())
                .plantId(entity.getPlant() != null ?
                        entity.getPlant().getId() : null)
                .build();
    }

    public void updateEntityFromDTO(Product entity, ProductDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        if (StringUtils.isNotBlank(dto.getProductName())) {
            entity.setProductName(dto.getProductName());
        }
        if (StringUtils.isNotBlank(dto.getProductDesc())) {
            entity.setProductDesc(dto.getProductDesc());
        }
        if (StringUtils.isNotBlank(dto.getPotSize())) {
            entity.setPotSize(PotSize.valueOf(dto.getPotSize().toUpperCase()));
        }
        if (StringUtils.isNotBlank(dto.getProductType())) {
            entity.setProductType(ProductType.valueOf(dto.getProductType().toUpperCase()));
        }
        if (StringUtils.isNotBlank(dto.getPotType())) {
            entity.setPotType(PotType.valueOf(dto.getPotType().toUpperCase()));
        }
        if (StringUtils.isNotBlank(dto.getToolType())) {
            entity.setToolType(ToolType.valueOf(dto.getToolType().toUpperCase()));
        }
        if (dto.isPot() == true) {
            entity.setPotNumber(dto.getPotNumber());
        }
        if (dto.getPrice() != null) {
            entity.setPrice(dto.getPrice());
        }
        if (dto.getPlantId() != null) {
            entity.setPlant(plantService.findById(dto.getPlantId()));
        }
    }

    public List<ProductDTO> toDTOList(List<Product> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}