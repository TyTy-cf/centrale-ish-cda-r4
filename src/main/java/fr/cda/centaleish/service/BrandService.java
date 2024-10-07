package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.BrandDTO;
import fr.cda.centaleish.entity.Brand;
import fr.cda.centaleish.repository.BrandRepository;
import fr.cda.centaleish.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements
        ServiceListInterface<Brand, Long, BrandDTO, BrandDTO> {

    private BrandRepository brandRepository;

    @Override
    public Brand create(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Brand brand = findOneById(id);
            brand.setName("Marque supprimée");
            brandRepository.saveAndFlush(brand);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Brand update(BrandDTO dto, Long id) {
        Brand brand = findOneById(id);
        brand.setName(dto.getName());
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public Brand findOneById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
    }

    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }
}
