package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.AddressDTO;
import fr.cda.centaleish.entity.Address;
import fr.cda.centaleish.repository.AddressRepository;
import fr.cda.centaleish.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService implements ServiceInterface<Address, Long, AddressDTO, AddressDTO> {

    private AddressRepository addressRepository;

    @Override
    public Address create(AddressDTO dto) {
        Address address = new Address();
        address.setZipCode(dto.getZipCode());
        address.setCity(dto.getCity());
        address.setStreetNumber(dto.getStreetNumber());
        address.setStreetName(dto.getStreetName());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());
        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Address update(AddressDTO o, Long id) {
        return null;
    }

    @Override
    public Address findOneById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }
}
