package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    public static final long ID = 1L;

    Owner returnOwner;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(eq(LAST_NAME))).thenReturn(returnOwner);

        Owner owner = ownerSDJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());

        verify(ownerRepository, times(1)).findByLastName(eq(LAST_NAME));
    }

    @Test
    void findAll() {
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(Owner.builder().id(1L).build());
        returnOwners.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());

        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(eq(ID))).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerSDJpaService.findById(ID);

        assertNotNull(owner);
        assertEquals(ID, owner.getId());
        verify(ownerRepository, times(1)).findById(eq(ID));
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(ID);

        assertNull(owner);
        verify(ownerRepository, times(1)).findById(eq(ID));
    }

    @Test
    void save() {
        Owner owner = ownerSDJpaService.save(returnOwner);

        verify(ownerRepository, times(1)).save(eq(returnOwner));
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);

        verify(ownerRepository).delete(returnOwner);
    }

    @Test
    void deletebyId() {
        ownerSDJpaService.deletebyId(ID);

        verify(ownerRepository).deleteById(eq(ID));
    }
}