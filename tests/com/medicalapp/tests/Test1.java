package com.medicalapp.tests;

import com.medicalapp.exception.InvalidTelemetryDataException;
import com.medicalapp.exception.SensorMalfunctionException;
import com.medicalapp.model.IcuPatient;
import com.medicalapp.model.Inpatient;
import com.medicalapp.model.Patient;
import com.medicalapp.repository.HospitalRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class Test1 {
    private HospitalRepository repository;
    private Patient icuPatient;
    private Patient inpatient;

    @Before
    public void setUp() {
        repository = new HospitalRepository();
        icuPatient = new IcuPatient("P-001", "Gheorghe", 75, 12);
        inpatient = new Inpatient("P-002", "Elena", 25, "Salon 4A");

        repository.registerPatient(icuPatient);
        repository.registerPatient(inpatient);
    }

    @Test
    public void testPatientPolymorphismAndRepository() {
        assertTrue(icuPatient.getPatientType().contains("Terapie Intensivă"));
        assertTrue(inpatient.getPatientType().contains("Saloane Normale"));

        // Testăm getteri specifici claselor derivate
        assertEquals(12, ((IcuPatient) icuPatient).getVentilatorId());
        assertEquals("Salon 4A", ((Inpatient) inpatient).getRoomNumber());

        // Testăm metode de bază Patient
        assertEquals("P-001", icuPatient.getId());
        assertEquals(25, inpatient.getAge());

        // Testăm tratamentul curent (get/set)
        icuPatient.setCurrentTreatment("Oxigenoterapie");
        assertEquals("Oxigenoterapie", icuPatient.getCurrentTreatment());

        // Verificăm repository-ul
        Patient found = repository.findPatientById("P-001");
        assertNotNull(found);

        Collection<Patient> all = repository.getAllPatients();
        assertEquals(2, all.size());
    }

    @Test
    public void testRemainingExceptionsInstantiation() {
        // Forțăm instanțierea excepțiilor checked rămase pentru a obține 100% coverage pe pachetul de excepții
        Exception ex1 = new InvalidTelemetryDataException("Data err");
        Exception ex2 = new SensorMalfunctionException("Hardware err");

        assertEquals("Data err", ex1.getMessage());
        assertEquals("Hardware err", ex2.getMessage());
    }
}