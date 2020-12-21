package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return null;
    }

    @Override
    public void deletebyId(Long id) {

    }

    @Override
    public void delete(PetType object) {

    }

    @Override
    public PetType save(PetType object) {
        return null;
    }

    @Override
    public PetType findById(Long id) {
        return null;
    }
}
