package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = 0;
        if(petTypeService.findAll() !=null){
            count = petTypeService.findAll().size();
        }
        if (count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dogPetType = new PetType();
        dogPetType.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dogPetType);

        PetType catPetType = new PetType();
        catPetType.setName("Cat");
        PetType savedCatPetType = petTypeService.save(catPetType);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Berley");
        owner1.setCity("Miami");
        owner1.setTelephone("1231243232");

        Pet mikesPets = new Pet();
        mikesPets.setPetType(savedDogPetType);
        mikesPets.setOwner(owner1);
        mikesPets.setName("Rosco");
        mikesPets.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPets);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Berley");
        owner2.setCity("Miami");
        owner2.setTelephone("1231243232");

        Pet fionasPets = new Pet();
        fionasPets.setPetType(savedCatPetType);
        fionasPets.setOwner(owner2);
        fionasPets.setName("kitty");
        fionasPets.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasPets);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasPets);
        catVisit.setDescription("sneezy kitty");
        catVisit.setDate(LocalDate.now());
        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(surgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
